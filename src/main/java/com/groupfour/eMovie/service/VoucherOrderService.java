package com.groupfour.eMovie.service;

import com.groupfour.eMovie.entity.VoucherOrder;

public interface VoucherOrderService {

    VoucherOrder addFlashVoucherOrder(Long voucherId, Integer uid);

    VoucherOrder createVoucherOrder(Long voucherId, Integer uid);
}
