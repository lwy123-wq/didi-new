package sun.com.didi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import sun.com.didi.interceptor.AddressedInterceptor;

@Configuration
public class WebappConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private AddressedInterceptor addressedInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(addressedInterceptor).addPathPatterns("/**");
    }
}
