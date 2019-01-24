package com.vincce.shop.controller;

import com.vincce.shop.common.Result;
import com.vincce.shop.entity.vo.PruchaseVo;
import com.vincce.shop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By BaoNing On 2019/1/24
 */
@RestController
@RequestMapping("shop")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/purchase")
    public Result purchase(@RequestBody PruchaseVo pv){
        Long userId = pv.getPurchaseDto().getUserId();
        Long productId = pv.getPurchaseDto().getProductId();
        Integer quantity = pv.getPurchaseDto().getQuantity();
        boolean status = purchaseService.purchase(userId, productId, quantity);
        String message = status ? "抢购成功" : "抢购失败";
        Result result =  new Result(status, message);
        return result;
    }

}
