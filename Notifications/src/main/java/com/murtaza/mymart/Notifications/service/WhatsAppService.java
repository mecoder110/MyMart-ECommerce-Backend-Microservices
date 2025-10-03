package com.murtaza.mymart.Notifications.service;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Log
public class WhatsAppService {



    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${wati.api.key}")
    private String apiKey;  // put in application.properties
    private final String baseUrl = "https://app-server.wati.io/api/v1/sendSessionMessage/";

    public String sendMessage(String phoneNumber, String message) {
        String url = baseUrl + phoneNumber;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("messageText", message);
        log.info("===>>> : "+message);
        log.info("===>>> : "+phoneNumber);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        log.info("===>>> : "+response.getBody());
        return response.getBody();
    }
}
