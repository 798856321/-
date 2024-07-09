package com.simulation.demo.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.simulation.demo.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.xml.ws.soap.Addressing;

@Configuration
@Order
public class WebConfig  implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    //本地静态文件映射为网络路径
   /* @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String osName = System.getProperty("os.name");
        //设置网络访问路径为 http://127.0.0.1:8080/upload/filename
        String fileUploadResources = "/upload/**";
        String win ="win";
        //判断电脑是否为windows  将本地路径 -> http://127.0.0.1:8080/upload
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String url = applicationHome.getDir().getParentFile()
                .getParentFile().getAbsolutePath() + "\\src\\main\\resources\\static\\video\\";
        if (osName.toLowerCase().startsWith(win)) {
            registry.addResourceHandler(fileUploadResources)
                    .addResourceLocations("file:"+url);}
        else{registry.addResourceHandler(fileUploadResources)
                .addResourceLocations("file:/upload/");}

    }*/



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 某些接口不拦截(例如登录和注册)
        registry.addInterceptor(loginInterceptor).addPathPatterns("/user/**","/admin/**").excludePathPatterns(
                "/user/login",
                "/user/register","/admin/login","/admin/register","/upload/**");
    }



    /*MyBatisPlus 分页插件*/
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        //1.初始化核心插件
        MybatisPlusInterceptor intercept = new MybatisPlusInterceptor();
        //2. 添加分页插件
        PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        pageInterceptor.setMaxLimit(1000L);//设置分页上限
        intercept.addInnerInterceptor(pageInterceptor);
        return intercept;
    }

    /*
    *
    * 全局CORS  (配置前后端跨域)
    * */

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowedHeaders("*")
                .exposedHeaders("*");

    }




    /**
     * 拦截器配置
     */

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            //访问路径
            registry.addResourceHandler("/upload/**")
                    //映射真实路径
                    .addResourceLocations("file:" + System.getProperty("user.dir") + "/src/main/resources/static" +
                            "/video/");//必须加"/"，不然映射不到
        }


}
