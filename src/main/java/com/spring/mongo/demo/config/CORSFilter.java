package com.spring.mongo.demo.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CORSFilter implements Filter {// 作用是允许跨域请求


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {// 初始化方法，在过滤器创建时被调用

    }

    /**
     *  过滤方法，每次请求都会被调用
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");// 允许任何域名使用
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");// 允许任何方法（post、get等）
        response.setHeader("Access-Control-Max-Age", "3600");// 预检请求的有效期，单位为秒
        response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept");// 允许任何头
        response.setHeader("Access-Control-Expose-Headers", "Location");// 允许返回自定义的响应头
        filterChain.doFilter(servletRequest, servletResponse);// 调用下一个过滤器
    }

    @Override
    public void destroy() {// 销毁方法，在过滤器销毁时被调用

    }

}
