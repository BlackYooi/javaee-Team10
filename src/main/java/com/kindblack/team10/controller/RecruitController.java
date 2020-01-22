package com.kindblack.team10.controller;

import com.kindblack.team10.POJO.Permission.UserInfo;
import com.kindblack.team10.POJO.Recruit;
import com.kindblack.team10.POJO.Resume;
import com.kindblack.team10.Utils.ConstantUtils;
import com.kindblack.team10.Utils.Msg;
import com.kindblack.team10.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author black
 * @date 2019/12/30 - 9:10
 */
@Controller
@RequestMapping
public class RecruitController {
    @Autowired
    RecruitService recruitService;

    /*根据不同用户返回列表*/
    @ResponseBody
    @GetMapping("/putList")
    public Msg getRecruitList(HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute(ConstantUtils.USER_SESSION_KEY);
        if ("系统管理员".equals(userInfo.getRoleName())) {
            List<Recruit> byPubId = recruitService.findByPubId(userInfo.getUserName());
            return Msg.success().add("recruitLists", byPubId);
        } else {
            return Msg.success().add("recruitLists", recruitService.findAllRec());
        }
    }

    /*发布招聘信息*/
    @ResponseBody
    @PostMapping("/putList")
    public Msg addRecruit(@RequestBody Recruit recruit, HttpSession session) {
        String[] strings = recruitService.addRecruit(recruit, session);
        return Msg.success().add("msg", "1")
                .add("uuid", strings[0])
                .add("publisherId", strings[1]);
    }

    @ResponseBody
    @DeleteMapping("/putList")
    public Msg delRecruit(@RequestParam String id) {
        recruitService.delRecruit(id);
        return Msg.success().add("msg", "1");
    }

    //投递简历
    @ResponseBody
    @PostMapping("/putList/addResume")
    public Msg handResume(HttpSession session, @RequestParam String recruitId, @RequestBody Resume resume) {
        if (recruitService.handResume(session, recruitId, resume)) {
            return Msg.success().add("msg", "1");
        }
        return Msg.fail().add("msg", "0");
    }

    //查询按钮
    @GetMapping("/putList/serchResumes")
    public String getOneRecuitsResume(HttpSession session, @RequestParam String recruitId) {
        session.setAttribute(ConstantUtils.I_WANT_GET_RESUMES_KEY, recruitId);
        session.setMaxInactiveInterval(15 * 60);
        return "redirect:/oneRecuitsResume";
    }

    //不同身份查询发布列表，返回不同视图
    @GetMapping("/recruit/list")
    public String getRecruits(HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute(ConstantUtils.USER_SESSION_KEY);
        if ("系统管理员".equals(userInfo.getRoleName())) {
            return "recruit/recruitList";
        } else {
            return "recruit/recruitListForNolmal";
        }
    }

}
