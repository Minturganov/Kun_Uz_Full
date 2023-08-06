package com.example.kun_uz_full.repository;

import com.example.kun_uz_full.entity.ProfileEntity;
import com.example.kun_uz_full.enums.ProfileStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {
    Optional<ProfileEntity> findByEmail(String email);

    Optional<ProfileEntity> findByPhone(String phone);

    @Transactional
    @Modifying
    @Query("update ProfileEntity set name=?2, surname=?2 where id=?1")
    int updateDetail(Integer id, String name, String surname);

    List<ProfileEntity> findAllByVisibleTrue();

    @Transactional
    @Modifying
    @Query("update ProfileEntity set visible = false where id=:id")
    int delete(@Param("id") Integer id);
}
