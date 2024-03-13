package com.groupfour.eMovie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@TableName("VoucherOrder")
public class VoucherOrder {

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @TableId(value = "USER_ID")
    private Integer userId;

    @TableId(value = "VOUCHER_ID")
    private Long voucherId;

    @TableId(value = "STATUS")
    private Integer status;

    @TableId(value = "PAY_TIME")
    private LocalDateTime payTime;

    @TableId(value = "USE_TIME")
    private LocalDateTime useTime;

    @TableId(value = "CREATE_TIME")
    private LocalDateTime createTime;

    @TableId(value = "LAST_MOD_TIME")
    private LocalDateTime lastModTime;
}
