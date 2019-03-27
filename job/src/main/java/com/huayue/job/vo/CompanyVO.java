package com.huayue.job.vo;

import com.huayue.job.entity.Company;
import com.huayue.job.entity.Industry;
import com.huayue.job.entity.Job;
import lombok.Data;

import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/27.
 * @description
 */
@Data
public class CompanyVO {
    Company company;
    List<Job> jobs;
    Industry industry;

    public CompanyVO() {
    }

    public CompanyVO(Company company, List<Job> jobs, Industry industry) {
        this.company = company;
        this.jobs = jobs;
        this.industry = industry;
    }
}
