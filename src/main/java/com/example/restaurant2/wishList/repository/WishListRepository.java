package com.example.restaurant2.wishList.repository;

import com.example.restaurant2.db.MemoryDbRepositoryAbstract;
import com.example.restaurant2.wishList.entity.WishListEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {

}
