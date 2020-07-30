package com.bai.gmall.service;

import com.bai.gmall.entity.PmsBaseAttrInfo;
import com.bai.gmall.entity.PmsBaseAttrValue;
import com.bai.gmall.entity.PmsBaseSaleAttr;

import java.util.List;
import java.util.Set;

/**
 * 属性表(PmsBaseAttrInfo)表服务接口
 *
 * @author makejava
 * @since 2020-06-09 10:48:59
 */

public interface AttrInfoSearchService {

    List<PmsBaseAttrInfo> getAttrValueListByValueId(Set<String> valueIdSet);
}