package com.groupfour.eMovie.controller;

import com.groupfour.eMovie.entity.FlashVoucher;
import com.groupfour.eMovie.service.VoucherService;
import com.groupfour.eMovie.utils.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vouchers")
@Tag(name = "优惠券API")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @PostMapping("")
    public Result addFlashVoucher(@RequestBody FlashVoucher flashVoucher) {
        HttpStatus code = HttpStatus.OK;
        String message = "";
        FlashVoucher newVoucher = null;

        newVoucher = voucherService.addFlashVoucher(flashVoucher);
        if (newVoucher == null) {
            message = "failure";
        } else {
            message = "success";
        }

        return new Result(code.value(), message, newVoucher);
    }


    @GetMapping("/flash/id/{id}")
    public Result getFlashVoucherById(@PathVariable long id) {
        HttpStatus code = HttpStatus.OK;
        String message = "";

        FlashVoucher voucher = voucherService.getFlashVoucherById(id);
        if (voucher == null) {
            message = "failure";
        } else {
            message = "success";
        }

        return new Result(code.value(), message, voucher);
    }
}
