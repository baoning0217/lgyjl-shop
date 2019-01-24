package com.vincce.shop.service;

import com.vincce.shop.mapper.ProductMapper;
import com.vincce.shop.mapper.PurchaseRecordMapper;
import com.vincce.shop.pojo.ProductPo;
import com.vincce.shop.pojo.PurchaseRecordPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created By BaoNing On 2019/1/24
 */
@Service
public class PurchaseService {

    @Autowired
    private PurchaseRecordMapper purchaseRecordMapper;

    @Autowired
    private ProductMapper productMapper;

    /**
     * 处理购买业务
     * @param userId 用户编号
     * @param productId 产品编号
     * @param quantity 购买数量
     * @return
     */
    @Transactional
    public boolean purchase(Long userId, Long productId, Integer quantity){
        //获取产品
        ProductPo product = productMapper.getProduct(productId);
        //比较库存和购买数量
        if (product.getStock() < quantity){
            //库存不足
            return false;
        }
        //扣减库存
        productMapper.decreaseProduct(productId, quantity);
        //初始化购买记录
        PurchaseRecordPo pr = this.initPurchaseRecord(userId, product, quantity);
        purchaseRecordMapper.insertPurchaseRecord(pr);
        return true;
    }


    /**
     * 初始化购买信息
     * @param userId
     * @param product
     * @param quantity
     * @return
     */
    private PurchaseRecordPo initPurchaseRecord(Long userId, ProductPo product, Integer quantity){
        PurchaseRecordPo pr = new PurchaseRecordPo();
        pr.setNote("购买日志,时间: " + System.currentTimeMillis());
        pr.setPrice(product.getPrice());
        pr.setProductId(product.getId());
        pr.setQuantity(quantity);
        Double sum = product.getPrice() * quantity;
        pr.setSum(sum);
        pr.setUserId(userId);
        return pr;
    }


}
