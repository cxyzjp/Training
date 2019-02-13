package com.study.spring.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/*
    @ComponentScan.Filter 中自定义过滤类型
    TypeFilter:
        MetadataReader: 当前正在扫描的类的信息
        MetadataReaderFactory：获取其他类的信息
 */
public class MyTypeFilter implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前类注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 当前类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 类资源信息（类路径等）
        Resource resource = metadataReader.getResource();

        return classMetadata.getClassName().contains("Green");
    }
}
