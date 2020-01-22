package com.kindblack.team10.service;

import com.kindblack.team10.POJO.Permission.UserInfo;
import com.kindblack.team10.POJO.Recruit;
import com.kindblack.team10.POJO.Resume;
import com.kindblack.team10.Utils.ConstantUtils;
import com.kindblack.team10.dao.DaoInter.RecruitInter;
import com.kindblack.team10.dao.DaoInter.ResumeInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class RecruitService {
    @Autowired
    RecruitInter recruitInter;
    @Autowired
    ResumeInter resumeInter;

    /*发布招聘信息*/
    public String[] addRecruit(Recruit recruit, HttpSession session) {
        recruit.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        UserInfo userInfo = (UserInfo) session.getAttribute(ConstantUtils.USER_SESSION_KEY);
        recruit.setPublisherId(userInfo.getUserName());
        String[] strings = new String[2];
        strings[0] = recruit.getId();
        strings[1] = recruit.getPublisherId();
        recruitInter.save(recruit);
        return strings;
    }

    /*删除招聘信息*/
    public boolean delRecruit(String id) {
        recruitInter.deleteById(id);
        return true;
    }

    /*招聘信息->列表(通过管理员id)*/
    public List<Recruit> findByPubId(String id) {
        List<Recruit> byPublisherId = recruitInter.findByPublisherId(id);
        return byPublisherId;
    }

    /*招聘信息->列表（所有管理员发布的）*/
    public List<Recruit> findAllRec() {
        return recruitInter.findAll();
    }

    /*投简历功能*/
    public boolean handResume(HttpSession session, String recruitId, Resume resume) {
        List<Recruit> allById = recruitInter.findAllById(Collections.singleton(recruitId));
        if (allById != null && (!allById.isEmpty())) {
            Recruit recruit = allById.get(0);
            UserInfo userInfo = (UserInfo) session.getAttribute(ConstantUtils.USER_SESSION_KEY);
            resume.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            resume.setRecruit(recruit);
            recruit.addResume(resume);
            recruitInter.save(recruit);
            resumeInter.save(resume);
            return true;
        }
        return false;
    }
}
