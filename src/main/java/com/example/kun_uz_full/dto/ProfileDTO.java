package com.example.kun_uz_full.dto;

import com.example.kun_uz_full.enums.ProfileRole;
import com.example.kun_uz_full.enums.ProfileStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProfileDTO {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private ProfileRole role;
    private LocalDateTime createDate;
    private ProfileStatus status;

}
