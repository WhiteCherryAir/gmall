package com.bai.gmall.manage;

import com.bai.gmall.entity.PmsBaseAttrValue;
import com.bai.gmall.entity.PmsBaseCatalog1;
import com.bai.gmall.entity.PmsProductSaleAttr;
import com.bai.gmall.manage.dao.PmsBaseAttrValueDao;
import com.bai.gmall.manage.dao.PmsBaseCatalog1Dao;
import com.bai.gmall.manage.dao.PmsProductSaleAttrDao;
import com.bai.gmall.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageServiceApplicationTests {
    @Autowired
    PmsBaseCatalog1Dao pmsBaseCatalog1Dao;
    @Autowired
    PmsBaseAttrValueDao pmsBaseAttrValueDao;
    @Autowired
    PmsProductSaleAttrDao pmsProductSaleAttrDao;
    @Autowired
    RedisUtil redisUtil;

    @Test
    public void testCatalog1Dao() {
        List<PmsBaseCatalog1> catalog2 = pmsBaseCatalog1Dao.getCatalog2("1");
        for (PmsBaseCatalog1 pmsBaseCatalog1 : catalog2) {
            System.out.println("11111111111111111111");
            System.out.println("11111111111111111111");
            System.out.println("11111111111111111111");
            System.out.println("11111111111111111111");
            System.out.println(pmsBaseCatalog1);
        }
    }

    @Test
    public void testattrIdDao() {
        List<PmsBaseAttrValue> pmsBaseAttrValue = pmsBaseAttrValueDao.selectByAttrId("49");
        for (PmsBaseAttrValue baseAttrValue : pmsBaseAttrValue) {
            System.out.println("2222222222222222222");
            System.out.println("2222222222222222222");
            System.out.println("2222222222222222222");
            System.out.println("2222222222222222222");
            System.out.println(baseAttrValue);
        }

    }

    @Test
    public void testSelectSpuSaleAttrListCheckBySku() {
        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductSaleAttrDao.selectSpuSaleAttrListCheckBySku("71", "106");
        for (PmsProductSaleAttr pmsProductSaleAttr : pmsProductSaleAttrs) {
            System.out.println(pmsProductSaleAttr.toString());
        }
    }

    @Test
    public void testRedis(){
        Jedis jedis = redisUtil.getJedis();
        System.out.println(jedis);
    }

}
