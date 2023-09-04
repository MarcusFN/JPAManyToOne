package com.example.jpamanytoone.controller;

import com.example.jpamanytoone.model.Kommune;
import com.example.jpamanytoone.model.Region;
import com.example.jpamanytoone.repository.KommuneRepository;
import com.example.jpamanytoone.repository.RegionRepository;
import com.example.jpamanytoone.service.ApiServiceGetKommune;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KommuneRestController {

    @Autowired
    ApiServiceGetKommune apiServiceGetKommune;

    @Autowired
    KommuneRepository kommuneRepository;

    //henter kommuner fra api
    @GetMapping("/getkommuner")
    public List<Kommune> getKommuner(){
        List<Kommune> lstKommuner = apiServiceGetKommune.getKommuner();
        return lstKommuner;
    }
    //henter kommuner fra database
    @GetMapping("/kommuner")
    public List<Kommune> getKommune(){
        return kommuneRepository.findAll();
    }
    //redigere kommuner
    @PutMapping("/kommuner")
    public Kommune putKommune(@RequestBody Kommune kommune){
        System.out.println(kommune);
        return kommuneRepository.save(kommune);
    }
    //opret region
    @PostMapping("/kommuner")
    @ResponseStatus(HttpStatus.CREATED)
    public Kommune postKommune(@RequestBody Kommune kommune){
        System.out.println(kommune);
        return kommuneRepository.save(kommune);
    }
    //Opgave 1:
    //delete kommune
    @DeleteMapping("/kommune")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKommune(@RequestBody Kommune kommune){
        kommuneRepository.delete(kommune);
        System.out.println(kommune + " Deleted");
    }

}
