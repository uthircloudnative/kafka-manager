package com.platformtool.kafkamanager.service;

import com.platformtool.kafkamanager.model.MessageResponse;

/**
 * MessagePublishService
 *
 * @author Uthiraraj Saminathan
 */
public interface MessagePublishService {

    MessageResponse publishMessage(int messageCount);
}
