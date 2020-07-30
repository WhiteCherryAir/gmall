package com.bai.gmall.service;

import com.bai.gmall.entity.UmsMemberReceiveAddress;

import java.util.List;

/**
 * 会员收货地址表(UmsMemberReceiveAddress)表服务接口
 *
 * @author makejava
 * @since 2020-06-04 11:01:43
 */
public interface UmsMemberReceiveAddressService {


    List<UmsMemberReceiveAddress> queryReceiveAddressByMemberId(String memberId);
}