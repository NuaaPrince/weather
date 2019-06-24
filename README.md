# weather
基于SpringCloud的天气预报，练手项目

### 环境及版本说明
JDK 1.8+  
Gradle 4+  
SpringBoot 2.1.3.RELEASE  
SpringCloud Greenwich.SR1  
httpclient 4.5.7

### 模块说明
weather-city-server  提供城市清单的微服务  
weather-collection-server  将城市清单的天气数据存储到Redis的微服务  
weather-data-server   查询Redis存储的天气数据微服务  
weather-eureka-server  注册中心  
weather-zuul-server  city和data两个微服务的网关服务  
weather-report-server   提供界面查询天气预报的微服务  

