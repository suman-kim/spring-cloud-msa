package org.example.catalogservice;


import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/catalog-service")
public class CatalogController {

    Environment env;
    private final CatalogService catalogService;


    @GetMapping("/health-check")
    public String status() {
        return String.format("It's Working in Catalog Service on PORT %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<CatalogDto>> getCatalogs(){
        List<CatalogDto> catalogDtoList = catalogService.findAll();


        return new ResponseEntity<>(catalogDtoList, HttpStatus.OK);
    }

}
