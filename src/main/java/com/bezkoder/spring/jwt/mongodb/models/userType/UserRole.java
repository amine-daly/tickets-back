package com.bezkoder.spring.jwt.mongodb.models.userType;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum UserRole {
    ADMIN,
    CONSUMER;
}