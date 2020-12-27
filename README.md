# self-rpc

为了学习 rpc 框架，手动搭建一个 rpc 框架

打算分6个阶段
* 第一阶段
    1. 基于 socket 实现简单的 rpc
* 第二阶段（基于第一阶段）
    1. 使用 ConcurrentHashMap 实现注册中心，提供注册服务
    2. 生产者向注册中心注册服务，提供服务
    3. 消费者向增加本地存根，向注册中心获取服务，消费服务
* 第三阶段（基于第二阶段）
    1. nio 替换 socket
* 第四阶段
    1. 实现服务的压缩和序列化
    2. 实现负载均衡
* 第五阶段
    1. 使用 netty 实现通信
    2. 使用 zookeeper 作为注册中心
* 第六阶段，完善
