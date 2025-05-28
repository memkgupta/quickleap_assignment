package com.quickleap_assignment.user_service.dtos.user;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
public class UserDTO  {

    private String id;
    private String username;
    private String email;
    private String name;
    private String bio;
    private String avatar;
    private String channelId;

    private Timestamp createdAt;


}