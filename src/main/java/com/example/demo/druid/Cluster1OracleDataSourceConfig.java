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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = Cluster1OracleDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "cluster1OracleSqlSessionFactory")
public class Cluster1OracleDataSourceConfig {
    static final String PACKAGE = "com.example.mapper.oracleclu";
    static final String MAPPER_LOCATION = "classpath*:oraclecluMapper/*.xml";

    @Autowired
    private Cluster1OracleDataSourceProperties config;

    @Bean(name = "cluster1oracleCdDataSource")
    public DataSource cluster1oracleCdDataSource() throws Exception{

        Properties properties = new Properties();
        properties.put("driverClassName", config.getDriverClassName());
        properties.put("url", config.getUrl());
        properties.put("username", config.getUsername());
        properties.put("password", config.getPassword());
        properties.put("testWhileIdle", "true");
        //#申请连接时，检测连接的有效性（性能损耗）  false
        properties.put("testOnBorrow", "false");
        //#归还连接时，检测连接的有效性（性能损耗） false
        properties.put("testOnReturn", "false");
        return DruidDataSourceFactory.createDataSource(properties);
    }

    @Bean(name = "cluster1oracleCdTransactionManager")
    public DataSourceTransactionManager cluster1oracleCdTransactionManager()throws Exception{
        return new DataSourceTransactionManager(cluster1oracleCdDataSource());
    }

    @Bean(name = "cluster1oracleCdSqlSessionFactory")
    public SqlSessionFactory cluster1oracleCdSqlSessionFactory(@Qualifier("cluster1oracleCdDataSource") DataSource cluster1oracleCdDataSource) throws Exception{
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(cluster1oracleCdDataSource);

        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");

        //5.1.2版本的pagehelper
        Interceptor interceptor = new PageInterceptor();
        interceptor.setProperties(p);
        Interceptor[] plugins =  new Interceptor[]{interceptor};
        sessionFactory.setPlugins(plugins);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(Cluster1OracleDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
