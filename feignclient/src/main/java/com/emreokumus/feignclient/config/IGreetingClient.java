package com.emreokumus.feignclient.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="spring-cloud-eureka-client")//Bu isimle eureka server'a register olan uygulamaya istek atacaktır.
//@FeignClient(name="spring-cloud-eureka-client",fallback = FeignClientCallback.class)
// Bu yöntemi kullanırsak FeignClientCallback sınıfı aktif olmalı ve Controller'da ki @HystrixCommand ve metod olmamalı.
public interface IGreetingClient {
    @RequestMapping(method = RequestMethod.GET,value = "/greeting/{name}")
    String greeting(@PathVariable("name") String name);
}
