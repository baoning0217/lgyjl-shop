package com.vincce.shop.service;

import com.vincce.shop.mapper.ProductMapper;
import com.vincce.shop.mapper.PurchaseRecordMapper;
import com.vincce.shop.pojo.ProductPo;
import com.vincce.shop.pojo.PurchaseRecordPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
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
    //启动Spring数据库事务机制,并将隔离级别设置为读写提交
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean purchase(Long userId, Long productId, Integer quantity){
        //当前时间
        long start = System.currentTimeMillis();
        //循环尝试直至成功
        while (true){
            //循环时间
            long end = System.currentTimeMillis();
            //如果循环时间大于100ms返回终止循环
            if (end - start > 100){
                return false;
            }
            //获取产品
            ProductPo product = productMapper.getProduct(productId);
            //比较库存和购买数量
            if (product.getStock() < quantity){
                //库存不足
                return false;
            }
            //获取当前版本号
            Integer version = product.getVersion();
            //扣减库存,同时将当前版本号发送给后台进行比较
            Integer result = productMapper.decreaseProduct(productId, quantity, version);
            //如果更新数据失败，说明数据在多线程中被其他线程修改，导致失败返回。
            //导致失败，则通过循环重入尝试购买商品
            if (result == 0){
                continue;
            }
            //初始化购买记录
            PurchaseRecordPo pr = this.initPurchaseRecord(userId, product, quantity);
            purchaseRecordMapper.insertPurchaseRecord(pr);
            return true;
        }
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
