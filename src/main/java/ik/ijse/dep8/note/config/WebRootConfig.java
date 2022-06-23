package ik.ijse.dep8.note.config;

import ik.ijse.dep8.note.WebAppInitializer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

@Configuration
@Import(JPAConfig.class)
public class WebRootConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public static YamlPropertiesFactoryBean yamlPropertiesFactoryBean(ConfigurableEnvironment env){
        YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
        yamlFactory.setResources(new ClassPathResource("application.yaml"));
        Properties yamlProps=yamlFactory.getObject();
        env.getPropertySources().addLast(new PropertiesPropertySource("yaml",yamlProps));
        return yamlFactory;
    }
}
