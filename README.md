# AliOSS
阿里OSS上传服务，在阿里原有基础上进行封装的上传文件到阿里云OSS的项目，可根据自己的项目需求打包成jar独立使用使用的时候，将dfs.properties里面的参数替换成自己的就可以了！

项目归纳的一些东西：http://blog.csdn.net/Dream__Snow/article/details/78519226

做这个东西，主要对一些文件处理的基础和IO进行回顾和总结，夯实一下基础

阿里产品的套路总结：
  做完支付再来看这些东西，大部分套路都是一样的，总结下来大体的流程就是：前端发起需求到后端，后端组装参数（在阿里平台获取到的固定参数和随请求变化的参数）成request向阿里后台发起请求，获取到一个key(或者类似key)的一个密钥，然后返回给前端进行处理，在前端跟官方后台交互完后，官方后台可能会向我们注册的回调地址传递一个通知用于判断成功与否。工作重点一般就在于参数组装和请求这一块，多注意细心。
