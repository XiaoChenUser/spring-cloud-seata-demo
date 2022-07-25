package com.example.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;

@SpringBootTest
public class GatewayApplicationTests {
    @Test
    void testTimezone(){
        System.out.println(ZonedDateTime.now());
    }
}
