package com.platformtool.kafkamanager.controller;

import com.platformtool.kafkamanager.model.MessageResponse;
import com.platformtool.kafkamanager.service.MessagePublishService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * MessagePublishController
 *
 * @author Uthiraraj Saminathan
 */
@RestController
public class MessagePublishController {

    private final MessagePublishService messagePublishService;

    public MessagePublishController(MessagePublishService messagePublishService) {
        this.messagePublishService = messagePublishService;
    }

    @GetMapping("/publish/{messageCount}")
    public MessageResponse publishMessage(@PathVariable int messageCount) {
       return messagePublishService.publishMessage(messageCount);
    }

}
