package jx.lczj.aop;

import jx.lczj.anotation.Privilege;
import jx.lczj.model.T_menu;
import jx.lczj.viewmodel.AdminVo;
import jx.lczj.viewmodel.MenuVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * Created by 14260 on 2018/6/22.
 */

@Aspect
@Component
public class PrivillegeAop {

// 任何通知方法都可以将第一个参数定义为 org.aspectj.lang.JoinPoint类型

    @Pointcut("@annotation(jx.lczj.anotation.Privilege)")
    private void controllerMethod(){}//定义一个切入点

    @Before("controllerMethod()")
    public void doBefore(JoinPoint call){

        try {
            String clazz = call.getTarget().getClass().getName();
            System.out.println("clazz：" + clazz);

            // 获取目标对象上正在执行的方法名
            String methodName = call.getSignature().getName();
            System.out.println("methodName：" + methodName);

            //获取所需权限
            String menu_name = "";

            MethodSignature methodSignature = (MethodSignature) call.getSignature();
            Method method = methodSignature.getMethod();
            if (method.isAnnotationPresent(Privilege.class)) {
                //得到方法上的注解
                Privilege privilege = method.getAnnotation(Privilege.class);
                //得到注解中的name值
                menu_name = privilege.value();
            }
            System.out.println("权限名称：" + menu_name);

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            if (session.getAttribute("admin") == null) {
                System.out.println("你尚未登录...");
                throw new RuntimeException("你尚未登录");
            } else if (!menu_name.equals("")) {
                System.out.println(menu_name + "权限检测中...");
                //该方法需要权限制执行
                boolean ok = false;
                AdminVo avo = (AdminVo) session.getAttribute("admin");
                for (MenuVo mv : avo.getMenuVos()) {
                    for (T_menu m : mv.getMenus()) {
                        if (m.getTitle().equals(menu_name)) {
                            ok = true;
                            break;
                        }
                    }
                    if (ok) {
                        break;
                    }
                }
                if (!ok) {
                    System.out.println("权限不足....");
                    throw new RuntimeException("权限不足！！");
                }
            }
            System.out.println("拥有" + menu_name + "权限...");
            System.out.println(clazz + "类的" + methodName + "方法开始了...");

        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("方法不存在");
        }

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
        System.out.println("- - - - - - - - - - - - - - ");
        System.out.println("开始....");
        Object object = pjp.proceed();//执行该方法
        System.out.println("成功....");
        return object;
    }


}