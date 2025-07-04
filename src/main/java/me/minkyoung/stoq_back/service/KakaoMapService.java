package me.minkyoung.stoq_back.service;

import lombok.RequiredArgsConstructor;
import me.minkyoung.stoq_back.dto.KakaoApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class KakaoMapService {

    @Value("${kakao.rest-api-key}")
    private String kakaoApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public Double[] getCoordinatesFromAddress(String address) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://dapi.kakao.com")
                .path("/v2/local/search/address.json")
                .queryParam("query", address)
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoApiKey);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<KakaoApiResponse> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                entity,
                KakaoApiResponse.class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            KakaoApiResponse.Document document = response.getBody().getFirstDocument();
            if (document != null) {
                Double latitude = Double.parseDouble(document.getY());
                Double longitude = Double.parseDouble(document.getX());
                return new Double[]{latitude, longitude};
            }
        }

        // 실패 시 기본 좌표 반환 또는 예외 처리
        return new Double[]{0.0, 0.0};
    }
}