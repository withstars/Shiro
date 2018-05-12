package cn.withstars.controller;


import cn.withstars.domain.User;
import cn.withstars.service.impl.UserServiceImpl;
import cn.withstars.util.PasswordUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class HelloController {

    private static final Logger logger=Logger.getLogger(HelloController.class);

    @Autowired
    public UserServiceImpl userService;

    /**
     * 首页
     * @return
     */
    @RequestMapping(value ={"/","/index"} )
    public String index(){
        return "index";
    }

    /**
     * 个人主页
     * @return
     */
    @RequestMapping(value ={"/me"} )
    public String personal(){
        return "personal";
    }

    /**
     * 登录页
     * @return
     */
    @RequestMapping(value ="/login" )
    public String login(){
        return "login";
    }

    /**
     * 注册页面
     * @return
     */
    @RequestMapping(value ="/signup" )
    public String signup(){
        return "signup";
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "login";
    }
    /**
     * 注册处理
     * @param request
     * @param model
     * @param redirect
     * @return
     */
    @RequestMapping(value = "/signup/do",method = RequestMethod.POST)
    public String signupDo(HttpServletRequest request,Model model,RedirectAttributes redirect){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        boolean existUser=userService.existUser(user);
        if (existUser){
            redirect.addFlashAttribute("error","用户名已存在!");
            return "redirect:/login";
        }
        // 加密处理
        String salt1 = username;
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        String encodePassword = PasswordUtil.encodePasswd(salt1,salt2,password);
        // 添加用户
        user.setPassword(encodePassword);
        user.setCreateTime(new Date());
        user.setSalt2(salt2);
        boolean ifAddSucc=userService.addUser(user);
        if (ifAddSucc){
            redirect.addFlashAttribute("info","注册成功!");
        }else {
            redirect.addFlashAttribute("error","注册失败!");
        }
        return "redirect:/login";
    }

    /**
     * 登录验证
     * @param userName
     * @param passwd
     * @param rememberMe
     * @param model
     * @return
     */
    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
    public String loginCheck(String userName, String passwd, String rememberMe, Model model, HttpServletRequest request) {
        // 密码处理
        User user = new User();
        user.setUsername(userName);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(userName, passwd);
        if (rememberMe != null){
            token.setRememberMe(true);
        }
        try {
            subject.login(token);
            if (subject.isAuthenticated()){
                request.getSession().setAttribute("user",user);
                SavedRequest savedRequest = WebUtils.getSavedRequest(request);
                if (savedRequest == null || savedRequest.getRequestUrl() == null){
                    return "index";
                }else {
                    return "redirect:"+savedRequest.getRequestUrl();
                }
            }else {
                return "redirect:/login";
            }
        } catch (UnknownAccountException e) {
            model.addAttribute("error", "用户名不存在");
        }
        catch (IncorrectCredentialsException e) {
            model.addAttribute("error", "密码错误");
        }catch (ExcessiveAttemptsException e){
            model.addAttribute("error","登录失败次数过多,请稍后尝试");
        }
        catch (LockedAccountException e){
            model.addAttribute("error", "您的账户已被禁用");
        }

        return "login";

    }

}
