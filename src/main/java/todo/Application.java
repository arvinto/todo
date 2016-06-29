package todo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import todo.controller.ControllerHelper;
import todo.service.RepositoryService;

/**
 * Created by arvinaboque on 6/27/16.
 */
@SpringBootApplication
public class Application {

    @Bean
    InitializingBean populate( RepositoryService service ){
        return () -> {
            service.addUser( "Bill", "Villaflor", "bill@villaflor.com", "bill", "bill" );
            service.addUser( "Arvin", "Aboque", "arvin@aboque.com", "arvin", "arvin" );
            service.addTask( 1L, "Sample Task", "Sample task decscription", ControllerHelper.getDateNow() );
        };
    }

    public static void main(String[] args){

        SpringApplication.run( Application.class, args );
    }
}
