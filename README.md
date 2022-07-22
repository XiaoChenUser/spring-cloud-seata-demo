# 工程简介
1.order-seata 和 stock-seata 展示普通的 @Transactional 无法支持全局事务；  
2.alibaba-order-seata 和 alibaba-stock-seata 为各自引入 seata 后，演示全局事务回滚；  
3.可以通过 OrderController 的断点 debug 观察 seata, seata_order, seata_stock 3 个数据库中数据的变化情况；  
4.其它有关 seata 使用介绍参见印象笔记： Spring Cloud - Seata.  

# 延伸阅读

