package com.groupfour.eMovie.controller;

import com.groupfour.eMovie.entity.Order;
import com.groupfour.eMovie.service.OrderService;
import com.groupfour.eMovie.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Tag(name = "订单API")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("")
    @Operation(summary = "插入订单")
    public Result insertOrder(@Schema(example = "{\"uid\": \"1\"}")
            @RequestBody Order order) {
        HttpStatus code = HttpStatus.OK;
        String message = "";
        // todo
        return new Result(code.value(), message, null);
    }

    @GetMapping("")
    @Operation(summary = "获取所有订单信息")
    public Result getOrders() {
        HttpStatus code = HttpStatus.OK;
        String message = "";
        // todo
        return new Result(code.value(), message, null);
    }

    @GetMapping("/uid/{uid}")
    @Operation(summary = "通过用户id获取订单详情")
    public Result getOrderByUserId(@PathVariable int uid) {
        HttpStatus code = HttpStatus.OK;
        String message = "";
        // todo
        return new Result(code.value(), message, null);
    }
}
