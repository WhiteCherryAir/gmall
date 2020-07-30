package com.bai.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.bai.gmall.entity.PmsSkuAttrValue;
import com.bai.gmall.entity.PmsSkuImage;
import com.bai.gmall.entity.PmsSkuInfo;
import com.bai.gmall.entity.PmsSkuSaleAttrValue;
import com.bai.gmall.manage.dao.PmsSkuAttrValueDao;
import com.bai.gmall.manage.dao.PmsSkuImageDao;
import com.bai.gmall.manage.dao.PmsSkuInfoDao;
import com.bai.gmall.manage.dao.PmsSkuSaleAttrValueDao;
import com.bai.gmall.service.SkuService;
import com.bai.gmall.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.UUID;

@Service
public class SkuServiceImpl implements SkuService {
    @Autowired
    PmsSkuInfoDao pmsSkuInfoDao;
    @Autowired
    PmsSkuAttrValueDao pmsSkuAttrValueDao;
    @Autowired
    PmsSkuSaleAttrValueDao pmsSkuSaleAttrValueDao;
    @Autowired
    PmsSkuImageDao pmsSkuImageDao;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        //插入sku信息
        int i = pmsSkuInfoDao.insertSelective(pmsSkuInfo);
        String skuId = pmsSkuInfo.getId();
        //插入平台属性关联
        List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        for (PmsSkuAttrValue pmsSkuAttrValue : skuAttrValueList) {
            pmsSkuAttrValue.setSkuId(skuId);
            pmsSkuAttrValueDao.insertSelective(pmsSkuAttrValue);
        }
        //插入销售属性关联
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
            pmsSkuSaleAttrValue.setSkuId(skuId);
            pmsSkuSaleAttrValueDao.insertSelective(pmsSkuSaleAttrValue);
        }
        //插入图片信息
        List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
        for (PmsSkuImage pmsSkuImage : skuImageList) {
            pmsSkuImage.setSkuId(skuId);
            pmsSkuImageDao.insertSelective(pmsSkuImage);
        }
    }

    //前台页面sku数据展示
    public PmsSkuInfo getSkuByIdFromDb(String skuId) {
        //sku商品对象
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        pmsSkuInfo.setId(skuId);
        PmsSkuInfo skuInfo = pmsSkuInfoDao.selectOne(pmsSkuInfo);
        //获取sku图片集合
        PmsSkuImage pmsSkuImage = new PmsSkuImage();
        pmsSkuImage.setSkuId(skuId);
        List<PmsSkuImage> pmsSkuImages = pmsSkuImageDao.select(pmsSkuImage);
        skuInfo.setSkuImageList(pmsSkuImages);

        return skuInfo;
    }

    //前台页面sku数据展示改为redis缓存查询
    @Override
    public PmsSkuInfo getSkuById(String skuId, String ip) {
        System.out.println("ip为" + ip + "的同学:" + Thread.currentThread().getName() + "进入的商品详情的请求");
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        // 链接缓存
        Jedis jedis = redisUtil.getJedis();
        try {
            // 查询缓存
            String skuKey = "sku:" + skuId + ":info";
            String skuJson = jedis.get(skuKey);

            if (StringUtils.isNotBlank(skuJson)) {//if(skuJson!=null&&!skuJson.equals(""))
                System.out.println("ip为" + ip + "的同学:" + Thread.currentThread().getName() + "从缓存中获取商品详情");

                pmsSkuInfo = JSON.parseObject(skuJson, PmsSkuInfo.class);
            } else {
                // 如果缓存中没有，查询mysql
                System.out.println("ip为" + ip + "的同学:" + Thread.currentThread().getName() + "发现缓存中没有，申请缓存的分布式锁：" + "sku:" + skuId + ":lock");

                // 设置分布式锁
                String token = UUID.randomUUID().toString();
                String OK = jedis.set("sku:" + skuId + ":lock", token, "nx", "ex", 10);// 拿到锁的线程有10秒的过期时间
                if (StringUtils.isNotBlank(OK) && OK.equals("OK")) {
                    // 设置成功，有权在10秒的过期时间内访问数据库
                    System.out.println("ip为" + ip + "的同学:" + Thread.currentThread().getName() + "有权在10秒的过期时间内访问数据库：" + "sku:" + skuId + ":lock");

                    pmsSkuInfo = getSkuByIdFromDb(skuId);
                    //Thread.sleep(5000);
                    //System.out.println("睡眠5秒");
                    if (pmsSkuInfo != null) {
                        // mysql查询结果存入redis
                        jedis.set("sku:" + skuId + ":info", JSON.toJSONString(pmsSkuInfo));
                    } else {
                        // 数据库中不存在该sku
                        // 为了防止缓存穿透将，null或者空字符串值设置给redis
                        jedis.setex("sku:" + skuId + ":info", 180, JSON.toJSONString(""));
                    }

                    // 在访问mysql后，将mysql的分布锁释放
                    System.out.println("ip为" + ip + "的同学:" + Thread.currentThread().getName() + "使用完毕，将锁归还：" + "sku:" + skuId + ":lock");
                    String lockToken = jedis.get("sku:" + skuId + ":lock");
                    //获取一下分布式锁
                    if (StringUtils.isNotBlank(lockToken) && lockToken.equals(token)) {
                        //jedis.eval("lua");可以用lua脚本在查询到key的同时删掉该key，防止高并发下产生的意外的发生
                        //判断分布式锁不为空，并且为自己的token方可删除
                        jedis.del("sku:" + skuId + ":lock");//用token确认删除的是自己的sku锁
                    }
                } else {
                    // 设置失败，自旋（该线程在睡眠几秒后，重新尝试访问本方法）
                    System.out.println("ip为" + ip + "的同学:" + Thread.currentThread().getName() + "没有拿到锁，开始自旋");

                    Thread.sleep(3000);
                    System.out.println("睡眠3秒");
                    return getSkuById(skuId, ip);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return pmsSkuInfo;
    }

    @Override
    public List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId) {
        List<PmsSkuInfo> pmsSkuInfos = pmsSkuInfoDao.selectSkuSaleAttrValueListBySpu(productId);
        return pmsSkuInfos;
    }

    @Override
    public List<PmsSkuInfo> getAllSku(String catalog3Id) {
        List<PmsSkuInfo> pmsSkuInfos = pmsSkuInfoDao.selectAll();

        for (PmsSkuInfo pmsSkuInfo : pmsSkuInfos) {
            String skuId = pmsSkuInfo.getId();
            PmsSkuAttrValue pmsSkuAttrValue = new PmsSkuAttrValue();
            pmsSkuAttrValue.setSkuId(skuId);
            List<PmsSkuAttrValue> PmsSkuAttrValueList = pmsSkuAttrValueDao.select(pmsSkuAttrValue);
            pmsSkuInfo.setSkuAttrValueList(PmsSkuAttrValueList);
        }
        return pmsSkuInfos;
    }
}
