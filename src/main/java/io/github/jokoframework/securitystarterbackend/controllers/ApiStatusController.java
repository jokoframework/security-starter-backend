package io.github.jokoframework.securitystarterbackend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.github.jokoframework.securitystarterbackend.constants.ApiPaths;

@RestController
public class ApiStatusController {
    @RequestMapping(value = ApiPaths.STATUS, method = RequestMethod.GET)
    public String status() {
        return "Bienvenido a Joko Security Starter Backend";
    }
}
