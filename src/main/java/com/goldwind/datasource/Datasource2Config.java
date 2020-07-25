package com.goldwind.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * @Author: zy
 * @Description: 读取数据源datasource2配置信息
 * @Configuration用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，并用于构建bean定义，初始化Spring容器
 * @Configuation等价于<Beans></Beans>
 * @Bean等价于<Bean></Bean>
 * @ComponentScan等价于<context:component-scan base-package="com.goldwind.datasource2"/>
 * @Date: 2020-2-5
 */
//表示这个类为一个配置类
@Configuration
//配置mybatis的接口类放的地方
@MapperScan(basePackages = "com.goldwind.mapper.datasource2", sqlSessionFactoryRef = "datasource2SqlSessionFactory")
public class Datasource2Config {

    /**
     * 功能描述:(配置datasource2数据库)
     * @return:@return DataSource
     */
    //创建一个bean对象，并注入到Spring容器中
    @Bean(name = "datasource2DataSource")
    // 读取application.properties中的配置参数映射成为一个对象
    @ConfigurationProperties(prefix = "spring.datasource.datasource2")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }


    /**
     * 功能描述:(datasource2 sql会话工厂)
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "datasource2SqlSessionFactory")
    //@Qualifier表示查找Spring容器中名字为datasource2DataSource的对象
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("datasource2DataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }


    /**
     * 功能描述:(datasource2 事物管理)
     * @param dataSource
     * @return
     */
    @Bean(name = "datasource2TransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("datasource2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "datasource2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("datasource2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

