package com.example.demo;

import com.google.gson.GsonBuilder;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    Encoder feignEncoder() {
        return new JacksonEncoder();
    }

    @Bean
    Decoder feignDecoder() {
        return new JacksonDecoder();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        //设置日志
        return Logger.Level.FULL;
    }
}
