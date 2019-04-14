package com.huayue.job.vo;

import com.huayue.job.entity.Industry;
import lombok.Data;

import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/27.
 * @description
 */
@Data
public class IndustryVO {
    private Industry industry;
    List<Industry> industries;
    public IndustryVO() {
    }
    public IndustryVO(Industry industry, List<Industry> industries) {
        this.industry = industry;
        this.industries = industries;
    }
}
