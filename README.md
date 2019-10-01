# springcloud-api-services
springcloud 统一管理多个api 。 api的 appkey 的申请 ，api请求验证，api 频率限制等
open-videos  是一个在采集库中查找电影资源的api 
myvideos-web 是一个利用 open-videos 做成的 第三方 电影网站

## 接入 open-videos
 - 1.向网关 申请appkey ->申请token（可设置是否过期 ) 不过期的token 再下一次请求token时 将会更新。为过期的token ，则下一次请求，如果没失效的话，会再次被返回
 - 2.同时附上参数appkey=xxx& token=& 请求网关 路径/api/apiname/uri  将会在判断 token 和 appkey 有效 和请求未过速后  被路由到 指定api/uri
 
