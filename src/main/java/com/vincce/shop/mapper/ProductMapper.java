package com.vincce.shop.mapper;

import com.vincce.shop.pojo.ProductPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created By BaoNing On 2019/1/24
 */
@Mapper
public interface ProductMapper {

    /**
     * 获取产品
     * @param id
     * @return
     */
    ProductPo getProduct(Long id);

    /**
     * 减库存
     * @param id
     * @param quantity
     * @return
     */
    Integer decreaseProduct(@Param("id") Long id, @Param("quantity") Integer quantity);


}
