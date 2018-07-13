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
    /**
     * 添加镜片信息
     * @param eyeglass
     * @param category
     * @param efficacy
     * @param brand
     * @param mask
     * @param style
     * @param refraction
     * @param price
     * @param name
     * @return
     */
    @Insert("insert into T_EYEGLASS(EYEGLASS,CATEGORY,EFFICACY,BRAND,MASK,STYLE,REFRACTION,PRICE,NAME) values(#{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7},#{8})")
    boolean add(String eyeglass,int category,int efficacy,int brand,int mask,int style,float refraction,int price,String name);

    /**
     * 添加镜片附件信息
     * @param eyeglass
     * @param s
     * @param i
     * @return
     */
    @Insert("insert into T_EYEGLASSATTACHMENT(EYEGLASS,ATTACHMENT,SN) values(#{0},#{1},#{2})")
    boolean addAttachmentDiv(String eyeglass,String s,  int i);

    /**
     * 根据Id获取镜片信息
     * @param eyeglass
     * @return
     */
    @Select("select * from T_EYEGLASS where EYEGLASS = #{0}")
    T_eyeglass loadById(String eyeglass);

    /**
     * 根据镜片Id来获取附件
     * @param eyeglass
     * @return
     */
    @Select("select * from T_ATTACHMENT WHERE ATTACHMENT IN ( SELECT ATTACHMENT FROM T_EYEGLASSATTACHMENT WHERE EYEGLASS  = #{0} )")
    List<T_attachment> loadAttachmentByEyeglass(String eyeglass);

    /**
     * 获取全部eyeglass
     * @return
     */
    @Select("select * from T_EYEGLASS ")
    List<T_eyeglass> loadlist();

    /**
     * 根据镜片Id删除附件
     * @param EYEGLASS
     * @return
     */
    @Delete("delete from T_EYEGLASSATTACHMENT where EYEGLASS = #{0}")
    boolean deleteAttachDiv(String EYEGLASS);


    /**
     * 根据附件Id删除附件
     * @param ATTACHMENT
     * @return
     */
    @Delete("delete from T_EYEGLASSATTACHMENT where ATTACHMENT = #{0}")
    boolean deleteAttachDivByAttachment(String ATTACHMENT);

    /**
     * 更新镜片信息
     * @param eyeglass
     * @param category
     * @param efficacy
     * @param brand
     * @param mask
     * @param style
     * @param refraction
     * @param price
     * @param name
     * @return
     */
    @Update("update  T_EYEGLASS set CATEGORY = #{1},EFFICACY = #{2},BRAND = #{3},MASK = #{4},STYLE = #{5} ,REFRACTION= #{6} ,PRICE = #{7} ,NAME = #{8} where EYEGLASS = #{0}")
    boolean update(String eyeglass, int category, int efficacy, int brand, int mask, int style, float refraction, int price, String name);

    /**
     * 根据Id删除镜片信息
     * @param code
     * @return
     */
    @Delete("delete from T_EYEGLASS where EYEGLASS = #{0}")
    boolean delete(String code);

    /**
     * 删除附件
     * @param code
     * @return
     */
    @Delete("delete from T_ATTACHMENT  WHERE ATTACHMENT = #{0}")
    boolean deleteAttach(String code);

}
