package com.huayue.adminservice.client;

import com.huayue.common.global.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/22.
 * @description
 */
@FeignClient(value = "job-service")
public interface JobServiceClient {
    @RequestMapping(value = "/job/{id}",method = RequestMethod.PATCH)
    Result checkJob(@PathVariable String id);
    @GetMapping("/job/{id}")
    Result getJOb(@PathVariable String id);
    @GetMapping("/company/{id}")
    Result getCompany(@PathVariable String id);
    @PatchMapping("/company/{id}")
    Result checkCompany(@PathVariable String id);
}
