package com.bai.gmall.user.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.bai.gmall.entity.UmsMember;
import com.bai.gmall.service.UmsMemberService;
import org.springframework.stereotype.Controller;
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
    @Reference
    UmsMemberService umsMemberService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("selectOne")
    @ResponseBody
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
        System.out.println("111111111111111111111111111111111");
        List<UmsMember> umsMembers = umsMemberService.queryAll();
        System.out.println("查出数据"+umsMembers);
        return umsMembers;
    }


}