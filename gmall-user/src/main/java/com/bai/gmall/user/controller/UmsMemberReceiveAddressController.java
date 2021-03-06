package com.bai.gmall.user.controller;


import com.bai.gmall.entity.UmsMemberReceiveAddress;
import com.bai.gmall.service.UmsMemberReceiveAddressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 会员收货地址表(UmsMemberReceiveAddress)表控制层
 *
 * @author makejava
 * @since 2020-06-04 11:01:43
 */
@RestController
@RequestMapping("umsMemberReceiveAddress")
public class UmsMemberReceiveAddressController {
    /**
     * 服务对象
     */
    @Resource
    private UmsMemberReceiveAddressService umsMemberReceiveAddressService;


    @RequestMapping("queryReceiveAddress")
    @ResponseBody
    public List<UmsMemberReceiveAddress> queryReceiveAddress(String memberId){
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = umsMemberReceiveAddressService.queryReceiveAddressByMemberId(memberId);
        return umsMemberReceiveAddresses;
    }
}