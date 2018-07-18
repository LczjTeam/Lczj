package jx.lczj.dao;

import jx.lczj.model.T_attachment;
import jx.lczj.model.T_goods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface GoodDao {
    /**
     * 加载镜框信息
     * @return
     */
    @Select("select * from T_goods")
    List<T_goods> loadlist();

    /**
     * 添加附件
     * @param code
     * @param fileName
     * @param fileName1
     * @param p
     * @return
     */
    @Insert("insert into T_ATTACHMENT (ATTACHMENT,NAME,PATH,TYPE) VALUES (#{0} ,#{1} ,#{2} ,#{3} )")
    boolean addAttach(String code, String fileName, String fileName1, String p);

    /**
     * 删除附件分配
     * @param code
     * @return
     */
    @Delete("delete from T_GOODSATTACHMENT  WHERE ATTACHMENT = #{0}")
    boolean deleteAttachDiv(String code);

    /**
     * 删除附件
     * @param code
     * @return
     */
    @Delete("delete from T_ATTACHMENT  WHERE ATTACHMENT = #{0}")
    boolean deleteAttach(String code);

    /**
     * 添加颜色分配
     * @param color
     * @param goods
     * @return
     */
    @Insert("insert into  T_GOODSCOLOR (COLOR,GOODS) VALUES (#{0},#{1})")
    boolean addColorDiv(int color, String goods);

    /**
     * 添加年龄段分配
     * @param AGESECTION
     * @param goods
     * @return
     */
    @Insert("insert into  T_SUITABLEAGE (AGESECTION,GOODS) VALUES (#{0},#{1})")
    boolean addAgesectionDiv(int AGESECTION, String goods);

    /**
     * 添加脸型分配
     * @param FACE
     * @param goods
     * @return
     */
    @Insert("insert into  T_SUITABLEFACE (FACE,GOODS) VALUES (#{0},#{1})")
    boolean addFaceDiv(int FACE, String goods);

    /**
     * 添加场景分配
     * @param occasion
     * @param goods
     * @return
     */
    @Insert("insert into  T_SUITABLEOCCASION (OCCASION,GOODS) VALUES (#{0},#{1})")
    boolean addOccasionDiv(int occasion, String goods);

    /**
     * 添加附件分配
     * @param s
     * @param goods
     * @param i
     * @return
     */
    @Insert("insert into  T_GOODSATTACHMENT (ATTACHMENT,GOODS,SN) VALUES (#{0},#{1},#{2})")
    boolean addAttachmentDiv(String s, String goods, int i);

    /**
     * 根据镜框Id添加境况信息
     * @param goods
     * @param brand
     * @param name
     * @param models
     * @param width
     * @param height
     * @param space
     * @param length
     * @param max_width
     * @param suitable_sex
     * @param price
     * @return
     */
    @Insert("insert into  T_GOODS(GOODS,BRAND,NAME,MODELS,WIDTH,HEIGHT,SPACE,LENGTH,MAX_WIDTH,SUITABLE_SEX,PRICE,DETAILPHOTO) VALUES (#{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7},#{8},#{9},#{10},#{11})")
    boolean add(String goods, int brand, String name, String models, int width, int height, int space, int length, int max_width, int suitable_sex, int price, String detailphoto);

    /**
     * 根据镜框Id获取镜框信息
     * @param goods
     * @return
     */
    @Select("select * from T_GOODS WHERE GOODS = #{0}")
    T_goods loadById(String goods);


    /**
     * 获取镜框试戴图片
     * @param goods
     * @return
     */
    @Select("select * from T_ATTACHMENT WHERE ATTACHMENT IN ( SELECT ATTACHMENT FROM T_GOODSATTACHMENT WHERE  GOODS = #{0} AND SN = 0)  ")
    List<T_attachment> loadWearsByGood(String goods);


    /**
     * 根据镜框Id获取附件信息
     * @param goods
     * @return
     */
    @Select("select * from T_ATTACHMENT WHERE ATTACHMENT IN ( SELECT ATTACHMENT FROM T_GOODSATTACHMENT WHERE  GOODS = #{0} AND SN <> 0)  ")
    List<T_attachment> loadAttachmentByGood(String goods);

    /**
     * 根据镜框Id获取附件信息
     * @param goods
     * @return
     */
    @Select("select * from T_ATTACHMENT WHERE ATTACHMENT IN ( SELECT ATTACHMENT FROM T_GOODSATTACHMENT WHERE  GOODS = #{0} )  ")
    List<T_attachment> loadAllAttachmentByGood(String goods);

    /**
     * 删除颜色分配
     * @param goods
     * @return
     */
    @Delete("delete from  T_GOODSCOLOR where GOODS = #{0}")
    boolean deleteColorDiv(String goods);

    /**
     * 删除年龄段分配
     * @param goods
     * @return
     */
    @Delete("delete from  T_SUITABLEAGE where GOODS = #{0}")
    boolean deleteAgeDiv(String goods);

    /**
     * 删除脸型分配
     * @param goods
     * @return
     */
    @Delete("delete from  T_SUITABLEFACE where GOODS = #{0}")
    boolean deleteFaceDiv(String goods);

    /**
     * 删除场景分配
     * @param goods
     * @return
     */
    @Delete("delete from  T_SUITABLEOCCASION where GOODS = #{0}")
    boolean deleteOccasionDiv(String goods);

    /**
     * 根据镜框Id删除镜框信息
     * @param code
     * @return
     */
    @Delete("delete from  T_GOODS where GOODS = #{0}")
    boolean delete(String code);

    /**
     * 根据镜框id更新镜框信息
     * @param goods
     * @param brand
     * @param name
     * @param models
     * @param width
     * @param height
     * @param space
     * @param length
     * @param max_width
     * @param suitable_sex
     * @param price
     * @return
     */
    @Update("update T_GOODS set BRAND  = #{1} ,NAME  = #{2}  ,MODELS  = #{3}  ,WIDTH = #{4} ,HEIGHT  = #{5}  ,SPACE  = #{6}  ,LENGTH  = #{7}  ,MAX_WIDTH  = #{8}  ,SUITABLE_SEX  = #{9}  ,PRICE  = #{10}, DETAILPHOTO = #{11} where goods = #{0}")
    boolean update(String goods, int brand, String name, String models, int width, int height, int space, int length, int max_width, int suitable_sex, int price, String editDetailphoto);

    /**
     * 添加类别分配
     * @param i
     * @param goods
     * @return
     */
    @Insert("insert into  T_GOODSCATEGORY (CATEGORY,GOODS) VALUES (#{0},#{1})")
    boolean addCategoryDiv(int i, String goods);

    /**
     * 删除类别分配
     * @param goods
     * @return
     */
    @Delete("delete from  T_GOODSCATEGORY where GOODS = #{0}")
    boolean deleteCategoryDiv(String goods);

    /**
     * 获取热销眼镜
     */
    @Select("SELECT * FROM T_GOODS WHERE rownum < 6")
    List<T_goods> listHot();

    /**
     * 获取各种眼镜类别的眼镜
     * @param category
     * @return
     */
    @Select("select * from T_GOODS where GOODS in (select GOODS from T_GOODSCATEGORY where CATEGORY = #{0})")
    List<T_goods> listShop(int category);

    /**
     * 推荐镜框
     * @param sex
     * @param occasion
     * @param pupil
     * @return
     */
    @Select("select * from T_GOODS WHERE GOODS in  ( select T_SUITABLEAGE.GOODS FROM T_AGESECTION,T_SUITABLEAGE,T_SUITABLEOCCASION,T_SUITABLEFACE  " +
            " WHERE  ( #{2} between ( width + space - 3 ) and ( width + space + 3 )) and ({4} between MINAGE and MAXAGE) and T_SUITABLEOCCASION.OCCASION = #{2} and T_SUITABLEFACE.FACE = #{3} and (SUITABLE_SEX = #{0} or SUITABLE_SEX = 0) and T_AGESECTION.AGESECTION = T_SUITABLEAGE.AGESECTION  " +
            " and T_SUITABLEAGE.GOODS = T_SUITABLEOCCASION.GOODS and T_SUITABLEAGE.GOODS = T_SUITABLEFACE.GOODS)")
    List<T_goods> recomend(int sex, int occasion, int pupil, int face, int age);


    /**
     * 通过类别获取眼镜
     * @param category
     * @return
     */
    @Select("select * from T_GOODS where goods in (select goods from T_GOODSCATEGORY where CATEGORY = #{0})")
    List<T_goods> loadGoodsByCategory(int category);


    /**
     * 通过类别获取眼镜   数据分页
     * @param category
     * @return
     */
    @Select("select * from (select T_GOODS.*,rownum rn from T_GOODS where goods in (select goods from T_GOODSCATEGORY where CATEGORY = #{2})) where rn > #{0} and rn <= #{1}")
    List<T_goods> loadGoodsByCategory_pager(int start,int end,int category);


    @Select("select * from (select T_GOODS.*,rownum rn from T_GOODS where goods in (select goods from T_GOODSCATEGORY)) where rn > 0 and rn <= 8")
    List<T_goods> loadTop8();
}
