package com.bai.gmall.service;

import com.bai.gmall.entity.UmsMember;

import java.util.List;

/**
 * 会员表(UmsMember)表服务接口
 *
 * @author makejava
 * @since 2020-06-03 21:41:30
 */
public interface UmsMemberService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UmsMember queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UmsMember> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param umsMember 实例对象
     * @return 实例对象
     */
    UmsMember insert(UmsMember umsMember);

    /**
     * 修改数据
     *
     * @param umsMember 实例对象
     * @return 实例对象
     */
    UmsMember update(UmsMember umsMember);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    List<UmsMember> queryAll();

}