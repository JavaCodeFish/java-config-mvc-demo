package com.yuan.demo.confing;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import java.util.regex.Pattern;

/**
 * 根应用上下文
 */
@Configuration
@PropertySource("classpath:config.properties")
@ComponentScan(basePackages = {"com.yuan.demo"})
@Import(DataConfig.class)
public class RootConfig {

    // 内部类，用来排除 web 相关的包，因为这些包已经在 WebConfig 中导入了
//    public static class WebPackage extends RegexPatternTypeFilter {
//        public WebPackage() {
//            super(Pattern.compile("org\\.acherie\\.web"));
//        }
//    }
}
