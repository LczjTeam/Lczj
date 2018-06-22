package jx.lczj.anotation;

import java.lang.annotation.*;

/**
 * Created by 14260 on 2018/6/21.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogin {
    String value()  default "";
}