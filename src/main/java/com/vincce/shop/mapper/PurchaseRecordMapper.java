package com.vincce.shop.mapper;

import com.vincce.shop.pojo.PurchaseRecordPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created By BaoNing On 2019/1/24
 */
@Mapper
public interface PurchaseRecordMapper {

    /**
     * 新增购买记录
     * @param purchaseRecordPo
     * @return
     */
    Integer insertPurchaseRecord(PurchaseRecordPo purchaseRecordPo);

}
