package com.example.restaurant.naver;

import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchLocalReq;
import lombok.extern.slf4j.Slf4j;
<<<<<<< HEAD
=======
import org.junit.jupiter.api.Assertions;
>>>>>>> 26b162e (test)
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

<<<<<<< HEAD
=======
@Slf4j
>>>>>>> 26b162e (test)
@SpringBootTest
public class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void searchLocalTest(){
        var search = new SearchLocalReq();
        search.setQuery("갈비집");

        var result = naverClient.localSearch(search);
<<<<<<< HEAD
        System.out.println(result);
=======
        log.info("result : {}", result);

        Assertions.assertNotNull(result.getItmes().stream().findFirst()
                .get().getCategory());
>>>>>>> 26b162e (test)
    }

    @Test
    public void searchImageTest(){
        var search = new SearchImageReq();
        search.setQuery("갈비집");

        var result = naverClient.imageSearch(search);
        System.out.println(result);
    }
}
