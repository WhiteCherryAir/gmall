package com.bai.gmall.user.controller;


import com.bai.gmall.entity.UmsMember;
import com.bai.gmall.service.UmsMemberService;
import com.bai.gmall.user.dao.UmsMemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 会员表(UmsMember)表控制层
 *
 * @author makejava
 * @since 2020-06-03 21:41:30
 */
@Controller
@RequestMapping("umsMember")
public class UmsMemberController {
    /**
     * 服务对象
     */
    @Autowired
    private UmsMemberService umsMemberService;

    @Autowired
   private UmsMemberDao umsMemberDao;
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UmsMember selectOne(String id) {
        return this.umsMemberService.queryById(id);
    }

    @RequestMapping("index")
    @ResponseBody
    public String index(){
        return "hello user";
    }

    @RequestMapping("queryAllUser")
    @ResponseBody
    public List<UmsMember> queryAllUser(){
        return umsMemberService.queryAll();
    }

    @RequestMapping("queryAddress")
    @ResponseBody
    public List<UmsMember> queryAddress(String id){
        List<UmsMember> umsMembers = umsMemberDao.selectAddress(id);

        for (UmsMember umsMember : umsMembers) {
            System.out.println(umsMember);
        }
        return umsMembers;
    }
}