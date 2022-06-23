package ik.ijse.dep8.note.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import ik.ijse.dep8.note.WebAppInitializer;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan(basePackageClasses = {WebAppInitializer.class})
//@EnableWebMvc
public class WebAppConfig {
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public YamlPropertiesFactoryBean yamlPropertiesFactory() {
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new ClassPathResource("application.yaml"));
        System.out.println(yamlPropertiesFactoryBean.getObject());
        return yamlPropertiesFactoryBean;
    }
}

