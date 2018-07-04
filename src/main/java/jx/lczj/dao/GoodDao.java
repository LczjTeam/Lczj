package jx.lczj.dao;

import jx.lczj.model.T_attachment;
import jx.lczj.model.T_goods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface GoodDao {

    @Select("select * from T_goods")
    List<T_goods> loadlist();

    @Insert("insert into T_ATTACHMENT (ATTACHMENT,NAME,PATH,TYPE) VALUES (#{0} ,#{1} ,#{2} ,#{3} )")
    boolean addAttach(String code, String fileName, String fileName1, String p);

    @Delete("delete from T_GOODSATTACHMENT  WHERE ATTACHMENT = #{0}")
    boolean deleteAttachDiv(String code);

    @Delete("delete from T_ATTACHMENT  WHERE ATTACHMENT = #{0}")
    boolean deleteAttach(String code);

    @Insert("insert into  T_GOODSCOLOR (COLOR,GOODS) VALUES (#{0},#{1})")
    boolean addColorDiv(int  color, String goods);

    @Insert("insert into  T_SUITABLEAGE (AGESECTION,GOODS) VALUES (#{0},#{1})")
    boolean addAgesectionDiv(int AGESECTION, String goods);

    @Insert("insert into  T_SUITABLEFACE (FACE,GOODS) VALUES (#{0},#{1})")
    boolean addFaceDiv(int FACE, String goods);

    @Insert("insert into  T_SUITABLEOCCASION (OCCASION,GOODS) VALUES (#{0},#{1})")
    boolean addOccasionDiv(int occasion, String goods);

    @Insert("insert into  T_GOODSATTACHMENT (ATTACHMENT,GOODS,SN) VALUES (#{0},#{1},#{2})")
    boolean addAttachmentDiv(String s, String goods, int i);

    @Insert("insert into  T_GOODS(GOODS,CATEGORY,BRAND,NAME,MODELS,WIDTH,HEIGHT,SPACE,LENGTH,MAX_WIDTH,SUITABLE_SEX,PRICE) VALUES (#{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7},#{8},#{9},#{10},#{11})")
    boolean add(String goods, int category, int brand, String name, String models, int width, int height,int space, int length, int max_width, int suitable_sex, int price);

    @Select("select * from T_GOODS WHERE GOODS = #{0}")
    T_goods loadById(String goods);


    @Select("select * from T_ATTACHMENT WHERE ATTACHMENT IN ( SELECT ATTACHMENT FROM T_GOODSATTACHMENT WHERE  GOODS = #{0} )")
    List<T_attachment> loadAttachmentByGood(String goods);

    @Delete("delete from  T_GOODSCOLOR where GOODS = #{0}")
    boolean deleteColorDiv(String goods);

    @Delete("delete from  T_SUITABLEAGE where GOODS = #{0}")
    boolean deleteAgeDiv(String goods);

    @Delete("delete from  T_SUITABLEFACE where GOODS = #{0}")
    boolean deleteFaceDiv(String goods);


    @Delete("delete from  T_SUITABLEOCCASION where GOODS = #{0}")
    boolean deleteOccasionDiv(String goods);

    @Delete("delete from  T_GOODS where GOODS = #{0}")
    boolean delete(String code);


    @Update("update T_GOODS set CATEGORY = #{1} ,BRAND  = #{2} ,NAME  = #{3}  ,MODELS  = #{4}  ,WIDTH = #{5} ,HEIGHT  = #{6}  ,SPACE  = #{7}  ,LENGTH  = #{8}  ,MAX_WIDTH  = #{9}  ,SUITABLE_SEX  = #{10}  ,PRICE  = #{11} where goods = #{0}")
    boolean update(String goods, int category, int brand, String name, String models, int width, int height, int space, int length, int max_width, int suitable_sex, int price);
}
