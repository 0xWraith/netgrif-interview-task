package com.wraith.netgrif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NetgrifApplication
{
    public static void main(String[] args) { SpringApplication.run(NetgrifApplication.class, args); }
}
