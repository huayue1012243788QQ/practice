package com.huayue.resume.client;

import com.huayue.common.global.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/15.
 * @description
 */
@FeignClient(value = "user-service")
public interface UserServiceClient {
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    Result getOne(@PathVariable("id") String id);
    @GetMapping(value = "/user")
    Object getAll();
}
