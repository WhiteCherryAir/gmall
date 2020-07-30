package com.bai.gmall.manage.dao;

import com.bai.gmall.entity.PmsProductSaleAttr;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * (PmsProductSaleAttr)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-16 20:33:16
 */
public interface PmsProductSaleAttrDao extends Mapper<PmsProductSaleAttr> {


    List<PmsProductSaleAttr> selectSpuSaleAttrListCheckBySku(@Param("productId") String productId, @Param("skuId") String skuId);
}