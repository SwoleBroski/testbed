package com.litmusintegration.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.litmusintegration.demo.model.AuthenticationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;


@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationProperties properties;
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    private String payload;
    private String authApiUrl;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private JsonNode authResponse;
    private String authToken;

    public void refreshToken() {
        setPayload();
        setAuthApiUrl();
        authenticate();
    }

    private void setPayload() {
        payload = "{\"username\":\"" + properties.getLitmus_username() +
                "\",\"password\":\"" + properties.getLitmus_password() + "\"}";

        log.info("Refresh Token payload: " + payload);
    }

    private void setAuthApiUrl() {
        authApiUrl = properties.getAuth_api_url() + ':' + properties.getAuth_api_port() + "/login";

        log.info("Authorization URL: " + authApiUrl);
    }

    private void authenticate() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            HttpEntity<String> request =
                    new HttpEntity<>(payload, headers);
            String result = restTemplate.postForObject(authApiUrl, request, String.class);
            authResponse = objectMapper.readTree(result);
            authToken = authResponse.get("access_token").textValue();
            log.info("Authentication successful, access token: " + authToken);
        } catch (JsonProcessingException e) {
            log.error("Json processing exception");
            throw new RuntimeException(e);
        } catch (RestClientException e) {
            log.error("Rest Client exception");
            throw new RuntimeException(e);
        }
    }

    public String getAuthToken() {
        return authToken;
    }
}
