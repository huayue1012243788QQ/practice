package com.huayue.resume.vo;

import com.huayue.resume.entity.*;
import lombok.Data;
import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/15.
 */
@Data
public class ResumeVO {
    private PersonInfo personInfo;
    private Resume resume;
    private List<Skill> skills;
    private List<Education> educations;
    private List<Experience> experiences;
    public ResumeVO(PersonInfo personInfo, Resume resume, List<Skill> skills, List<Education> educations, List<Experience> experiences) {
        this.personInfo = personInfo;
        this.resume = resume;
        this.skills = skills;
        this.educations = educations;
        this.experiences = experiences;
    }
}
