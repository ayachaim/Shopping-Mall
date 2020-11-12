package com.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
    //http://localhost:8088/swagger-ui.html
    //http://localhost:8088/doc.html
    //配置核心docket
    @Bean
    public Docket createRestApi(){
         return new Docket(DocumentationType.SWAGGER_2)     //使用Swagger2
                 .apiInfo(apiInfo())                        //文档注册汇总信息
                 .select()
                 .apis(RequestHandlerSelectors.basePackage("com.dev.controller"))           //指定contraller
                 .paths(PathSelectors.any())                                                //所有contraller
                 .build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("电商网站接口平台")                                                    //标题
                .contact(new Contact("wangning",                                     //联系人信息
                        "https://github.com/wangning136876/Shopping-Mall",
                        "376885153@qq.com"))
                .description("电商前后端对接的开发文档")                                        //详细信息
                .version("1.0.1")                                                          //版本
                .termsOfServiceUrl("https://github.com/wangning136876/Shopping-Mall")      //网站
                .build();

    }

}
