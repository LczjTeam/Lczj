package jx.lczj.aop;


import jx.lczj.model.T_menu;
import jx.lczj.viewmodel.AdminVo;
import jx.lczj.viewmodel.MenuVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Aspect
@Component
public class UserAspect {

    // 任何通知方法都可以将第一个参数定义为 org.aspectj.lang.JoinPoint类型
    //@Pointcut("execution(* jx.lczj.controller..*.*(..))")
    @Pointcut("@annotation(jx.lczj.anotation.UserOperation)")
    private void controllerMethod(){}//定义一个切入点

    @Before("controllerMethod()")
    public void doBefore(JoinPoint call){
        String clazz =call.getTarget().getClass().getName();
        // 获取目标对象上正在执行的方法名
        String methodName =call.getSignature().getName();
         System.out.println(clazz + "类的" + methodName + "方法开始了...");
    }

    @AfterReturning("controllerMethod()")
    public void doAfter(){
        System.out.println("结束....");

    }

    @AfterThrowing("controllerMethod()")
    public void doAfterThrow(){
        System.out.println("出错啦....");
    }

    @Around("controllerMethod()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("开始....");
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session =request.getSession();
        if(session.getAttribute("admin")==null){
            System.out.println("你尚未登录....");
            return "你尚未登录";
        }else{
            boolean ok = false;
            AdminVo avo =  (AdminVo)session.getAttribute("admin");
            for (MenuVo mv: avo.getMenuVos()) {
                for (T_menu m : mv.getMenus()){
                    if(m.getTitle().equals("用户管理")){
                        ok = true;
                        break;
                    }
                }
                if(ok){
                    break;
                }
            }
            if(!ok){
                System.out.println("权限不足....");
                return "权限不足！！";
            }
            Object object = pjp.proceed();//执行该方法
            System.out.println("执行成功....");
            return object;
        }

    }
}
