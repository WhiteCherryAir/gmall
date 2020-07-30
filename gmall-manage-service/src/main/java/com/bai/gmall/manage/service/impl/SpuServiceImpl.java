package com.bai.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bai.gmall.entity.PmsProductImage;
import com.bai.gmall.entity.PmsProductInfo;
import com.bai.gmall.entity.PmsProductSaleAttr;
import com.bai.gmall.entity.PmsProductSaleAttrValue;
import com.bai.gmall.manage.dao.PmsProductImageDao;
import com.bai.gmall.manage.dao.PmsProductInfoDao;
import com.bai.gmall.manage.dao.PmsProductSaleAttrDao;
import com.bai.gmall.manage.dao.PmsProductSaleAttrValueDao;
import com.bai.gmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import javax.lang.model.element.VariableElement;
import java.util.List;

@Service
public class SpuServiceImpl implements SpuService {
    @Autowired
    PmsProductInfoDao pmsProductInfoDao;
    @Autowired
    PmsProductSaleAttrDao pmsProductSaleAttrDao;
    @Autowired
    PmsProductSaleAttrValueDao pmsProductSaleAttrValueDao;
    @Autowired
    PmsProductImageDao pmsProductImageDao;

    @Override
    public List<PmsProductInfo> spuList(String catalog3Id) {
        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3Id);
        List<PmsProductInfo> PmsProductInfos = pmsProductInfoDao.select(pmsProductInfo);
        return PmsProductInfos;
    }

    @Override
    public String saveSpuInfo(PmsProductInfo pmsProductInfo) {
        String id = pmsProductInfo.getId();
        //判断商品id是否存在
        if (id == null) {
            //不存在则添加商品信息
            pmsProductInfoDao.insertSelective(pmsProductInfo);
            //添加图片信息
            List<PmsProductImage> spuImageList = pmsProductInfo.getSpuImageList();
            for (PmsProductImage pmsProductImage : spuImageList) {
                pmsProductImage.setProductId(pmsProductInfo.getId());
                pmsProductImageDao.insertSelective(pmsProductImage);
            }
            //添加销售属性
            List<PmsProductSaleAttr> spuSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
            for (PmsProductSaleAttr pmsProductSaleAttr : spuSaleAttrList) {
                pmsProductSaleAttr.setProductId(pmsProductInfo.getId());
                pmsProductSaleAttrDao.insertSelective(pmsProductSaleAttr);
                //添加销售属性值
                List<PmsProductSaleAttrValue> spuSaleAttrValueList = pmsProductSaleAttr.getSpuSaleAttrValueList();
                for (PmsProductSaleAttrValue pmsProductSaleAttrValue : spuSaleAttrValueList) {
                    pmsProductSaleAttrValue.setProductId(pmsProductInfo.getId());
                    pmsProductSaleAttrValueDao.insertSelective(pmsProductSaleAttrValue);
                }
            }
        } else {
            //商品id不存在时执行修改销售属性值
            Example example = new Example(PmsProductInfo.class);
            example.createCriteria().andEqualTo("id", pmsProductInfo.getId());
            //商品信息修改
            pmsProductInfoDao.updateByExampleSelective(pmsProductInfo, example);
            //按照图片ID删除原来的图
            PmsProductImage pmsProductImage = new PmsProductImage();
            pmsProductImage.setProductId(pmsProductInfo.getId());
            //删除原来的图片
            pmsProductImageDao.delete(pmsProductImage);
            //添加新的图片
            List<PmsProductImage> spuImageList = pmsProductInfo.getSpuImageList();
            for (PmsProductImage productImage : spuImageList) {
                productImage.setProductId(pmsProductInfo.getId());
                pmsProductImageDao.insertSelective(productImage);
            }
            //按照销售属性ID修改
            PmsProductSaleAttr pmsProductSaleAttr = new PmsProductSaleAttr();
            Example example1 = new Example(PmsProductSaleAttr.class);
            pmsProductSaleAttr.setProductId(pmsProductInfo.getId());
            example1.createCriteria().andEqualTo("id", pmsProductSaleAttr.getProductId());
            pmsProductSaleAttrDao.updateByExampleSelective(pmsProductSaleAttr, example1);
            pmsProductSaleAttrDao.delete(pmsProductSaleAttr);
            List<PmsProductSaleAttrValue> spuSaleAttrValueList = pmsProductSaleAttr.getSpuSaleAttrValueList();
            for (PmsProductSaleAttrValue pmsProductSaleAttrValue : spuSaleAttrValueList) {
                pmsProductSaleAttrValue.setProductId(pmsProductInfo.getId());
                pmsProductSaleAttrValueDao.insertSelective(pmsProductSaleAttrValue);
            }
        }
        return "success";
    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {

        PmsProductSaleAttr pmsProductSaleAttr = new PmsProductSaleAttr();
        pmsProductSaleAttr.setProductId(spuId);
        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductSaleAttrDao.select(pmsProductSaleAttr);

        for (PmsProductSaleAttr productSaleAttr : pmsProductSaleAttrs) {
            PmsProductSaleAttrValue pmsProductSaleAttrValue = new PmsProductSaleAttrValue();
            pmsProductSaleAttrValue.setProductId(spuId);
            //销售属性id用的是系统字典表中的id不是销售属性主键id
            pmsProductSaleAttrValue.setSaleAttrId(productSaleAttr.getSaleAttrId());
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValues = pmsProductSaleAttrValueDao.select(pmsProductSaleAttrValue);
            productSaleAttr.setSpuSaleAttrValueList(pmsProductSaleAttrValues);
        }

        return pmsProductSaleAttrs;
    }

    @Override
    public List<PmsProductImage> spuImageList(String spuId) {
        PmsProductImage pmsProductImage = new PmsProductImage();
        pmsProductImage.setProductId(spuId);
        List<PmsProductImage> pmsProductImages = pmsProductImageDao.select(pmsProductImage);
        return pmsProductImages;
    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String productId,String skuId) {
       /* PmsProductSaleAttr pmsProductSaleAttr = new PmsProductSaleAttr();
        pmsProductSaleAttr.setProductId(productId);
        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductSaleAttrDao.select(pmsProductSaleAttr);
        for (PmsProductSaleAttr productSaleAttr : pmsProductSaleAttrs) {
            String saleAttrId = productSaleAttr.getSaleAttrId();
            PmsProductSaleAttrValue pmsProductSaleAttrValue = new PmsProductSaleAttrValue();
            pmsProductSaleAttrValue.setSaleAttrId(saleAttrId);
            pmsProductSaleAttrValue.setProductId(productId);
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValues = pmsProductSaleAttrValueDao.select(pmsProductSaleAttrValue);
            productSaleAttr.setSpuSaleAttrValueList(pmsProductSaleAttrValues);
        }*/
        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductSaleAttrDao.selectSpuSaleAttrListCheckBySku(productId,skuId);
        return pmsProductSaleAttrs;
    }
}
