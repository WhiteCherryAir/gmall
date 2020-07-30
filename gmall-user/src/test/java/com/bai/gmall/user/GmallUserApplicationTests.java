package com.bai.gmall.user;


import com.bai.gmall.entity.UmsMember;
import com.bai.gmall.service.UmsMemberService;
import com.bai.gmall.user.dao.UmsMemberDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallUserApplicationTests {
    @Autowired
   private UmsMemberService umsMemberService;

    @Autowired
    private UmsMemberDao umsMemberDao;
    @Test
    public void testqueryAllService() {
        List<UmsMember> umsMembers = umsMemberService.queryAll();
        for (UmsMember umsMember : umsMembers) {
            System.out.println(umsMember);
        }
    }

    @Test
    public void testqueryAllDao() {
        List<UmsMember> umsMembers = umsMemberDao.queryAll();
        for (UmsMember umsMember : umsMembers) {
            System.out.println(umsMember);
        }
    }

    @Test
    public void testInsertUmsMembers() {
        int insert = umsMemberDao.insert(new UmsMember());

    }
    /*@Test
    public void testUpdateUmsMembers() {
        umsMemberDao.update(new UmsMember("11","2","修改测试用","111111",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null));

    }*/
    @Test
    public void testDeleteUmsMembers() {
        umsMemberDao.deleteById("11");
    }
    @Test
    public void testqueryOneUmsMembers() {
        UmsMember umsMember = umsMemberDao.queryById("10");
        System.out.println(umsMember);
    }

    @Test
    public void testselectAddress() {
        List<UmsMember> umsMembers = umsMemberDao.selectAddress("1");
        for (UmsMember umsMember : umsMembers) {
            System.out.println(umsMember);
        }
    }
}
