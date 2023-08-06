package com.example.kun_uz_full.repository;

import com.example.kun_uz_full.entity.RegionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends CrudRepository<RegionEntity, Integer> {

    @Transactional
    @Modifying
    @Query("update RegionEntity as r set r.orderNumber =:order_number," +
            "r.nameUz =:name_uz, r.nameRu =:name_ru, r.nameEn =:name_en where r.id =:id")
    int updateById(@Param("id") Integer id,
                   @Param("order_number") Integer order_number,
                   @Param("name_uz") String name_uz,
                   @Param("name_ru") String name_ru,
                   @Param("name_en") String name_en);

    @Transactional
    @Modifying
    @Query("update RegionEntity set visible = false where id=?1")
    int delete(Integer id);

    List<RegionEntity> findAllByVisibleTrue();


//    @Query("select r from RegionEntity as r where r.id =:id")
//    Optional<RegionEntity> getById(Integer id);
//
//
//    // TODO
//    @Query("select r.name_uz from RegionEntity as r")
//    Optional<RegionEntity> getByName_uz();


}
