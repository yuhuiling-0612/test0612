package com.example.demo.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;
import java.util.Properties;


/**这是一个OracleDataSourceConfig **/
@Configuration
@MapperScan(basePackages = OracleDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "oracleSqlSessionFactory")
public class OracleDataSourceConfig {
    //你的mapper位置，会自动扫描，这也就是自动装配的好处了
    /**
     这里我在mapper层下又建立了一个包oracle，专门用来存储这个数据源的mapper，
     为后面的多数据源配置做准备。一定要注意不要写错。
     **/
    static final String PACKAGE = "com.example.demo.mapper.oracle";
    //你的mapper.xml位置
    static final String MAPPER_LOCATION = "classpath:oracleMapper/*.xml";

    @Autowired
    private OracleDataSourceProperties config;
    /**
     @primary 这个是用来区分数据源的，如果你是多数据源配置，那么一定要在你的主数据源上
     添加@primary注释，否则多数据源容易出错。如果你只有一个数据源，那么可以不要这个注解。
     **/
    @Bean(name = "oracleDataSource")
    @Primary
    public DataSource oracleDataSource() throws Exception{

        Properties properties = new Properties();
        properties.put("driverClassName", config.getDriverClassName());
        properties.put("url", config.getUrl());
        properties.put("username", config.getUsername());
        properties.put("password", config.getPassword());


        //#申请连接时，空闲时间大于（timeBetweenEvictionRunsMillis）,则检测连接的有效性    true
        properties.put("testWhileIdle", "true");
        //#申请连接时，检测连接的有效性（性能损耗）  false
        properties.put("testOnBorrow", "false");
        //#归还连接时，检测连接的有效性（性能损耗） false
        properties.put("testOnReturn", "false");
        return DruidDataSourceFactory.createDataSource(properties);
    }

    @Bean(name = "oracleTransactionManager")
    @Primary
    public DataSourceTransactionManager oracleTransactionManager()throws Exception{
        return new DataSourceTransactionManager(oracleDataSource());
    }

    @Bean(name = "oracleSqlSessionFactory")
    @Primary
    public SqlSessionFactory oracleSqlSessionFactory(@Qualifier("oracleDataSource") DataSource oracleDataSource) throws Exception{
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(oracleDataSource);

        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        //5.1.2版本的pagehelper
        Interceptor interceptor = new PageInterceptor();
        interceptor.setProperties(p);
        Interceptor[] plugins =  new Interceptor[]{interceptor};
        sessionFactory.setPlugins(plugins);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(OracleDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
