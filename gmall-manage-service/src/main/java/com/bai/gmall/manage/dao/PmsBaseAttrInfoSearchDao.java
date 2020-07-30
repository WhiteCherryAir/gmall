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
public interface PmsBaseAttrInfoSearchDao extends Mapper<PmsBaseAttrInfo> {

    List<PmsBaseAttrInfo> selectAttrValueListByValueId(@Param("valueIdStr") String valueIdStr);
}