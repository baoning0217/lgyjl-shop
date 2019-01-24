package com.vincce.shop.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created By BaoNing On 2019/1/24
 */
@Data
@Alias("product")
public class ProductPo implements Serializable{

    private Long id;

    private String productName;

    private Integer stock;

    private Double price;

    private Integer version;

    private String note;

}
