package com.huayue.job.vo;

import com.huayue.job.entity.Company;
import com.huayue.job.entity.Industry;
import com.huayue.job.entity.Job;
import com.huayue.job.entity.JobType;
import lombok.Data;

import java.io.Serializable;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/26.
 * @description 用于岗位信息展示，公司信息为附带。
 */
@Data
public class JobVO implements Serializable {
    Job job;
    Company company;
    JobType jobType;
    Industry industry;
    public JobVO() {

    }

    public JobVO(Job job, Company company, JobType jobType, Industry industry) {
        this.job = job;
        this.company = company;
        this.jobType = jobType;
        this.industry = industry;
    }
}
