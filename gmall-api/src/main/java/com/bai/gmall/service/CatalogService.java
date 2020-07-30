package com.bai.gmall.service;

import com.bai.gmall.entity.PmsBaseCatalog1;
import com.bai.gmall.entity.PmsBaseCatalog2;
import com.bai.gmall.entity.PmsBaseCatalog3;

import java.util.List;

public interface CatalogService {

    List<PmsBaseCatalog1> getCatalog1();

    List<PmsBaseCatalog1> getCatalog2(String catalog1Id);

    List<PmsBaseCatalog2> getCatalog3(String catalog2Id);


}
