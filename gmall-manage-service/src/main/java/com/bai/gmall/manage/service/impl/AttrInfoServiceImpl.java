package com.bai.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bai.gmall.entity.PmsBaseAttrInfo;
import com.bai.gmall.entity.PmsBaseAttrValue;
import com.bai.gmall.entity.PmsBaseSaleAttr;
import com.bai.gmall.manage.dao.PmsBaseAttrInfoDao;
import com.bai.gmall.manage.dao.PmsBaseAttrValueDao;
import com.bai.gmall.manage.dao.PmsBaseSaleAttrDao;
import com.bai.gmall.service.AttrInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 属性表(PmsBaseAttrInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-06-09 11:05:34
 */
@Service
public class AttrInfoServiceImpl implements AttrInfoService {
    @Autowired
    PmsBaseAttrInfoDao pmsBaseAttrInfoDao;
    @Autowired
    PmsBaseAttrValueDao pmsBaseAttrValueDao;
    @Autowired
    PmsBaseSaleAttrDao pmsBaseSaleAttrDao;

    /*查平台属性*/
    @Override
    public List<PmsBaseAttrInfo> getAttrInfoList(String catalog3Id) {
        //查到平台属性
        List<PmsBaseAttrInfo> attrInfoList = pmsBaseAttrInfoDao.getAttrInfoList(catalog3Id);
        //遍历平台属性循环添加值
        for (PmsBaseAttrInfo pmsBaseAttrInfo : attrInfoList) {
            //获得平台属性值
            PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
            pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
            //根据平台属性值获得平台属性值集合
            List<PmsBaseAttrValue> pmsBaseAttrValues = pmsBaseAttrValueDao.select(pmsBaseAttrValue);
            //将查出来的平台属性值放入平台属性
            pmsBaseAttrInfo.setAttrValueList(pmsBaseAttrValues);
        }
        return attrInfoList;
    }

    /*添加平台属性和平台属性值*/
    @Override
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        String id = pmsBaseAttrInfo.getId();
        //判断平台属性id为空执行添加
        if (id == null) {
            //insertSelective 将不为空的值插入数据库
            //保存属性
            pmsBaseAttrInfoDao.insertSelective(pmsBaseAttrInfo);//insert insertSelective 是否将null插入数据库
            //保存属性值
            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                pmsBaseAttrValueDao.insertSelective(pmsBaseAttrValue);
            }
        } else {
            //id不为空执行对平台属性值的修改
            //属性修改
            Example example = new Example(PmsBaseAttrInfo.class);
            example.createCriteria().andEqualTo("id", pmsBaseAttrInfo.getId());
            pmsBaseAttrInfoDao.updateByExampleSelective(pmsBaseAttrInfo, example);
            //属性值修改
            //按照属性Id删除所有属性值
            PmsBaseAttrValue pmsBaseAttrValueDel = new PmsBaseAttrValue();
            pmsBaseAttrValueDel.setAttrId(pmsBaseAttrInfo.getId());
            pmsBaseAttrValueDao.delete(pmsBaseAttrValueDel);
            //删除后循环插入新数据
            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                pmsBaseAttrValueDao.insertSelective(pmsBaseAttrValue);
            }
        }
        return "success";
    }

    /*修改平台属性值时查出数据*/
    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        List<PmsBaseAttrValue> pmsBaseAttrValues = pmsBaseAttrValueDao.selectByAttrId(attrId);
        return pmsBaseAttrValues;
    }

    /*查出销售属性*/
    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {

        return pmsBaseSaleAttrDao.selectAll();
    }


}