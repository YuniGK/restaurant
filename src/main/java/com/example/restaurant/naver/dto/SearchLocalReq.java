package com.example.restaurant.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchLocalReq {
    private String query = "";
    private int display = 1;
    private int start = 1;
    private String sort = "random";

    /*
    MultiValueMap - 키의 중복이 허용된다. 즉 같은 키를 가진 파라미터 값이 여러개일 경우를 대비
    LinkedMultiValueMap - LinkedList의 각 키에 대해 여러 값을 저장하는 LinkedHashMap을 래핑
    */
    public MultiValueMap<String, String> toMultiValueMap(){
        var map = new LinkedMultiValueMap<String, String>();

        map.add("query", query);
        map.add("display", String.valueOf(display));
        map.add("start", String.valueOf(start));
        map.add("sort", sort);

        return map;
    }
}
