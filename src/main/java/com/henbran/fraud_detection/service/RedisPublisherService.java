package com.henbran.fraud_detection.service;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

@Service
public class RedisPublisherService {
    // public static final Jedis jedis = new Jedis("localhost", 6379);

    public static void publish(String channel, String message){
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.publish(channel, message);
        jedis.close();
        System.out.println("Published to channel " + channel + " message: " + message);
    }

    public static String subscribe(String channel){
        Jedis jedis = new Jedis("localhost", 6379);
        JedisPubSub jedisPubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message){
                System.out.println("Received message: " + message + " from channel " + channel);
            }
        };
        
        jedis.subscribe(jedisPubSub, channel);
        jedis.close();
        return "Subscribed to channel " + channel;

    }
}
