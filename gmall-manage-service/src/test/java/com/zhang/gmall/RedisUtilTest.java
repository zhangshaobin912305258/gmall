package com.zhang.gmall;

import com.zhang.gmall.config.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.UUID;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RedisUtilTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void contextLoads() {
        Jedis jedis = redisUtil.getJedis();
        System.out.println(jedis.getDB());
    }

    @Test
    public void testRedisSetNx() {
        Jedis jedis = redisUtil.getJedis();
        String token = UUID.randomUUID().toString();
        String ok = jedis.set("test:lock", token, "nx", "px", 10 * 1000);
        if (StringUtils.isNotEmpty(ok) && "OK".equals(ok)) {
            //获取分布式锁成功
            log.info("获取分布式锁成功");
            //业务处理  此处可能有线程安全问题，导致误删除其他线程拥有的锁
            String tokenValue = jedis.get("test:lock");
            if (StringUtils.isNotEmpty(tokenValue) && token.equals(tokenValue)) {
                //执行lua脚本
                String script ="if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                jedis.eval(script, Collections.singletonList("test:lock"),Collections.singletonList(token));
                jedis.del("test:lock");
            }
        } else {
            //获取分布式锁失败，自旋，线程休眠几秒后，重新尝试访问本方法
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //如果方法有返回值，需要写成 return testRedisSetNx();
            testRedisSetNx();
        }
    }
}
