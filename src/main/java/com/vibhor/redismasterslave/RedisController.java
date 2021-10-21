package com.vibhor.redismasterslave;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/redis-master")
public class RedisController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/{value}")
    public void insertKey(@PathVariable String value) {
        this.redisTemplate.opsForValue().set("test",value);
    }

    @GetMapping("/get")
    public String getKey() {
        return this.redisTemplate.opsForValue().get("test");
    }

}
