package com.example.jpamanytoone.controller;

import com.example.jpamanytoone.model.Kommune;
import com.example.jpamanytoone.model.Region;
import com.example.jpamanytoone.repository.RegionRepository;
import com.example.jpamanytoone.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RegionRestController {

    @Autowired
    ApiServiceGetRegioner apiServicegetRegioner;

    @Autowired
    RegionRepository regionRepository;

    //henter data fra api
    @GetMapping("/getregioner")
    public List<Region> getRegioner(){
        List<Region> lstRegion = apiServicegetRegioner.getRegioner();
        return lstRegion;
    }
    //henter regioner fra database
    @GetMapping("/regioner")
    public List<Region> getRegion(){
        return regionRepository.findAll();
    }
    //rediger region
    @PutMapping("/region")
    public Region putRegion(@RequestBody Region region){
        System.out.println(region);
        return regionRepository.save(region);
    }
    //opret region
    @PostMapping("/region")
    @ResponseStatus(HttpStatus.CREATED)
    public Region postRegion(@RequestBody Region region){
        System.out.println(region);
        return regionRepository.save(region);
    }
    //Opgave 1:
    //delete kommune
    @DeleteMapping("/region")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRegion(@RequestBody Region region){
        regionRepository.delete(region);
        System.out.println(region + " Deleted");
    }
    //Opgave 2:
    @PostMapping("kommunenames")
    public List<String> getKommuneNames(@RequestBody Region region) {
        if (region == null || region.getKode() == null) {
            throw new IllegalArgumentException("Region kode skal angives i anmodningen.");
        }

        // Find den ønskede region baseret på regionens kode
        Region foundRegion = regionRepository.findById(region.getKode())
                .orElseThrow(() -> new ExpressionException("Region not found with kode: " + region.getKode()));

        // Hent navne på alle kommuner tilknyttet den fundne region
        List<String> kommunenavne = foundRegion.getKommuner().stream()
                .map(Kommune::getNavn)
                .collect(Collectors.toList());

        return kommunenavne;
    }









}
