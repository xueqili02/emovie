package com.groupfour.eMovie.dao;

import com.groupfour.eMovie.entity.FlashVoucher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface VoucherDao {

    @Insert("INSERT INTO flash_voucher(stock, begin_time, end_time) " +
            "VALUES( #{stock}, #{begin_time}, #{end_time})")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    Integer addFlashVoucher(FlashVoucher flashVoucher);
}
