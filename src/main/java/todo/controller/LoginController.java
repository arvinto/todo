package todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by bill.villaflor on 6/29/16.
 */
@Controller
@RequestMapping( "/login" )
public class LoginController {

    @RequestMapping( method = RequestMethod.POST )
    public void login( @RequestBody Map<String, Object> loginMap ){

        String id = loginMap.get( "id" ).toString();
        String password = loginMap.get( "password" ).toString();

        System.out.print( id );
    }
}
