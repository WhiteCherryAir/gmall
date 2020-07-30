package com.bai.gmall.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.bai.gmall.entity.PmsProductSaleAttr;
import com.bai.gmall.entity.PmsSkuInfo;
import com.bai.gmall.entity.PmsSkuSaleAttrValue;
import com.bai.gmall.service.SkuService;
import com.bai.gmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class ItemController {
    @Reference
    SkuService skuService;
    @Reference
    SpuService spuService;

    @RequestMapping("{skuId}.html")
    public String item(@PathVariable String skuId, ModelMap modelMap, HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();//直接从请求中获取ip
        //request.getHeader("");//nginx负载均衡
        PmsSkuInfo pmsSkuInfo = skuService.getSkuById(skuId, remoteAddr);
        //sku对象
        modelMap.addAttribute("skuInfo", pmsSkuInfo);
        //销售属性列表
        List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrListCheckBySku(pmsSkuInfo.getProductId(), pmsSkuInfo.getId());
        modelMap.addAttribute("spuSaleAttrListCheckBySku", pmsProductSaleAttrs);
        //查询当前sku的spu的其他sku集合的hash表
        Map<String, String> skuSaleAttrHash = new HashMap<>();
        List<PmsSkuInfo> pmsSkuInfos = skuService.getSkuSaleAttrValueListBySpu(pmsSkuInfo.getProductId());
        for (PmsSkuInfo skuInfo : pmsSkuInfos) {
            String k = "";
            String v = skuInfo.getId();
            List<PmsSkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
            for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
                k += pmsSkuSaleAttrValue.getSaleAttrValueId() + "|";//256|260
            }
            skuSaleAttrHash.put(k, v);
        }
        //将sku属性的销售属性hash表放到页面
        String skuSaleAttrHashJsonStr = JSON.toJSONString(skuSaleAttrHash);
        modelMap.addAttribute("skuSaleAttrHashJsonStr", skuSaleAttrHashJsonStr);
        return "item";
    }


    //测试用方法
    @RequestMapping("index")
    public String index(ModelMap modelMap) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("循环数据" + i);
        }
        modelMap.addAttribute("list", list);
        modelMap.addAttribute("hello", "hello thymeleaf !!");
        modelMap.addAttribute("check", "1");
        return "index";
    }
}
