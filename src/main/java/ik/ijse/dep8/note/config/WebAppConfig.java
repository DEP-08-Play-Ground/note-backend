package ik.ijse.dep8.note.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import ik.ijse.dep8.note.WebAppInitializer;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackageClasses = {WebAppInitializer.class})
@EnableWebMvc
public class WebAppConfig {
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yamlMapFactoryBean = new YamlPropertiesFactoryBean();
        yamlMapFactoryBean.setResources(new ClassPathResource("application.yaml"));
        placeholderConfigurer.setProperties(yamlMapFactoryBean.getObject());
        return placeholderConfigurer;
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}

