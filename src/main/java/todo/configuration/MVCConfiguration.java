package todo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by bill.villaflor on 6/29/16.
 */
@Configuration
public class MVCConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers( ViewControllerRegistry registry ){

        registry.addViewController( "/login" ).setViewName( "login" );
    }
}
