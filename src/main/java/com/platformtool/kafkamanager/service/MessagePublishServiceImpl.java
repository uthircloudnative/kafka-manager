package com.platformtool.kafkamanager.service;

import com.platformtool.kafkamanager.model.MessageResponse;
import com.platformtool.kafkamanager.model.UserProfile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * MessagePublishServiceImpl
 *
 * @author Uthiraraj Saminathan
 */
@Service
public class MessagePublishServiceImpl implements MessagePublishService {

    private final KafkaTemplate<String, UserProfile> kafkaTemplate;

    public MessagePublishServiceImpl(KafkaTemplate<String, UserProfile> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public MessageResponse publishMessage(int messageCount) {

        for(int i = 0; i < messageCount; i++){
            CompletableFuture<SendResult<String, UserProfile>> future =  kafkaTemplate.send("user-profile-topic-1", generateRandomUserProfile());

            future.whenComplete((result, ex) -> {
                if (ex != null) {
                    System.out.println("Unable to publish message: " + ex.getMessage());
                    System.out.println("Message published successfully: " + result.getProducerRecord().value());
                } else {
                    System.out.println("Message published successfully to topic: " + result.getRecordMetadata().topic());
                    System.out.println("Message published successfully: " + result.getRecordMetadata().offset());
                }
            });
        }

        return new MessageResponse("Message published successfully");
    }


    private UserProfile generateRandomUserProfile() {
        String[] firstNames = {"John", "Jane", "Mike", "Emily"};
        String[] lastNames = {"Doe", "Smith", "Johnson", "Williams"};
        String[] emailDomains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com"};
        String[] poneNumebers = {"453-656-9872", "243-654-7898", "908-675-2341", "909-432-0987"};

        Random random = new Random();

        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        LocalDate dateOfBirth = LocalDate.of(random.nextInt(50) + 1970, Month.of(random.nextInt(12) + 1), random.nextInt(28) + 1);
        String emailId = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + emailDomains[random.nextInt(emailDomains.length)];
        String phoneNumber = poneNumebers[random.nextInt(4)];

        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName(firstName);
        userProfile.setLastName(lastName);
        userProfile.setDateOfBirth(dateOfBirth);
        userProfile.setEmail(emailId);
        userProfile.setPhoneNumber(phoneNumber);

        return userProfile;
    }

}
