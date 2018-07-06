package jx.lczj.dao;

import jx.lczj.model.T_attachment;
import jx.lczj.model.T_eyeglass;
import jx.lczj.viewmodel.EyeglassVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface EyeglassDao {

    @Insert("insert into T_EYEGLASS(EYEGLASS,CATEGORY,EFFICACY,BRAND,MASK,STYLE,REFRACTION,PRICE,NAME) values(#{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7},#{8})")
    boolean add(String eyeglass,int category,int efficacy,int brand,int mask,int style,float refraction,int price,String name);

    @Insert("insert into T_EYEGLASSATTACHMENT(EYEGLASS,ATTACHMENT,SN) values(#{0},#{1},#{2})")
    boolean addAttachmentDiv(String eyeglass,String s,  int i);

    @Select("select * from T_EYEGLASS where EYEGLASS = #{0}")
    T_eyeglass loadById(String eyeglass);

    @Select("select * from T_ATTACHMENT WHERE ATTACHMENT IN ( SELECT ATTACHMENT FROM T_EYEGLASSATTACHMENT WHERE EYEGLASS  = #{0} )")
    List<T_attachment> loadAttachmentByEyeglass(String eyeglass);

    /**
     * 获取全部eyeglass
     * @return
     */
    @Select("select * from T_EYEGLASS ")
    List<T_eyeglass> loadlist();

    @Delete("delete from T_EYEGLASSATTACHMENT where ATTACHMENT = #{0}")
    boolean deleteAttachDiv(String ATTACHMENT);


    @Update("update  T_EYEGLASS set CATEGORY = #{1},EFFICACY = #{2},BRAND = #{3},MASK = #{4},STYLE = #{5} ,REFRACTION= #{6} ,PRICE = #{7} ,NAME = #{8} where EYEGLASS = #{0}")
    boolean update(String eyeglass, int category, int efficacy, int brand, int mask, int style, float refraction, int price, String name);

    @Delete("delete from T_EYEGLASS where EYEGLASS = #{0}")
    boolean delete(String code);

    @Delete("delete from T_ATTACHMENT  WHERE ATTACHMENT = #{0}")
    boolean deleteAttach(String code);

}
