package com.bai.gmall.service;

import com.bai.gmall.entity.PmsSkuInfo;

import java.util.List;

public interface SkuService {
    void saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    PmsSkuInfo getSkuById(String skuId,String ip);

    List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId);

    List<PmsSkuInfo> getAllSku(String catalog3Id);
}
