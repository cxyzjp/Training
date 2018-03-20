package com.cxy.shardingjdbc.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.cxy.shardingjdbc.mapper.b", sqlSessionTemplateRef = "dsBSqlSessionTemplate")
public class DataSourceBConfig {

    @Bean(name = "dsBDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dsB")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dsBSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("dsBDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/b/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "dsBTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("dsBDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "dsBSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("dsBSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}