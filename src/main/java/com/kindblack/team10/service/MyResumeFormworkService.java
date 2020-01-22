package com.kindblack.team10.service;

import com.kindblack.team10.POJO.MyResumeFormwork;
import com.kindblack.team10.POJO.Permission.UserInfo;
import com.kindblack.team10.Utils.ConstantUtils;
import com.kindblack.team10.dao.DaoInter.MyResumeFormworkInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Service
public class MyResumeFormworkService {
    @Autowired
    MyResumeFormworkInter myResumeFormworkInter;

    //新建和更新模板
    public void addOrUpdate(MyResumeFormwork myResumeFormwork, HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute(ConstantUtils.USER_SESSION_KEY);
        myResumeFormwork.setDeliverId(userInfo.getUserName());
        myResumeFormworkInter.save(myResumeFormwork);
    }

    //查询模板,如果为空
    public MyResumeFormwork getFormwork(HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute(ConstantUtils.USER_SESSION_KEY);
        List<MyResumeFormwork> allById = myResumeFormworkInter
                .findAllById(Collections.singleton(userInfo.getUserName()));
        if (allById != null && !allById.isEmpty()) {
            return allById.get(0);
        } else {

        }
        return null;
    }
}
