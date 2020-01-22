package com.kindblack.team10.controller;

import com.kindblack.team10.POJO.Permission.UserInfo;
import com.kindblack.team10.Utils.ConstantUtils;
import com.kindblack.team10.Utils.Msg;
import com.kindblack.team10.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author black
 * @date 2020/1/1 - 14:20
 */

@RestController
@RequestMapping("/resume")
public class ResumeContrller {
    @Autowired
    ResumeService resumeService;

    @GetMapping
    public Msg getResumes(HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute(ConstantUtils.USER_SESSION_KEY);
        if ("普通用户".equals(userInfo.getRoleName())) {
            return Msg.success()
                    .add("role", "普通用户")
                    .add("resumes", resumeService.findByDeliverId(userInfo.getUserName()));
        } else {
            String id = (String) session.getAttribute(ConstantUtils.I_WANT_GET_RESUMES_KEY);
            System.out.println(id);
            return Msg.success()
                    .add("role", "系统管理员")
                    .add("resumes", resumeService.findByRecruitId(id));
        }
    }

    @DeleteMapping
    public Msg delResume(@RequestParam String id) {
        resumeService.delMyResume(id);
        return Msg.success().add("msg", "1");
    }


}
