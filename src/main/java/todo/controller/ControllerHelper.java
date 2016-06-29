package todo.controller;

import todo.model.User;

import java.time.Instant;
import java.util.*;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by bill.villaflor on 6/29/16.
 */
public final class ControllerHelper {

    public static final String TASK_TITLE = "title";
    public static final String TASK_DESCRIPTION = "description";
    public static final String TASK_ID = "taskId";

    public static final String USER_ID = "userId";
    public static final String USER_FIRST_NAME = "firstName";
    public static final String USER_LAST_NAME = "lastName";
    public static final String USER_EMAIL = "emailAddress";


    private static final String MESSAGE = "message";

    public static Map<String,Object> createResponse(String message, String type, List<User> objects){

        Map<String,Object> response = new LinkedHashMap<>();
        response.put( MESSAGE, message );
        response.put( type, objects);

        return response;
    }

    public static Date getDateNow(){

        return Date.from( Instant.now() );
    }
}
