package jx.lczj.dao;

import jx.lczj.model.T_news;
import jx.lczj.viewmodel.NewsVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by 14260 on 2018/6/23.
 */
public interface NewsDao {
    /**
     *添加文章信息
     * @param code
     * @param title
     * @param ISSUE_DATE
     * @param AUTHOR
     * @param KEYWORD
     * @param FILENAME
     * @param ITEMS
     * @param TOP
     * @param PUBLISH
     * @param PHOTO
     * @return
     */
    @Insert("insert into T_NEWS (CODE,TITLE,ISSUE_DATE,AUTHOR,KEYWORD,FILENAME,ITEMS,TOP,PUBLISH,PHOTO)  VALUES (#{0},#{1},TO_DATE(#{2},'yyyy-mm-dd hh24:mi:ss'),#{3},#{4},#{5},#{6},#{7},#{8},#{9})")
    boolean add(String code, String title, String ISSUE_DATE, String AUTHOR, String KEYWORD, String FILENAME, int ITEMS, String TOP, String PUBLISH, String PHOTO);

    /**
     * 根据文章Id获取文章信息
     * @param code
     * @return
     */
    @Select("select * from T_NEWS WHERE CODE =#{0}")
    T_news loadByCode(String code);

    /**
     * 根据管理员Id和类别获取文章
     * @param admin
     * @param items
     * @return
     */
    @Select("select * from T_NEWS WHERE AUTHOR =#{0} and ITEMS = #{1} ")
    List<T_news> listByAdmin(String admin,int items);

    /**
     * 根据文章Id删除文章信息
     * @param code
     * @return
     */
    @Delete("delete from T_NEWS WHERE CODE =#{0}")
    boolean delete(String code);

    /**
     * 根据文章Id更新获取文章信息
     * @param code
     * @param title
     * @param ISSUE_DATE
     * @param AUTHOR
     * @param KEYWORD
     * @param FILENAME
     * @param ITEMS
     * @param TOP
     * @param PUBLISH
     * @param PHOTO
     * @return
     */
    @Update("update T_NEWS set  TITLE = #{1},ISSUE_DATE = TO_DATE(#{2},'yyyy-mm-dd hh24:mi:ss') ,AUTHOR = #{3} ,KEYWORD =  #{4},FILENAME =  #{5},ITEMS = #{6},TOP = #{7},PUBLISH = #{8} ,PHOTO = #{9}  where code = #{0}")
    boolean update(String code, String title, String ISSUE_DATE, String AUTHOR, String KEYWORD, String FILENAME, int ITEMS, String TOP, String PUBLISH, String PHOTO);

    /**
     * 根据类别加载文章
     * @param items
     * @return
     */
    @Select("select * from T_NEWS WHERE  ITEMS = #{1} ")
    List<T_news> list(int items);

    /**
     * 删除文章图片
     * @param code
     * @return
     */
    @Update("update T_NEWS set PHOTO = ''  where code = #{0}")
    boolean deletePhoto(String code);

    @Select("select * from  ( select t.*,rownum rn from T_NEWS t  where  ITEMS = #{2} ) where rn > #{0} and rn <= #{1}")
    List<T_news> listByStart(int start, int end,int items);

    /**
     * 获取item 的文章
     * @return
     */
    @Select("SELECT * FROM  T_NEWS WHERE ITEMS = #{0} ORDER BY TOP , ITEMS  DESC")
    List<T_news> listitem(int item);
}
