package com.example.kun_uz_full.service;

import com.example.kun_uz_full.dto.ProfileDTO;
import com.example.kun_uz_full.entity.ProfileEntity;
import com.example.kun_uz_full.enums.ProfileStatus;
import com.example.kun_uz_full.exp.AppBadRequestException;
import com.example.kun_uz_full.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    /* 1*/

    public ProfileDTO create(ProfileDTO dto) {
        // TODO check

        isValidProfile(dto);

        Optional<ProfileEntity> profileByEmail = profileRepository.findByEmail(dto.getEmail());
        if (profileByEmail.isPresent()){
            throw new AppBadRequestException("Email already exists");
        }

        Optional<ProfileEntity> profileByName = profileRepository.findByPhone(dto.getPhone());
        if (profileByName.isPresent()){
            throw new AppBadRequestException("Phone already exists");
        }


        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setRole(dto.getRole());
        profileRepository.save(entity);

        dto.setId(entity.getId());
//        dto.setVisible(entity.isVisible());
        dto.setCreateDate(entity.getCreatedDate());
//        dto.setPhotoId(entity.getPhotoId());
        return dto;
    }

    void isValidProfile(ProfileDTO dto){
        if (dto.getName()==null || dto.getName().isBlank() || dto.getName().length()<3){
            throw new AppBadRequestException("Name required");
        }
    }

    // TODO check phone
    //...
    //..

    /* 2 */

    public Boolean update(Integer profileId, ProfileDTO dto){
        isValidProfile(dto);
        ProfileEntity entity = get(profileId);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());

        profileRepository.save(entity);
        return true;
    }

    public Boolean updateDetail(Integer profileId, ProfileDTO dto){
        isValidProfile(dto);
        int effectedRows = profileRepository.updateDetail(profileId, dto.getName(), dto.getSurname());
        return effectedRows == 1;
    }
    public ProfileEntity get (Integer id){
        return profileRepository.findById(id).orElseThrow(()->{
            throw new AppBadRequestException("Profile not found");
        });
    }


    public List<ProfileDTO> getList(){
//        List<ProfileEntity> entities = profileRepository.findAllByVisibleTrue();
//
//        List<ProfileDTO> dtoList = new LinkedList<>();
//
//        entities.forEach(c->
//                dtoList.add(ToDTO(c)));
//        return dtoList;

        return profileRepository.findAllByVisibleTrue().stream()
                .map(this::ToDTO).collect(Collectors.toList());
    }


    public boolean delete(Integer id){
        return profileRepository.delete(id) == 1;
    }

    private ProfileDTO ToDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        dto.setCreateDate(entity.getCreatedDate());

        return dto;
    }

}
