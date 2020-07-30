package com.bai.gmall.manage.dao;

import com.bai.gmall.entity.PmsBaseCatalog2;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsBaseCatalog2Dao extends Mapper<PmsBaseCatalog2> {
    List<PmsBaseCatalog2> getCatalog3(String catalog2Id);
}
