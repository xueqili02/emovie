package com.groupfour.eMovie.dao;

import com.groupfour.eMovie.entity.VoucherOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface VoucherOrderDao {

    @Insert("INSERT INTO voucher_order(id, user_id, voucher_id) " +
            "VALUES(#{id}, #{user_id}, #{voucher_id})")
    Integer addFlashVoucherOrder(VoucherOrder voucherOrder);

    @Select("SELECT COUNT(*) FROM voucher_order WHERE user_id = #{uid} AND voucher_id = #{voucherId}")
    Integer getCountByUidAndVoucherId(int uid, long voucherId);
}
