package com.example.kun_uz_full.controller;

import com.example.kun_uz_full.dto.ProfileDTO;
import com.example.kun_uz_full.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping(value = "")
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO dto){
        return ResponseEntity.ok(profileService.create(dto));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Boolean> update(@RequestBody ProfileDTO dto,
                                          @PathVariable("id") Integer id){
        return ResponseEntity.ok(profileService.update(id,dto));
    }

    @PutMapping(value = "/detail/{id}")
    public ResponseEntity<Boolean> updateDetail(@RequestBody ProfileDTO dto,
                                          @PathVariable("id") Integer id){
        return ResponseEntity.ok(profileService.updateDetail(id,dto));
    }

    @GetMapping(value = "")
    public ResponseEntity<List<ProfileDTO>> getAll(){
        return ResponseEntity.ok(profileService.getList());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(profileService.delete(id));
    }

}
