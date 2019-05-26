package com.sgai.property.redisTest;

import com.sgai.property.application.SgaiPropertyApplication;
import com.sgai.property.common.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ppliu
 * created in 2019/3/15 10:21
 */
@SpringBootTest(classes = SgaiPropertyApplication.class)
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testSave() {
        redisUtil.set("aaa","bbb");
        System.out.println(redisUtil.get("aaa"));
    }
}
