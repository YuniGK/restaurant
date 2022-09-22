package com.example.restaurant2.naver;

import com.example.restaurant2.naver.dto.SearchImageReq;
import com.example.restaurant2.naver.dto.SearchImageRes;
import com.example.restaurant2.naver.dto.SearchLocalReq;
import com.example.restaurant2.naver.dto.SearchLocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NaverClient {

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @Value("${naver.url.search.image}")
    private String naverimageSearchUrl;

    public SearchLocalRes localSearch(SearchLocalReq searchLocalReq){
        //요청 주소
        var uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
                .queryParams(searchLocalReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        //헤더
        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        //request
        var httpEntity = new HttpEntity<>(headers);
        //response
        var responseType = new ParameterizedTypeReference<SearchLocalRes>(){};

        //결과
        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return responseEntity.getBody();
    }

    public SearchImageRes imageSearch(SearchImageReq searchImageReq){
        //요청 주소
        var uri = UriComponentsBuilder.fromUriString(naverimageSearchUrl)
                .queryParams(searchImageReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        //헤더
        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        //request
        var httpEntity = new HttpEntity<>(headers);
        //response
        var responseType = new ParameterizedTypeReference<SearchImageRes>(){};

        //결과
        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return responseEntity.getBody();
    }
}
