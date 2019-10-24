package com.as.test.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * @author as
 * @desc
 * @since 2019/4/19 16:27
 */
@WebService
public class HelloWebService {
    public String helloWorld(String name) {
        return "hello " + name;
    }

    @WebMethod(exclude = true)
    public String helloWorld2(String name) {
        return "hello " + name;
    }

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9999/helloWorld", new HelloWebService());
    }
}
