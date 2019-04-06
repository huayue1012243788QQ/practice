package com.huayue.resume.service;

import com.huayue.common.exception.NotFoundException;
import com.huayue.common.exception.RepeatException;
import com.huayue.common.global.Result;
import com.huayue.common.repository.BaseRepository;
import com.huayue.common.service.BaseService;
import com.huayue.common.util.BeanUtil;
import com.huayue.resume.client.UserServiceClient;
import com.huayue.resume.entity.PersonInfo;
import com.huayue.resume.repository.PersonInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/14.
 */
@Service
public class PersonInfoService extends BaseService<PersonInfo> {
    @Autowired
    private PersonInfoRepository personInfoRepository;
    @Autowired
    private UserServiceClient userServiceClient;
    @Override
    public BaseRepository<PersonInfo> getRepository() {
        return personInfoRepository;
    }
    public PersonInfo save(PersonInfo personInfo) {
        Result result = userServiceClient.getOne(personInfo.getUserId());
        if (result.getCode() != Result.SUCCESS) {
            throw new NotFoundException(personInfo.getUserId());
        }
        if (personInfoRepository.existsByUserId(personInfo.getUserId())) {
            throw new RepeatException();
        }
        return personInfoRepository.saveAndFlush(personInfo);
    }
    public PersonInfo update(PersonInfo personInfo) {
        if (!personInfoRepository.existsById(personInfo.getId())) {
            throw new NotFoundException(personInfo.getId());
        }
        PersonInfo personInfo1 = personInfoRepository.findById(personInfo.getId()).get();
        BeanUtil.copyNonNullProperties(personInfo,personInfo1);
        return personInfoRepository.saveAndFlush(personInfo1);
    }
    public PersonInfo getByUserId(String id) {
        if (userServiceClient.getOne(id).getCode() != 200) {
            throw new NotFoundException(id);
        }
        if (!personInfoRepository.existsByUserId(id)) {
            throw new NotFoundException("user:" + id + "未完善个人信息");
        }
        List<PersonInfo> personInfos = personInfoRepository.findByUserId(id);
        if (personInfos.size() == 0) {
            return null;
        } else {
            return personInfos.get(0);
        }
    }
}
