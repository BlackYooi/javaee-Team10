package com.kindblack.team10.controller;

import com.kindblack.team10.POJO.Permission.UserInfo;
import com.kindblack.team10.Utils.ConstantUtils;
import com.kindblack.team10.Utils.Msg;
import com.kindblack.team10.Utils.PermissionCode;
import com.kindblack.team10.service.URPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author black
 * @date 2019/12/28 - 14:28
 */

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    PermissionCode permissionCode;
    @Autowired
    URPService urpService;

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping()
    public String loginCheck(@RequestParam String userName, @RequestParam String password, HttpSession session, Model model) {
        UserInfo userInfo1 = urpService.loginCheck(userName, password);
        if (userInfo1 != null) {
            session.setAttribute(ConstantUtils.USER_SESSION_KEY, userInfo1);
            session.setMaxInactiveInterval(15 * 60);
            return "redirect:/sysmain";
        } else {
            model.addAttribute("msg", "账号或密码错误");
            return "login";
        }
    }

    @ResponseBody
    @GetMapping("/view")
    public Msg getView(HttpSession session) {
        return Msg.success()
                .add("viewLOs", urpService.getViewFr(session))
                .add("viewLTs", urpService.getViewSe(session));
    }

    @GetMapping("/out")
    public String loginOut(HttpSession session){
        session.setAttribute(ConstantUtils.USER_SESSION_KEY,null);
        return "login";
    }
}
