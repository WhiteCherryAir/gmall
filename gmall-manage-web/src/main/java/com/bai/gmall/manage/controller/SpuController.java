package com.bai.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bai.gmall.entity.*;
import com.bai.gmall.manage.util.PmsUploadUtil;
import com.bai.gmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
//开启跨域支持
@CrossOrigin
public class SpuController {
    @Reference
    SpuService spuService;

    //spu（销售属性）数据列表查询
    //http://127.0.0.1:8091/spuList?catalog3Id=61
    @RequestMapping("spuList")
    @ResponseBody
    public List<PmsProductInfo> spuList(String catalog3Id) {
        List<PmsProductInfo> pmsProductInfos = spuService.spuList(catalog3Id);
        return pmsProductInfos;
    }

    //添加spu和销售属性和销售属性值
    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo) {
        String success = spuService.saveSpuInfo(pmsProductInfo);
        System.out.println(success);
        return "forward:/spuList";
    }

    //spu图片上传
    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile) {
        //将图片或者视频上传到分布式文件存储系统

        //将图片存储路径返回给页面
        String imgUrl = PmsUploadUtil.uploadImage(multipartFile);
        System.out.println(imgUrl);
        return imgUrl;
    }

    //添加sku时查询spu属性和值
    @RequestMapping("spuSaleAttrList")
    @ResponseBody
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {
        List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrList(spuId);
        return pmsProductSaleAttrs;
    }

    //添加sku时查询spu图片值
    @RequestMapping("spuImageList")
    @ResponseBody
    public List<PmsProductImage> spuImageList(String spuId) {
        List<PmsProductImage> pmsProductImages = spuService.spuImageList(spuId);
        return pmsProductImages;
    }
}
