package com.murtaza.mymart.Notifications.model;

import lombok.Data;

@Data
public class SendMessageDTO {

    private String whatsappNumber;
    private String message;
}
