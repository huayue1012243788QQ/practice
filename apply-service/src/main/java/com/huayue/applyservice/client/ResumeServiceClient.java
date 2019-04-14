package com.huayue.applyservice.client;

import com.huayue.common.global.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/20.
 * @description
 */
@FeignClient(value = "resume-service")
public interface ResumeServiceClient {
    @GetMapping(value = "/{id}")
    Result getOne(@PathVariable String id);
    @GetMapping(value = "/getResumeVOByResumeId")
    Result getVOByResumeId(@RequestParam String resumeId);
}
