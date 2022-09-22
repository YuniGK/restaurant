package com.example.restaurant2.naver;

import com.example.restaurant2.naver.dto.SearchImageReq;
import com.example.restaurant2.naver.dto.SearchLocalReq;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void searchLocalTest(){
        var search = new SearchLocalReq();
        search.setQuery("갈비집");

        var result = naverClient.localSearch(search);

        log.info("result : {}", result);

        Assertions.assertNotNull(result.getItmes().stream().findFirst()
                .get().getCategory());
    }

    @Test
    public void searchImageTest(){
        var search = new SearchImageReq();
        search.setQuery("갈비집");

        var result = naverClient.imageSearch(search);
        System.out.println(result);
    }
}
