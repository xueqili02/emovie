package com.groupfour.eMovie.dao;

import com.groupfour.eMovie.entity.VoucherOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VoucherOrderDao {

    @Insert("INSERT INTO voucher_order(id, user_id, voucher_id) " +
            "VALUES(#{id}, #{user_id}, #{voucher_id})")
    Integer addFlashVoucherOrder(VoucherOrder voucherOrder);

}
