//package com.example.ykcloud.config;
//
//import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
//
//import java.util.List;
//
//
//@Configuration
//@EnableSwagger2WebMvc
//@Import({BeanValidatorPluginsConfiguration.class})
//public class Swagger2ConfigForProductData {
//    @Value("${spring.application.name}")
//    private String applicationName;
//    @Value("${swagger.scan_base_package.path:com.cadi.cg}")
//    private String path;
//
//    public Swagger2ConfigForProductData() {
//        //do nothing
//    }
//
//    @Bean
//    public Docket restfulApi() {
//        return (new Docket(DocumentationType.SWAGGER_2)).apiInfo(this.groupApiInfo()).select().apis(RequestHandlerSelectors.basePackage(this.path)).apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any()).build().pathMapping(CommonUtil.judgeIsCloudService() ? "/" + this.applicationName : "").securityContexts(CollectionUtils.newArrayList(this.securityContext())).securitySchemes(CollectionUtils.newArrayList(this.apiKey()));
//    }
//
//    private ApiInfo groupApiInfo() {
//        return (new ApiInfoBuilder()).title(this.applicationName + "接口说明文档").description("<div style='font-size:14px;color:red;'>REACH Foundation接口说明文档</div>").termsOfServiceUrl(this.applicationName).contact(new Contact("REACH Foundation", "http://www.glaway.com", "yuyang@glaway.com")).version("5.0").build();
//    }
//
//    private ApiKey apiKey() {
//        return new ApiKey("BearerToken", "X-Access-Token", "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder().securityReferences(this.defaultAuth()).forPaths(PathSelectors.regex("/.*")).build();
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
//        return CollectionUtils.newArrayList(new SecurityReference("BearerToken", authorizationScopes));
//    }
//}
