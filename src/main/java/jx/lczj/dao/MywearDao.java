package jx.lczj.dao;

import org.apache.ibatis.annotations.Insert;

/**
 * Created by 14260 on 2018/7/9.
 */
public interface  MywearDao {

    /**
     * 添加试戴基本信息
     * @param mywear
     * @param occasion
     * @param customer
     * @param color
     * @param face
     * @param goods
     * @param pupil
     * @param sex
     * @param selfphoto
     * @param showphoto
     * @param deal
     * @param iscart
     * @return
     */
    @Insert("insert into T_MYWEAR(MYWEAR,OCCASION,CUSTOMER,COLOR,FACE,GOODS,PUPIL,SEX,SELFPHOTO,SHOWPHOTO,DEAL,ISCART) VALUES( #{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7},#{8},#{9},#{10},#{11} )")
    boolean add(String mywear, int occasion, String customer, int color, int face, String goods, int pupil, String sex, String selfphoto, String showphoto, int deal, int iscart);


    @Insert("insert into T_WEARGLASS (MYWEAR,EYEGLASS,DEGRESS,ASDEGRESS,AXIS,SIGN) VALUES( #{0},#{1},#{2},#{3},#{4},#{5})")
    boolean addEyeglass(String mywear, String eyeglass, Integer degress, Integer asdegress, Integer axis, String sign);
}
