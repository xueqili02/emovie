package com.groupfour.eMovie.service.impl;

import com.groupfour.eMovie.dao.VoucherDao;
import com.groupfour.eMovie.entity.FlashVoucher;
import com.groupfour.eMovie.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("VoucherServiceImpl")
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherDao voucherDao;

    @Override
    public FlashVoucher addFlashVoucher(FlashVoucher flashVoucher) {
        voucherDao.addFlashVoucher(flashVoucher);
        return flashVoucher;
    }

    public FlashVoucher getFlashVoucherById(long id) {
        return voucherDao.getFlashVoucherById(id);
    }
}
