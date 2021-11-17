package sun.com.didi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sun.com.didi.lucene.createIndex;

import javax.annotation.Resource;

@ComponentScan(basePackages = "sun.com")
@SpringBootApplication
@EnableCaching
public class Application implements WebMvcConfigurer {
    @Autowired
    private static createIndex createIndex;
    public static void main(String[] args) {

        try {
            createIndex.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SpringApplication.run(Application.class);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
