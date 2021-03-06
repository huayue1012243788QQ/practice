package com.huayue.applyservice.client;

import com.huayue.common.global.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/20.
 * @description
 */
@FeignClient(value = "job-service")
public interface JobServiceClient {
    @GetMapping(value = "/job/{id}")
    Result getOne(@PathVariable String id);
}
