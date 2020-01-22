package com.kindblack.team10.controller;

import com.kindblack.team10.POJO.MyResumeFormwork;
import com.kindblack.team10.Utils.Msg;
import com.kindblack.team10.service.MyResumeFormworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/formwork")
public class ResumeFormworkController {
    @Autowired
    MyResumeFormworkService myResumeFormworkService;

    //返回我的模板
    @GetMapping
    public Msg getFormwork(HttpSession session) {
        MyResumeFormwork formwork = myResumeFormworkService.getFormwork(session);
        if (formwork != null) {
            return Msg.success()
                    .add("msg", "1")
                    .add("formwork", formwork);
        } else {
            return Msg.fail().add("msg", "0");
        }
    }

    //添加或更新我的模板
    @PostMapping
    public Msg addOrUpdate(HttpSession session, @RequestBody MyResumeFormwork myResumeFormwork) {
        myResumeFormworkService.addOrUpdate(myResumeFormwork, session);
        return Msg.success().add("msg", "1");
    }
}
