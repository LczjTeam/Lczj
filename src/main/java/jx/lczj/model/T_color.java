package jx.lczj.model;

/**
 * Created by 14260 on 2018/6/12.
 */

import java.io.Serializable;

/**
 * 眼镜颜色样式
 */
public class T_color  implements Serializable{

    private int color;   //颜色代码
    private String name; //颜色名称
    private String rgb;  //颜色rgb 颜色的rgb值，十六制，6位长度

}
