package jx.lczj.dao;

import jx.lczj.model.T_mywear;
import jx.lczj.model.T_wearglass;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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
    @Insert("insert into T_MYWEAR(MYWEAR,OCCASION,CUSTOMER,COLOR,FACE,GOODS,PUPIL,SEX,SELFPHOTO,SHOWPHOTO,DEAL,ISCART,AGE) VALUES( #{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7},#{8},#{9},#{10},#{11},#{12} )")
    boolean add(String mywear, int occasion, String customer, int color, int face, String goods, int pupil, String sex, String selfphoto, String showphoto, int deal, int iscart, int age);


    /**
     * 添加双眼镜片信息
     * @param mywear
     * @param eyeglass
     * @param degress
     * @param asdegress
     * @param axis
     * @param sign
     * @return
     */
    @Insert("insert into T_WEARGLASS (MYWEAR,EYEGLASS,DEGRESS,ASDEGRESS,AXIS,SIGN,WEARGLASS) VALUES( #{0},#{1},#{2},#{3},#{4},#{5},#{6})")
    boolean addEyeglass(String mywear, String eyeglass, Integer degress, Integer asdegress, float axis, String sign,int wearglass);

    /**
     * 获取左、右眼信息
     * @param mywear
     * @param SIGN
     * @return
     */
    @Select("select * from T_WEARGLASS WHERE  MYWEAR = #{0} and SIGN = #{1}")
    T_wearglass loadWearglassByMywear(String mywear,String SIGN);

    /**
     * 通过会员编号获取会员的是试戴信息
     * @param customer
     * @return
     */
    @Select("select * from T_MYWEAR  WHERE CUSTOMER = #{0}  AND  \"order\"  is null ")
    List<T_mywear> listByCustomer(String customer);

    /**
     * 通过试戴编号获取试戴信息
     * @param mywear
     * @return
     */
    @Select("select * from T_MYWEAR  WHERE MYWEAR  = #{0}")
    T_mywear loadById(String mywear);

    /**
     * 通过试戴编号删除左右眼镜片信息
     * @param mywear
     * @return
     */
    @Delete("DELETE FROM T_WEARGLASS  WHERE MYWEAR  = #{0}")
    boolean deleteWearglassByMywear(String mywear);


    /**
     * 通过试戴编号删除试戴信息
     * @param mywear
     * @return
     */
    @Delete("DELETE FROM T_MYWEAR  WHERE MYWEAR  = #{0}")
    boolean delete(String mywear);

    /**
     * 修改颜色
     * @param mywear
     * @param color
     * @return
     */
    @Update("UPDATE T_MYWEAR SET COLOR = #{1} WHERE MYWEAR = #{0}")
    boolean updateColor(String mywear,int color);

    /**
     * 修改镜片参数
     * @param mywear
     * @param degress
     * @param asdegress
     * @param axis
     * @param sign
     * @return
     */
    @Update("UPDATE T_WEARGLASS SET DEGRESS = #{1},ASDEGRESS = #{2},AXIS = #{3} ,WEARGLASS = #{4}  WHERE MYWEAR = #{0} AND SIGN = #{4}")
    boolean updateEyeglass(String mywear, int degress, int asdegress, float axis, String sign ,int wearglass);


    @Update("UPDATE T_MYWEAR SET \"order\" = #{0} WHERE MYWEAR = #{1} ")
    boolean updateOrder(String order, String mywear);
}
