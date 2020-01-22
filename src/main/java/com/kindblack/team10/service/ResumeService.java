package com.kindblack.team10.service;

import com.kindblack.team10.POJO.Recruit;
import com.kindblack.team10.POJO.Resume;
import com.kindblack.team10.dao.DaoInter.RecruitInter;
import com.kindblack.team10.dao.DaoInter.ResumeInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author black
 * @date 2019/12/30 - 10:23
 */

@Service
public class ResumeService {
    @Autowired
    ResumeInter resumeInter;
    @Autowired
    RecruitInter recruitInter;

    /*查找出简历发起者的简历*/
    public List<Resume> findByDeliverId(String deliverId) {
        List<Resume> byDeliverId = resumeInter.findByDeliverId(deliverId);
        return byDeliverId;
    }

    /*通过招聘id查出简历*/
    public List<Resume> findByRecruitId(String id) {
        List<Recruit> allById = recruitInter.findAllById(Collections.singleton(id));
        if (allById != null && !allById.isEmpty()) {
            List<Resume> resumes = allById.get(0).getResumes();
            return resumes;
        }
        return null;
    }

    /*简历发起者删除发布的简历*/
    public boolean delMyResume(String id) {
        resumeInter.deleteById(id);
        return true;
    }

}
