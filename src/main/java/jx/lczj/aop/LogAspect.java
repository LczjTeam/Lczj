package jx.lczj.aop;


import org.aspectj.lang.annotation.Around;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;



import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.ProceedingJoinPoint;



import org.aspectj.lang.annotation.AfterReturning;

import org.aspectj.lang.annotation.AfterThrowing;

import org.aspectj.lang.annotation.Around;

import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Before;

import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class LogAspect {

    // 任何通知方法都可以将第一个参数定义为 org.aspectj.lang.JoinPoint类型

    //@Pointcut("execution(* jx.lczj.controller..*.*(..))")
    @Pointcut("@annotation(jx.lczj.anotation.SysLogin)")
    private void controllerMethod(){}//定义一个切入点


    @Before("controllerMethod()")
    public void doBefore(JoinPoint call){

        String clazz =call.getTarget().getClass().getName();

        // 获取目标对象上正在执行的方法名

        String methodName =call.getSignature().getName();

         System.out.println("前置通知:" + clazz + "类的" + methodName + "方法开始了...");



    }





    @AfterReturning("controllerMethod()")
    public void doAfter(){

        System.out.println("-------------执行结束----------------");

    }



    @AfterThrowing("controllerMethod()")

    public void doAfterThrow(){

        System.out.println("例外通知");

    }



    @Around("controllerMethod()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{

        System.out.println("-------------执行开始----------------");

        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        HttpSession session =request.getSession();

        if(session.getAttribute("admin")==null){
            return  false;
        }

        Object object = pjp.proceed();//执行该方法

        System.out.println("-------------执行成功----------------");

        return object;

    }
}
