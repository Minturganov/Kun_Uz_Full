package com.example.kun_uz_full.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionDTO {
    private Integer id;
    private Integer orderNumber;
    private String name;
    private String nameUz;
    private String nameRu;
    private String nameEn;
    private LocalDateTime createdDate;

}
