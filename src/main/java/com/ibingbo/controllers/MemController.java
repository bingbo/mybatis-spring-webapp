package com.ibingbo.controllers;

import com.ibingbo.models.Response;
import com.ibingbo.services.MemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bing on 2017/1/12.
 */
@Controller
@RequestMapping("/mem")
public class MemController {
    private final static Logger LOGGER = LoggerFactory.getLogger(MemController.class);
    @Autowired
    private MemService memService;

    @RequestMapping("/get/{key}")
    @ResponseBody
    public Response get(@PathVariable("key") String key) {
        Response response = new Response();
        response.setData(memService.get(key));
        return response;
    }

    @RequestMapping("/set/{key}/{value}")
    @ResponseBody
    public Response set(@PathVariable("key") String key, @PathVariable("value") String value) {
        Response response = new Response();
        memService.set(key,value);
        return response;
    }
}
