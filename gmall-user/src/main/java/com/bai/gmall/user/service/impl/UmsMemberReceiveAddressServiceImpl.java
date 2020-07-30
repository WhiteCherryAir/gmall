package com.bai.gmall.user.service.impl;


import com.bai.gmall.entity.UmsMemberReceiveAddress;
import com.bai.gmall.service.UmsMemberReceiveAddressService;
import com.bai.gmall.user.dao.UmsMemberReceiveAddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 会员收货地址表(UmsMemberReceiveAddress)表服务实现类
 *
 * @author makejava
 * @since 2020-06-04 11:01:43
 */
@Service
@Transactional
public class UmsMemberReceiveAddressServiceImpl implements UmsMemberReceiveAddressService {
    @Autowired
    private UmsMemberReceiveAddressDao umsMemberReceiveAddressDao;

    @Override
    public List<UmsMemberReceiveAddress> queryReceiveAddressByMemberId(String memberId) {
       //封装参数对象
        UmsMemberReceiveAddress umsMemberReceiveAddress = new UmsMemberReceiveAddress();
        umsMemberReceiveAddress.setMemberId(memberId);
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = umsMemberReceiveAddressDao.select(umsMemberReceiveAddress);
        /*Example example = new Example(UmsMemberReceiveAddress.class);
        example.createCriteria().andEqualTo("memberId",memberId);
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = umsMemberReceiveAddressDao.selectByExample(example);*/
        return umsMemberReceiveAddresses;
    }
}