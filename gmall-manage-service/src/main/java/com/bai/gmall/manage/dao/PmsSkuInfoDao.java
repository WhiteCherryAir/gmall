package com.bai.gmall.manage.dao;

import com.bai.gmall.entity.PmsSkuInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsSkuInfoDao extends Mapper<PmsSkuInfo> {
    List<PmsSkuInfo> selectSkuSaleAttrValueListBySpu(String productId);
}
