package com.zhang.gmall.manage.controller;

import com.zhang.gmall.manage.config.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
@RequestMapping("/redisson")
@RequiredArgsConstructor
public class RedissonController {

    private final RedisUtil redisUtil;

    private final RedissonClient redissonClient;

    @RequestMapping("/testRedisson")
    public String testRedisson() {
        Jedis jedis = redisUtil.getJedis();
        RLock lock = redissonClient.getLock("anyLock");
        lock.lock();
        try{
            String k = jedis.get("k");
            if (StringUtils.isEmpty(k)) {
                k = "0";
            }
            System.out.println(k);
            jedis.set("k", String.valueOf(Integer.parseInt(k) + 1));
        } finally {
            jedis.close();
            lock.unlock();
        }
        return "success";
    }
}
