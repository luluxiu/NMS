package com.nms;

import com.nms.service.support.SViewHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by freedom on 2016/4/13.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private SViewHelper svh;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(viewObjectAddingInterceptor());
        super.addInterceptors(registry);
    }


    @Bean
    public HandlerInterceptor viewObjectAddingInterceptor() {
        return new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                    throws Exception {

                svh.setStartTime(System.currentTimeMillis());
                if(svh.getPath() == null) {
                    svh.setPath(request.getContextPath());
                }
                if(svh.getUsername() == null) {
                    SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
                            .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
                    if(securityContextImpl != null && securityContextImpl.getAuthentication() != null &&
                            securityContextImpl.getAuthentication().getName() != null) {

                        svh.setUsername(securityContextImpl.getAuthentication().getName());
                    }
                }

                return true;
            }
        };
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return new MyCustomizer();
    }

    private static class MyCustomizer implements EmbeddedServletContainerCustomizer {

        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
            container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400.html"));
            container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/error/403.html"));
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.html"));
            container.addErrorPages(new ErrorPage(HttpStatus.SERVICE_UNAVAILABLE, "/error/5xx.html"));
        }

    }
}
