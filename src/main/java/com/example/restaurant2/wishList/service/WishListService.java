package com.example.restaurant2.wishList.service;

import com.example.restaurant2.naver.NaverClient;
import com.example.restaurant2.naver.dto.SearchImageReq;
import com.example.restaurant2.naver.dto.SearchLocalReq;
import com.example.restaurant2.wishList.dto.WishListDto;
import com.example.restaurant2.wishList.entity.WishListEntity;
import com.example.restaurant2.wishList.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public WishListDto add(WishListDto wishListDto) {
        var entity = dtoToEntity(wishListDto);
        var saveEntity = wishListRepository.save(entity);

        return entityToDto(saveEntity);
    }

    private WishListEntity dtoToEntity(WishListDto wishListDto){
        var entity = new WishListEntity();

        entity.setIndex(wishListDto.getIndex());
        entity.setTitle(wishListDto.getTitle());
        entity.setCategory(wishListDto.getCategory());
        entity.setAddress(wishListDto.getAddress());
        entity.setReadAddress(wishListDto.getReadAddress());
        entity.setHomePageLink(wishListDto.getHomePageLink());
        entity.setImgeLink(wishListDto.getImgeLink());
        entity.setVisit(wishListDto.isVisit());
        entity.setVisitCount(wishListDto.getVisitCount());
        entity.setLastVistDate(wishListDto.getLastVistDate());

        return entity;
    }

    private WishListDto entityToDto(WishListEntity wishListEntity){
        var dto = new WishListDto();

        dto.setIndex(wishListEntity.getIndex());
        dto.setTitle(wishListEntity.getTitle());
        dto.setCategory(wishListEntity.getCategory());
        dto.setAddress(wishListEntity.getAddress());
        dto.setReadAddress(wishListEntity.getReadAddress());
        dto.setHomePageLink(wishListEntity.getHomePageLink());
        dto.setImgeLink(wishListEntity.getImgeLink());
        dto.setVisit(wishListEntity.isVisit());
        dto.setVisitCount(wishListEntity.getVisitCount());
        dto.setLastVistDate(wishListEntity.getLastVistDate());

        return dto;
    }

    public List<WishListDto> findAll() {
        return wishListRepository.listAll().stream().map(it -> entityToDto(it)).collect(Collectors.toList());
    }

    public void delete(int index) {
        wishListRepository.deleteById(index);
    }

    public void addVisit(int index){
        var wishItem = wishListRepository.findById(index);

        if (wishItem.isPresent()){
            var item = wishItem.get();
            item.setVisit(true);
            item.setVisitCount(item.getVisitCount());
        }
    }
}
