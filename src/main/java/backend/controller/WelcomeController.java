package backend.controller;


import backend.entity.Demo;
import backend.entity.User;
import backend.parameter.welcome.UserQueryParameter;
import backend.parameter.welcome.SignUpParameter;
import backend.parameter.welcome.UserLoginParameter;
import backend.response.commonResponse.UserQueryResponse;
import backend.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping(value = "/welcome")
public class WelcomeController {
    @Autowired
    WelcomeService service;

    public WelcomeController() {
    }

    @RequestMapping(value = "/signup",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void userSignUp(@RequestBody SignUpParameter param,
                           HttpServletRequest request, HttpServletResponse response) {

        response.setStatus(service.signUp(param));

    }

    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void userLogin(@RequestBody UserLoginParameter param,
                          HttpServletRequest request, HttpServletResponse response) {

        response.setStatus(service.userLogin(param));

    }

    @RequestMapping(value = "/admin",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void adminLogin(@RequestBody Demo demo,
                           HttpServletRequest request, HttpServletResponse response) {
        if (service.adminLogin(demo.getUsername(), demo.getPassword())) {
            response.setStatus(201);
        } else {
            response.setStatus(404);
        }
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    String getUserName(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
        return service.getUserName(id);
    }

    @RequestMapping(value = "/users",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    UserQueryResponse getUserInfo(UserQueryParameter param, HttpServletRequest request, HttpServletResponse response) {
        UserQueryResponse res = service.getUserInfo(param);
        response.setStatus(201);
        return res;
    }

}
