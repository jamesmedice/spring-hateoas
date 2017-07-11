package com.open.demo.hateoas;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.open.demo.hateoas.api.CORSFilter;

@Configuration
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class RestConfiguration {

    @Bean
    public Filter shallowEtagHeaderFilter() {
	return new ShallowEtagHeaderFilter();
    }

    @Bean
    @Autowired
    public FilterRegistrationBean corsFilterRegistrationBean() {
	final FilterRegistrationBean registration = new FilterRegistrationBean();
	registration.setFilter(new CORSFilter());
	registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
	return registration;
    }

}
