package com.vincce.shop.entity.dto;

import lombok.Data;

/**
 * Created By BaoNing On 2019/1/24
 */
@Data
public class PurchaseDto {

    public Long userId;

    public Long productId;

    public Integer quantity;

}
