package com.example.restaurant.wishList.service;

import com.example.restaurant.naver.NaverClient;
import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.wishList.dto.WishListDto;
import com.example.restaurant.wishList.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WishListService {
    private final NaverClient naverClient;
    private final WishListRepository wishListRepository;

    public WishListDto search(String query){
        //지역 검색
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);

        var searchLocalRes = naverClient.localSearch(searchLocalReq);

        if(searchLocalRes.getTotal() > 0 && searchLocalRes.getItmes() != null){
            log.info("size : {}",searchLocalRes.getTotal());
            log.info("items : {}",searchLocalRes.getItmes());

            var localItem = searchLocalRes.getItmes().stream().findFirst().get();

            //특수문자 등이 있는 것을 지워준다.
            var imgeQuery = localItem.getTitle().replaceAll("<[^>]*>", "");

            //이미지 검색
            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imgeQuery);

            var searchImageRes = naverClient.imageSearch(searchImageReq);

            if(searchImageRes.getTotal() > 0 && searchImageRes.getItmes() != null){
                var imageItem = searchImageRes.getItmes().stream().findFirst().get();

                //결과 리턴
                var result = new WishListDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setReadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImgeLink(imageItem.getLink());

                return result;
            }

        }

        return new WishListDto();

    }
}
