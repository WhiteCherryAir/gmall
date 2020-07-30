package com.bai.gmall.manage.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.bai.gmall.entity.PmsBaseCatalog1;
import com.bai.gmall.entity.PmsBaseCatalog2;
import com.bai.gmall.entity.PmsBaseCatalog3;
import com.bai.gmall.service.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 一级分类表(PmsBaseCatalog1)表控制层
 *
 * @author makejava
 * @since 2020-06-08 18:15:11
 */
@Controller
//开启跨域支持
@CrossOrigin
public class CatalogController {
    /**
     * 服务对象
     */
    @Reference
    CatalogService catalogService;

    /**
     * 通过主键查询单条数据
     *
     * @param //id 主键
     * @return 单条数据
     */
    //查询一级分类
    @RequestMapping("getCatalog1")
    @ResponseBody
    public List<PmsBaseCatalog1> getCatalog1() {
        List<PmsBaseCatalog1> catalog1s = catalogService.getCatalog1();
        return catalog1s;
    }
    //查询二级分类
    @RequestMapping("getCatalog2")
    @ResponseBody
    public List<PmsBaseCatalog1> getCatalog2(String catalog1Id) {
        List<PmsBaseCatalog1> catalog2s = catalogService.getCatalog2(catalog1Id);
        return catalog2s;
    }
    //查询三级分类
    @RequestMapping("getCatalog3")
    @ResponseBody
    public List<PmsBaseCatalog2> getCatalog3(String catalog2Id) {
        List<PmsBaseCatalog2> catalog3s = catalogService.getCatalog3(catalog2Id);
        return catalog3s;
    }

}