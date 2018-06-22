package jx.lczj.anotation;

import java.lang.annotation.*;

/**
 * Created by 14260 on 2018/6/22.
 */


@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MenuOperation {
    String value() default  "菜单管理操作求权限";
}
