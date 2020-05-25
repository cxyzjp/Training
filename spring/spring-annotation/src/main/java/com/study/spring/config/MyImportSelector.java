package com.study.spring.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
    /*
        AnnotationMetadata: 当前标注了@Import类的信息
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 返回Bean的全类名
        return new String[]{"com.study.spring.beans.Blue"};
    }
}
