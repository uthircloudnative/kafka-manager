package com.platformtool.kafkamanager.model;

import lombok.Data;

import java.time.LocalDate;

/**
 * UserProfile
 *
 * @author Uthiraraj Saminathan
 */
@Data
public class UserProfile {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
}
