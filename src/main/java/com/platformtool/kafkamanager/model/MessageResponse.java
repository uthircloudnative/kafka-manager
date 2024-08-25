package com.platformtool.kafkamanager.model;

import lombok.Data;

/**
 * MessageResponse
 *
 * @author Uthiraraj Saminathan
 */
@Data
public class MessageResponse {

    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
