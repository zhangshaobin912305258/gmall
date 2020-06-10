package com.zhang.gmall;

import com.zhang.gmall.config.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RedissonTest {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void testRedisson() {
        Jedis jedis = redisUtil.getJedis();
        String k = jedis.get("k");
        if (StringUtils.isEmpty(k)) {
            k = "1";
        }
        System.out.println(k);
        jedis.set("k", String.valueOf(Integer.parseInt(k) + 1));
        jedis.close();
        RLock lock = redissonClient.getLock("anyLock");
        //最常见的使用方法
        lock.lock();
        //也可以指定加锁的时间 加锁后，到指定时间后，自动释放锁
        //lock.lock(10, TimeUnit.SECONDS);

        lock.unlock();
    }
}
