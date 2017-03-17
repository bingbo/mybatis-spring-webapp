package com.ibingbo.controllers;

import com.ibingbo.models.Response;
import com.ibingbo.services.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bing on 2017/1/13.
 */
@Controller
@RequestMapping("/redis")
public class RedisController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedisController.class);
    @Autowired
    private RedisService redisService;

    @RequestMapping("/set/{key}/{value}")
    @ResponseBody
    public Response set(@PathVariable("key") String key, @PathVariable("value") String value) {
        Response response = new Response();
        try {
            response.setData(redisService.set(key,value));
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage(e.getMessage());
            LOGGER.error(e.getMessage(),e);
        }
        return response;
    }

    @RequestMapping("/get/{key}")
    @ResponseBody
    public Response get(@PathVariable("key") String key) {
        Response response = new Response();
        try {
            response.setData(redisService.get(key));
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage(e.getMessage());
            LOGGER.error(e.getMessage(),e);
        }
        return response;
    }
}
