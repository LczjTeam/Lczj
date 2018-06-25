package jx.lczj.dao;

import jx.lczj.model.T_face;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface FaceDao {

    @Select("select * FROM T_FACE")
    public List<T_face> loadFace();

    //插入脸型
    @Insert("insert into T_FACE values(#{0},#{1},#{2})")
    boolean addFace(int face,String name,String photo);

    //通过face查询信息
    @Select("select * FROM T_FACE WHERE FACE = #{0}")
    T_face loadByFace(int face);
    //update
    @Update("update T_FACE set name = #{1}，photo = #{2} where face = #{0}")
    boolean updateFace(int face, String name, String photo);

    //删除记录
    @Delete("delete FROM T_FACE where FACE = #{0} ")
    boolean deleteFace(int face);
}
