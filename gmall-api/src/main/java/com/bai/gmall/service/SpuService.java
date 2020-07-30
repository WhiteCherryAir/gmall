package com.bai.gmall.service;

import com.bai.gmall.entity.PmsProductImage;
import com.bai.gmall.entity.PmsProductInfo;
import com.bai.gmall.entity.PmsProductSaleAttr;

import java.util.List;

public interface SpuService {
    List<PmsProductInfo> spuList(String catalog3Id);

    String saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    List<PmsProductImage> spuImageList(String spuId);

    List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String productId,String skuId);
}
