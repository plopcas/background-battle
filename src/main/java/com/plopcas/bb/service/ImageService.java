package com.plopcas.bb.service;

import com.plopcas.bb.model.Image;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {
    private static Logger log = LoggerFactory.getLogger(ImageService.class);

    @Value("${imgbb.apiKey}")
    private String imgbbApiKey;

    @Value("${imgbb.uploadUrl}")
    private String uploadUrl;

    @Autowired
    private RestTemplate restTemplate;

    public Image uploadImage(MultipartFile image) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var map = new LinkedMultiValueMap<String, Object>();
        map.add("key", imgbbApiKey);
        try {
            map.add("image", StringUtils.newStringUtf8(Base64.encodeBase64(image.getBytes(), false)));
        } catch (IOException e) {
            throw new RuntimeException("Error getting bytes from image");
        }

        var request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);

        ResponseEntity<Image> response = restTemplate.postForEntity(uploadUrl, request, Image.class);

        log.info("Image uploaded");

        return response.getBody();
    }
}
