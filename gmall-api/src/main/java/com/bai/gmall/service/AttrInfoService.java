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

public interface AttrInfoService {

    //查平台属性
    List<PmsBaseAttrInfo> getAttrInfoList(String catalog3Id);
    //添加平台属性和平台属性值
    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    //修改平台属性值
    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    List<PmsBaseSaleAttr> baseSaleAttrList();

}