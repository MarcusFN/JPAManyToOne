package com.example.jpamanytoone;

import com.example.jpamanytoone.model.Region;
import com.example.jpamanytoone.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionController {

    @Autowired
    ApiServiceGetRegioner apiServicegetRegioner;

    @GetMapping
    public List<Region> getRegioner(){
        List<Region> lstRegioner = apiServicegetRegioner.getRegioner();
        return lstRegioner;
    }

}
