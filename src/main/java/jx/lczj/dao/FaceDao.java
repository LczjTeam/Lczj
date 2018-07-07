package jx.lczj.dao;

import jx.lczj.model.T_face;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface FaceDao {
    /**
     * 加载所有脸型
     * @return
     */
    @Select("select * FROM T_FACE")
    public List<T_face> loadFace();

    /**
     * 添加脸型信息
     * @param face
     * @param name
     * @param photo
     * @return
     */
    @Insert("insert into T_FACE values(#{0},#{1},#{2})")
    boolean addFace(int face,String name,String photo);

    /**
     * 根据脸型Id查找脸型信息
     * @param face
     * @return
     */
    @Select("select * FROM T_FACE WHERE FACE = #{0}")
    T_face loadByFace(int face);

    /**
     * 根据脸型Id更新脸型信息
     * @param face
     * @param name
     * @param photo
     * @return
     */
    @Update("update T_FACE set name = #{1}，photo = #{2} where face = #{0}")
    boolean updateFace(int face, String name, String photo);

    /**
     * 根据脸型Id删除脸型信息
     * @param face
     * @return
     */
    @Delete("delete FROM T_FACE where FACE = #{0} ")
    boolean deleteFace(int face);

    /**
     * 根据镜框Id获取脸型信息
     * @param goods
     * @return
     */
    @Select("select *  FROM T_FACE WHERE FACE in ( select FACE FROM T_SUITABLEFACE WHERE GOODS = #{0} )")
    List<T_face> loadByGood(String goods);
}
