package com.bai.gmall.service;

import com.bai.gmall.entity.PmsSearchParam;
import com.bai.gmall.entity.PmsSearchSkuInfo;

import java.util.List;

public interface SearchService {
    List<PmsSearchSkuInfo> list(PmsSearchParam pmsSearchParam);
}
