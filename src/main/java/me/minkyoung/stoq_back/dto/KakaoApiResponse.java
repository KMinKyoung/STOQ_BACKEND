package me.minkyoung.stoq_back.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoApiResponse {

    private List<Document> documents;

    public Document getFirstDocument() {
        return documents != null && !documents.isEmpty() ? documents.get(0) : null;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Document {
        private String x; // 경도
        private String y; // 위도
    }
}