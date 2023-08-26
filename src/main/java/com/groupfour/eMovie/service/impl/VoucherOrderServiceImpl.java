package com.groupfour.eMovie.service.impl;

import com.groupfour.eMovie.dao.VoucherOrderDao;
import com.groupfour.eMovie.entity.VoucherOrder;
import com.groupfour.eMovie.service.VoucherOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("VoucherOrderServiceImpl")
public class VoucherOrderServiceImpl implements VoucherOrderService {

    @Autowired
    private VoucherOrderDao voucherOrderDao;

    @Override
    public VoucherOrder addFlashVoucherOrder(Long id) {
        return null;
    }
}
