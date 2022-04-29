package com.example.controllers;

import com.example.entities.User;
import com.example.handlers.UserHandler;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import javax.validation.constraints.Size;
import java.beans.beancontext.BeanContext;
import java.util.Collections;

@Controller("/Users")
public class UserController {
    //@Inject
    public UserHandler userHandler;
    public UserController(UserHandler userHandler){
        this.userHandler = userHandler;
    }
//    final BeanContext context = BeanContext.run();
    //Тест
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    @Post("/Test")
    public HttpResponse<String> test(String name/*HttpRequest<?> request*/) {
        /*String name = request.getParameters()
                .getFirst("name")
                .orElse("xui");

         */
        return HttpResponse.ok().body("{\"msg\":\""+ name +"\"}");
    }
    @Post(value = "/echo", consumes = MediaType.APPLICATION_JSON) //
    String echo(@Size(max = 1024) @Body String text) { //
        return text; //
    }
    @Post(value = "/Register" , consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<String> reg(String login,String pass,String role) {
        if(userHandler.checkUnique(login).isEmpty())
        {
            userHandler
                    .addU(new User(1, login,pass, role));
            return HttpResponse.ok().body("{\"msg\":\"ok\"}");
        }
        else {
            return HttpResponse.ok().body("{\"msg\":\"neok\"}");
        }
    }
    @Post(value = "/Login" , consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<String> log(String login,String pass) {
        if(!(userHandler.checkLog(login,pass).isEmpty()))
        {
            return HttpResponse.ok().body("{\"msg\":\"okLOg\"}");
        }
        else {
            return HttpResponse.ok().body("{\"msg\":\"neokLOg\"}");
        }
    }

}
