package com.henbran.fraud_detection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henbran.fraud_detection.service.RedisPublisherService;
import com.henbran.fraud_detection.utils.Constants;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("test-pubsub")
public class RedisPubSubController {
    
   

    @GetMapping("/pub")
    public ResponseEntity<String> publishMessage() throws Exception{
        RedisPublisherService.publish(Constants.REDIS_CHANNEL_STRING, "Mensagem para o outro serviço..");
        return ResponseEntity.ok("Mensagem publicada com sucesso!");
    }

    @GetMapping("/sub")
    public ResponseEntity<String> subscribe() {
        String mesageFound = RedisPublisherService.subscribe(Constants.REDIS_CHANNEL_STRING);
        
        return ResponseEntity.ok(mesageFound);
    }
    

}
