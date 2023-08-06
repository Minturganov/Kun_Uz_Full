package com.example.kun_uz_full.service;

import com.example.kun_uz_full.dto.ProfileDTO;
import com.example.kun_uz_full.dto.RegionDTO;
import com.example.kun_uz_full.entity.RegionEntity;
import com.example.kun_uz_full.enums.Language;
import com.example.kun_uz_full.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public RegionDTO add(RegionDTO dto) {

        RegionEntity entity = new RegionEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setCreatedDate(LocalDateTime.now());
        regionRepository.save(entity);
        dto.setId(entity.getId());

        return dto;
    }

    public Boolean update(Integer id, RegionDTO dto) {
        int effect = regionRepository.updateById(id, dto.getOrderNumber(),
                dto.getNameUz(), dto.getNameRu(), dto.getNameEn());
        return effect == 1;
    }

    public Boolean delete(Integer id) {
        return regionRepository.delete(id) == 1;
    }

    public List<RegionDTO> getList() {

        List<RegionEntity> entities = regionRepository.findAllByVisibleTrue();
        List<RegionDTO> dtoList = new LinkedList<>();

        entities.forEach(c ->
                dtoList.add(ToDTO(c)));
        return dtoList;
    }



    private RegionDTO ToDTO(RegionEntity c) {
        RegionDTO dto = new RegionDTO();
        dto.setOrderNumber(c.getOrderNumber());
        dto.setNameUz(c.getNameUz());
        dto.setNameRu(c.getNameRu());
        dto.setNameEn(c.getNameEn());
        dto.setCreatedDate(c.getCreatedDate());
        return dto;
    }

    public List<RegionDTO> getByLan(Language lan) {
        List<RegionDTO> dtoList = new LinkedList<>();
        regionRepository.findAllByVisibleTrue().forEach(entity ->{
            dtoList.add(toDTO(entity,lan));
        });
        return dtoList;
    }

    private RegionDTO toDTO(RegionEntity c, Language lan) {
        RegionDTO dto = new RegionDTO();
        dto.setId(c.getId());
        dto.setOrderNumber(c.getOrderNumber());
        switch (lan){
            case en ->dto.setName(c.getNameEn());
            case ru ->dto.setName(c.getNameRu());
            case uz ->dto.setName(c.getNameUz());
            default -> dto.setName(c.getNameUz());
        }
        dto.setCreatedDate(c.getCreatedDate());
        return dto;
    }
}
