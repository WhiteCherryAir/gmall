package com.bai.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bai.gmall.entity.PmsBaseCatalog1;
import com.bai.gmall.entity.PmsBaseCatalog2;
import com.bai.gmall.manage.dao.PmsBaseCatalog1Dao;
import com.bai.gmall.manage.dao.PmsBaseCatalog2Dao;
import com.bai.gmall.manage.dao.PmsBaseCatalog3Dao;
import com.bai.gmall.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class CatalogServiceImpl implements CatalogService {
    @Autowired
    PmsBaseCatalog1Dao pmsBaseCatalog1Dao;
    @Autowired
    PmsBaseCatalog2Dao pmsBaseCatalog2Dao;
    @Autowired
    PmsBaseCatalog3Dao pmsBaseCatalog3Dao;

    @Override
    public List<PmsBaseCatalog1> getCatalog1() {
        List<PmsBaseCatalog1> pmsBaseCatalog1s = pmsBaseCatalog1Dao.selectAll();
        return pmsBaseCatalog1s;
    }

    @Override
    public List<PmsBaseCatalog1> getCatalog2(String catalog1Id) {


        List<PmsBaseCatalog1> catalog2 = pmsBaseCatalog1Dao.getCatalog2(catalog1Id);
        return catalog2;
    }

    @Override
    public List<PmsBaseCatalog2> getCatalog3(String catalog2Id) {
        List<PmsBaseCatalog2> catalog3 = pmsBaseCatalog2Dao.getCatalog3(catalog2Id);
        return catalog3;
    }
}
