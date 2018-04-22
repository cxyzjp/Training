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
@MapperScan(basePackages = "com.cxy.shardingjdbc.mapper.b", sqlSessionTemplateRef = "dsbSqlSessionTemplate")
public class DataSourceBConfig {

    @Bean(name = "dsbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dsb")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dsbSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("dsbDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/b/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "dsbTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("dsbDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "dsbSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("dsbSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}