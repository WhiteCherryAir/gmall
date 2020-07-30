package com.bai.gmall.manage.dao;

import com.bai.gmall.entity.PmsBaseAttrInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 属性表(PmsBaseAttrInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-09 10:51:12
 */
public interface PmsBaseAttrInfoDao extends Mapper<PmsBaseAttrInfo> {
    //查平台属性
    List<PmsBaseAttrInfo> getAttrInfoList(String catalog3Id);

    //List<PmsBaseAttrInfo> selectAttrValueListByValueId(@Param("valueIdStr") String valueIdStr);
}