package com.example.kun_uz_full.controller;

import com.example.kun_uz_full.dto.ProfileDTO;
import com.example.kun_uz_full.dto.RegionDTO;
import com.example.kun_uz_full.enums.Language;
import com.example.kun_uz_full.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping(value = {"","/"})
    public ResponseEntity<RegionDTO> create(@RequestBody RegionDTO dto) {
        return ResponseEntity.ok(regionService.add(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Integer id,
                                        @RequestBody RegionDTO regionDTO) {
        Boolean b = regionService.update(id, regionDTO);
        return ResponseEntity.ok(b);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
        Boolean result = regionService.delete(id);
        if (result) {
            return ResponseEntity.ok("Region deleted!!!");
        }
        return ResponseEntity.badRequest().body("Region not faund");
    }

//    public ResponseEntity<List<RegionDTO>> getAll(){
//        return ResponseEntity.ok(regionService.getList());

    @GetMapping("/")
    public List<RegionDTO> all() {
        return regionService.getList();
    }


    @GetMapping("/lan")
    public ResponseEntity<List<RegionDTO>> getBylan(@RequestParam(value = "lan",defaultValue = "uz")Language lan) {
        return ResponseEntity.ok(regionService.getByLan(lan));
    }

}

