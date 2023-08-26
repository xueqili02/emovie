package com.groupfour.eMovie.controller;

import com.groupfour.eMovie.entity.VoucherOrder;
import com.groupfour.eMovie.service.VoucherOrderService;
import com.groupfour.eMovie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voucher-order")
public class VoucherOrderController {
    @Autowired
    private VoucherOrderService voucherOrderService;

    @PostMapping("/flash/id/{id}")
    public Result flashVoucherOrder(@PathVariable Long id) {
        HttpStatus code = HttpStatus.OK;
        String message = "";

        VoucherOrder voucherOrder = voucherOrderService.addFlashVoucherOrder(id);
        if (voucherOrder == null) {
            message = "failure";
        } else {
            message = "success";
        }

        return new Result(code.value(), message, voucherOrder);
    }
}
