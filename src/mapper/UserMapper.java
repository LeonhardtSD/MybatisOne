package mapper;

import entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author Leon
 */
public interface UserMapper {
    @Select(value = "select * from user where id=#{id}")
    public User getUser(Long id);
}
