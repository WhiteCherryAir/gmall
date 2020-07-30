package com.bai.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bai.gmall.entity.PmsBaseAttrInfo;
import com.bai.gmall.entity.PmsBaseAttrValue;
import com.bai.gmall.entity.PmsBaseSaleAttr;
import com.bai.gmall.manage.dao.PmsBaseAttrInfoDao;
import com.bai.gmall.manage.dao.PmsBaseAttrInfoSearchDao;
import com.bai.gmall.manage.dao.PmsBaseAttrValueDao;
import com.bai.gmall.manage.dao.PmsBaseSaleAttrDao;
import com.bai.gmall.service.AttrInfoSearchService;
import com.bai.gmall.service.AttrInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Set;

/**
 * 属性表(PmsBaseAttrInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-06-09 11:05:34
 */
@Service
public class AttrInfoSearchServiceImpl implements AttrInfoSearchService {
    @Autowired
    PmsBaseAttrInfoSearchDao pmsBaseAttrInfoSearchDao;

    @Override
    public List<PmsBaseAttrInfo> getAttrValueListByValueId(Set<String> valueIdSet) {
        String valueIdStr = StringUtils.join(valueIdSet, ",");
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = pmsBaseAttrInfoSearchDao.selectAttrValueListByValueId(valueIdStr);
        return pmsBaseAttrInfos;
    }


}