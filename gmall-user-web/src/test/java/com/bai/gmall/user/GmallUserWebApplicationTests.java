package com.bai.gmall.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bai.gmall.entity.UmsMember;
import com.bai.gmall.service.UmsMemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallUserWebApplicationTests {
    @Reference
    UmsMemberService umsMemberService;
    @Test
    public void contextLoads() {
        List<UmsMember> umsMembers = umsMemberService.queryAll();
        for (UmsMember umsMember : umsMembers) {
            System.out.println("1111111111111111111111111111111111");
            System.out.println("1111111111111111111111111111111111");
            System.out.println("1111111111111111111111111111111111");
            System.out.println("1111111111111111111111111111111111");
            System.out.println("1111111111111111111111111111111111");
            System.out.println("1111111111111111111111111111111111");
            System.out.println(umsMember);
        }
    }

}
