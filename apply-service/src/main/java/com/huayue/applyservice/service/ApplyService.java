package com.huayue.applyservice.service;

import com.alibaba.fastjson.JSONObject;
import com.huayue.applyservice.client.JobServiceClient;
import com.huayue.applyservice.client.ResumeServiceClient;
import com.huayue.applyservice.entity.Apply;
import com.huayue.applyservice.repository.ApplyRepository;
import com.huayue.common.exception.NotFoundException;
import com.huayue.common.exception.RepeatException;
import com.huayue.common.global.Result;
import com.huayue.common.repository.BaseRepository;
import com.huayue.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/20.
 * @description
 */
@Service
public class ApplyService extends BaseService<Apply> {
    @Autowired
    private ApplyRepository applyRepository;
    @Autowired
    private JobServiceClient jobServiceClient;
    @Autowired
    private ResumeServiceClient resumeServiceClient;
    @Override
    public BaseRepository<Apply> getRepository() {
        return applyRepository;
    }
    public Apply save(Apply apply) {
        if (applyRepository.existsByJobIdAndResumeId(apply.getJobId(), apply.getResumeId())) {
            throw new RepeatException();
        }
        if (jobServiceClient.getOne(apply.getJobId()).getCode() != Result.SUCCESS) {
            throw new NotFoundException(apply.getJobId());
        }
        if (resumeServiceClient.getOne(apply.getResumeId()).getCode() != Result.SUCCESS) {
            throw new NotFoundException(apply.getResumeId());
        }
        return applyRepository.save(apply);
    }
    public List<JSONObject> getByJobList(List<String> jobIds) {
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (String id:
             jobIds) {
            if (applyRepository.findByJobId(id) != null) {
                Apply apply = applyRepository.findByJobId(id);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("jobVO",jobServiceClient.getOne(apply.getJobId()).getData());
                jsonObject.put("resumeVO",resumeServiceClient.getVOByResumeId(apply.getResumeId()).getData());
                jsonObjects.add(jsonObject);
            }
        }
        return jsonObjects;
    }
}
