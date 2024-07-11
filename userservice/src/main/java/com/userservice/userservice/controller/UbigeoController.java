/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.userservice.userservice.controller;

/**
 *
 * @author Usuario
 */

import com.userservice.userservice.dto.UbigeoRequest;
import com.userservice.userservice.dto.UbigeoResponse;
import com.userservice.userservice.service.UbigeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ubigeos")
@RequiredArgsConstructor
public class UbigeoController {
    private final UbigeoService ubigeoService;
    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> insert(@RequestBody UbigeoRequest newUbigeo){
        this.ubigeoService.insert(newUbigeo);
        UbigeoResponse ubigeo = new UbigeoResponse();
        ubigeo.setIdUbigeo(newUbigeo.getIdUbigeo());
        ubigeo.setDistrito(newUbigeo.getDistrito());
        ubigeo.setDepartamento(newUbigeo.getDepartamento());
        ubigeo.setProvincia(newUbigeo.getProvincia());
        ubigeo.setLongitud(newUbigeo.getLongitud());
        ubigeo.setLatitud(newUbigeo.getLatitud());

        Map<String, Object> response = new HashMap<>();
            response.put("message", "Ubigeo creado con exito");
            response.put("status", 200);
            response.put("ubigeo", ubigeo);

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("get")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> get_ubigeos(){
        /*
        ApiResponse<List<UbigeoDTO>> response = new ApiResponse<>();
            response.setMessage("Lista generada con exito");
            response.setStatus(200);
            response.setData(ubigeoService.getAllUbigeos());
            return ResponseEntity.status(200).body(response); */

        Map<String, Object> response = new HashMap<>();
        response.put("ubigeos", ubigeoService.getAllUbigeos());
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Lista generada con éxito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }





}
