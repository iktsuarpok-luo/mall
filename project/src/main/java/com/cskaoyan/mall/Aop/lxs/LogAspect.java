package com.cskaoyan.mall.Aop.lxs;
import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.lxs.lxsAdminTwo;
import com.cskaoyan.mall.service.lxs.AdminService;
import com.cskaoyan.mall.util.AnnotationLog;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;

@Component
@Aspect
public class LogAspect {
    @Autowired
    AdminService adminService;
    Log log = new Log();

    @Pointcut("@annotation(com.cskaoyan.mall.util.AnnotationLog)")
    public void pointcut() {
    }
    @Before("pointcut()")
    public void Before(JoinPoint point){
           Object[] args = point.getArgs();
        if(args.length>1){
            log.setResult(args[0].toString());
        }else {
            String string = args[0].toString();
            String[] split = string.split("'");
            log.setResult(split[1]);
        }
    }
    @AfterReturning(value = "pointcut()&&@annotation(alog)", returning = "a")
    public void myAfterReturning(Object a, AnnotationLog alog) throws UnknownHostException {
        Subject subject = SecurityUtils.getSubject();
        lxsAdminTwo user = (lxsAdminTwo) subject.getPrincipal();
        String username = user.getUsername();
        String action = alog.value();
        String address = InetAddress.getLocalHost().getHostAddress();
        Map b= (Map) a;
        String errmsg = (String)b.get("errmsg");
        int errno = (int) b.get("errno");
        log.setAdmin(username);
        log.setType(1);
        log.setAction(action);
        log.setAddTime(new Date());
        log.setUpdateTime(new Date());
        log.setIp(address);
        if ("成功".equals(errmsg)){
            log.setStatus(true);
        }else {
            log.setStatus(false);
        }
        if(errno!=0){
            log.setStatus(false);
        }else {
            log.setStatus(true);
        }
        adminService.logInsert(log);
        System.out.println("after-returing");
    }
    @AfterThrowing(value = "pointcut()",throwing = "a")
    public void MyAfterThrowing(Exception a){
        System.out.println("throwing");
    }


    @After("pointcut()")
    public void after(){

        System.out.println("after");
    }
}
