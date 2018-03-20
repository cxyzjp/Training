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
@MapperScan(basePackages = "com.cxy.shardingjdbc.mapper.a", sqlSessionTemplateRef = "dsASqlSessionTemplate")
public class DataSourceAConfig {

    @Bean(name = "dsADataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dsA")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dsASqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("dsADataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/a/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "dsATransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("dsADataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "dsASqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("dsASqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}