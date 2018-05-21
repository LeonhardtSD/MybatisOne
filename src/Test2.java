import entity.User;
import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @author Leon
 */
public class Test2 {
   @Test
   public void test2(){
       SqlSession sqlSession=null;
       try{
           sqlSession =DBUtils.openSqlSession();
           UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
           User user=userMapper.getUser(2L);

           System.out.println(user.toString());
           sqlSession.commit();
       }catch (Exception e){
           e.printStackTrace();
           sqlSession.rollback();
       }finally {
           if(sqlSession!=null){
               sqlSession.close();
           }
       }
   }

   @Test
    public void test3(){
       SqlSession sqlSession=null;
       try{
           sqlSession=DBUtils.openSqlSession();
           User user=(User) sqlSession.selectOne("mapper.UserMapper.getUser",1L);
           System.out.print(user.toString());
           sqlSession.commit();
       }catch (Exception e){
           e.printStackTrace();
           sqlSession.rollback();
       }finally{
           if(sqlSession!=null){
               sqlSession.close();
           }
       }
   }

}
