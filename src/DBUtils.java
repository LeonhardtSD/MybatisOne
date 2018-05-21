import mapper.UserMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import sun.util.locale.provider.HostLocaleProviderAdapterImpl;

/**
 * @author Leon
 */
public class DBUtils {
    private static SqlSessionFactory sqlSessionFactory=null;
    private static final Class Class_LOCK=DBUtils.class;

    public static SqlSessionFactory initSqlSessionFactory(){
        synchronized (Class_LOCK){
            if(sqlSessionFactory==null){
                PooledDataSource dataSource=new PooledDataSource();
                dataSource.setDriver("com.mysql.jdbc.Driver");
                dataSource.setUrl("jdbc:mysql://localhost:3306/mybatisone");
                dataSource.setUsername("root");
                dataSource.setPassword("FREEDOM");

                //事务管理
                TransactionFactory transactionFactory=new JdbcTransactionFactory();
                Environment environment=new Environment("development",transactionFactory,dataSource);
                Configuration configuration=new Configuration(environment);
                configuration.addMapper(UserMapper.class);

                //通过SqlSessionFactoryBuilder来builder一个sqlSessionFactory（数据库连接）
                sqlSessionFactory=new SqlSessionFactoryBuilder().build(configuration);

            }
        }
        return sqlSessionFactory;
    }


    public static SqlSession openSqlSession(){
        if(sqlSessionFactory==null)
            initSqlSessionFactory();
        return sqlSessionFactory.openSession();
    }
}
