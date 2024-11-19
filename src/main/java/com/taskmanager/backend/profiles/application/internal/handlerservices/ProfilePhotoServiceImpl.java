package com.taskmanager.backend.profiles.application.internal.handlerservices;

import com.taskmanager.backend.profiles.domain.services.ProfilePhotoService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: Backend
 * Date: 19/11/24 @ 02:11
 */
@Service
public class ProfilePhotoServiceImpl implements ProfilePhotoService {

    private static final String RANDOM_USER_API_URL = "https://randomuser.me/api/";

    @Override
    public String getRandomProfilePhoto() {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(RANDOM_USER_API_URL)
                .queryParam("inc", "picture");

        Map<String, Object> response = restTemplate.getForObject(uriBuilder.toUriString(), Map.class);
        if (response != null && response.containsKey("results")) {
            Map<String, Object> results = ((List<Map<String, Object>>) response.get("results")).get(0);
            Map<String, String> picture = (Map<String, String>) results.get("picture");
            return picture.get("large");
        }
        return null;
    }
}
