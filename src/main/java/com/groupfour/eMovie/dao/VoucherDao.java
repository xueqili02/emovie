package com.groupfour.eMovie.dao;

import com.groupfour.eMovie.entity.FlashVoucher;
import org.apache.ibatis.annotations.*;

@Mapper
public interface VoucherDao {

    @Insert("INSERT INTO flash_voucher(stock, begin_time, end_time) " +
            "VALUES( #{stock}, #{begin_time}, #{end_time})")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    Integer addFlashVoucher(FlashVoucher flashVoucher);

    @Select("SELECT * FROM flash_voucher WHERE id=#{id}")
    FlashVoucher getFlashVoucherById(long id);

    // check if stock > 0, 通过乐观锁防止库存超卖
    @Update("UPDATE flash_voucher SET stock = stock + #{diff} WHERE id = #{id} AND stock > 0")
    boolean updateStock(long id, int diff);
}
