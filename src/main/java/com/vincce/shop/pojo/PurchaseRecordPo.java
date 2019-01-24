package com.vincce.shop.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created By BaoNing On 2019/1/24
 */
@Data
@Alias("purchaseRecord")
public class PurchaseRecordPo implements Serializable {

    private Long id;

    private Long userId;

    private Long productId;

    private Double price;

    private Integer quantity;

    private Double sum;

    private Timestamp purchaseTime;

    private String note;

}
