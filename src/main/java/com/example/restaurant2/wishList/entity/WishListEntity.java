package com.example.restaurant2.wishList.entity;

import com.example.restaurant2.db.MemoryDbEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishListEntity extends MemoryDbEntity {
    private String title;               //음식명, 장소명
    private String category;            //카테고리
    private String address;             //주소
    private String readAddress;         //도로명
    private String homePageLink;        //홈페이지 주소
    private String imgeLink;            //음식, 가게 이미지 주소
    private boolean isVisit;            //방문여부
    private int visitCount;             //방문 카운트
    private LocalDateTime lastVistDate; //마지막 방문일자
}
