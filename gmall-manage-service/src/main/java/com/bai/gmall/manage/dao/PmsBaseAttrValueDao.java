package com.bai.gmall.manage.dao;

import com.bai.gmall.entity.PmsBaseAttrValue;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 属性值表(PmsBaseAttrValue)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-09 11:23:59
 */
public interface PmsBaseAttrValueDao extends Mapper<PmsBaseAttrValue> {
/*

    */
/**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     *//*

    PmsBaseAttrValue queryById(String id);

    */
/**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     *//*

    List<PmsBaseAttrValue> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    */
/**
     * 通过实体作为筛选条件查询
     *
     * @param pmsBaseAttrValue 实例对象
     * @return 对象列表
     *//*

    List<PmsBaseAttrValue> queryAll(PmsBaseAttrValue pmsBaseAttrValue);

    */
/**
     * 新增数据
     *
     * @param pmsBaseAttrValue 实例对象
     * @return 影响行数
     *//*

    int insert(PmsBaseAttrValue pmsBaseAttrValue);

    */
/**
     * 修改数据
     *
     * @param pmsBaseAttrValue 实例对象
     * @return 影响行数
     *//*

    int update(PmsBaseAttrValue pmsBaseAttrValue);

    */
/**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     *//*

    int deleteById(String id);
*/

    //根据attrId查出平台属性值
    List<PmsBaseAttrValue> selectByAttrId(String attrId);
}