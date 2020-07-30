package com.bai.gmall.user.service.impl;

import com.bai.gmall.entity.UmsMember;
import com.bai.gmall.service.UmsMemberService;
import com.bai.gmall.user.dao.UmsMemberDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 会员表(UmsMember)表服务实现类
 *
 * @author makejava
 * @since 2020-06-03 21:41:30
 */
@Service
@Transactional
public class UmsMemberServiceImpl implements UmsMemberService {
    @Resource
    private UmsMemberDao umsMemberDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public UmsMember queryById(String id) {

        return umsMemberDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UmsMember> queryAllByLimit(int offset, int limit) {
        return this.umsMemberDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param umsMember 实例对象
     * @return 实例对象
     */
    @Override
    public UmsMember insert(UmsMember umsMember) {
        this.umsMemberDao.insert(umsMember);
        return umsMember;
    }

    /**
     * 修改数据
     *
     * @param umsMember 实例对象
     * @return 实例对象
     */
    @Override
    public UmsMember update(UmsMember umsMember) {
        this.umsMemberDao.update(umsMember);
        return this.queryById(umsMember.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.umsMemberDao.deleteById(id) > 0;
    }

    @Override
    public List<UmsMember> queryAll() {
        return umsMemberDao.queryAll();
    }
}