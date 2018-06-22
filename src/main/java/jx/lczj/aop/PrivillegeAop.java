package jx.lczj.aop;

import jx.lczj.anotation.Privilege;
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
           // 获取目标对象上正在执行的方法名
           String methodName = call.getSignature().getName();
           //获取所需权限
           String menu_name = PrivillegeAop.parse(call.getTarget().getClass(), methodName);
           HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
           HttpSession session =request.getSession();
           if(session.getAttribute("admin")==null){
               System.out.println("你尚未登录...");
               throw new RuntimeException("你尚未登录");
           }else if( !menu_name.equals("")){
               System.out.println(menu_name+"权限检测中...");
               //该方法需要权限制执行
               boolean ok = false;
               AdminVo avo =  (AdminVo)session.getAttribute("admin");
               for (MenuVo mv: avo.getMenuVos()) {
                   for (T_menu m : mv.getMenus()){
                       if(m.getTitle().equals(menu_name)){
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
                   throw new RuntimeException("权限不足！！");
               }
           }
           System.out.println("拥有"+menu_name+"权限...");
           System.out.println(clazz + "类的" + methodName + "方法开始了...");


       }catch (Exception e){
           throw new RuntimeException(e.getMessage());
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


    /**
     * 解析注解
     * @param targetClassName 目标类（Class形式）
     * @param methodName 目标方法（在客户端调用哪个方法，methodName就代表哪个方法）
     * @return
     * @throws Exception
     */
    public static String parse(Class targetClassName,String methodName) throws Exception{
        //获得目标方法
        Method method = targetClassName.getMethod(methodName);

        String methodAccess = "";
        //判断目标方法上面是否存在@PrivilegeInfo注解
        //@Privilege（name="savePerson"）
        if(method.isAnnotationPresent(Privilege.class)){
            //得到方法上的注解
            Privilege privilege = method.getAnnotation(Privilege.class);
            //得到注解中的name值
            methodAccess = privilege.value();
        }
        return methodAccess;
    }
}