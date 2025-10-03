package com.murtaza.mymart.Notifications.controller;

import com.murtaza.mymart.Notifications.model.SendMessageDTO;
import com.murtaza.mymart.Notifications.service.WhatsAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private WhatsAppService whatsAppService;

    @PostMapping("/wati")
    public ResponseEntity<String> sendNotification(@RequestBody SendMessageDTO sendMessageDTO) {
        String s = whatsAppService.sendMessage(sendMessageDTO.getWhatsappNumber(), sendMessageDTO.getMessage());
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}
