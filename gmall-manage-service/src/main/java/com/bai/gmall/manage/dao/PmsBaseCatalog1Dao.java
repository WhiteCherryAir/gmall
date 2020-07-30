package com.bai.gmall.manage.dao;

import com.bai.gmall.entity.PmsBaseCatalog1;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 一级分类表(PmsBaseCatalog1)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-08 19:46:13
 */
public interface PmsBaseCatalog1Dao extends Mapper<PmsBaseCatalog1>{
    List<PmsBaseCatalog1> getCatalog2(String catalog1Id);
}