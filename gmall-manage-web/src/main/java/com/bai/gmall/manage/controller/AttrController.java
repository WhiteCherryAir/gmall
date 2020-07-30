package com.bai.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bai.gmall.entity.PmsBaseAttrInfo;
import com.bai.gmall.entity.PmsBaseAttrValue;
import com.bai.gmall.entity.PmsBaseSaleAttr;
import com.bai.gmall.service.AttrInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
//解决跨域访问
@CrossOrigin
public class AttrController {
    @Reference
    AttrInfoService attrInfoService;
    //查询三级分类下的平台属性
    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        List<PmsBaseAttrInfo> attrInfoList = attrInfoService.getAttrInfoList(catalog3Id);
        return attrInfoList;
    }
    //添加平台属性和平台属性值
    @RequestMapping("saveAttrInfo")
    @ResponseBody
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo) {

        String success = attrInfoService.saveAttrInfo(pmsBaseAttrInfo);
        System.out.println(success);
        return "forward:/attrInfoList";
    }
    //修改时先查出平台属性值   //http://127.0.0.1:8091/getAttrValueList?attrId=49
    @RequestMapping("getAttrValueList")
    @ResponseBody
    public List<PmsBaseAttrValue> getAttrValueList(String attrId){
        List<PmsBaseAttrValue> attrValueList = attrInfoService.getAttrValueList(attrId);
        return attrValueList;
    }

    //查出销售属性字典表（名称）的数据
    @RequestMapping("baseSaleAttrList")
    @ResponseBody
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        List<PmsBaseSaleAttr> pmsBaseSaleAttrs = attrInfoService.baseSaleAttrList();
        return pmsBaseSaleAttrs;
    }
}
