package com.chanheng.demo;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class RestTemplateService {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    public static ResponseEntity<String> get(final String url, final Map<String, String> headers) {
        final var header = buildHttpEntity(headers);
        return REST_TEMPLATE.exchange(url, HttpMethod.GET, header, String.class);
    }

    public static <T> ResponseEntity<T> get(final String url,
                                            final Map<String, String> headers,
                                            final Class<T> clazz) {
        final var header = buildHttpEntity(headers);
        return REST_TEMPLATE.exchange(url, HttpMethod.GET, header, clazz);
    }

    private static HttpEntity<String> buildHttpEntity(final Map<String, String> headersMap) {
        final var headers = buildHeader(headersMap);
        return new HttpEntity<>(headers);
    }

    private static HttpHeaders buildHeader(final Map<String, String> headersMap) {
        final var headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (headersMap != null) {
            headersMap.forEach((k, v) -> headers.add(k, v == null ? "" : v));
        }
        return headers;
    }
}
