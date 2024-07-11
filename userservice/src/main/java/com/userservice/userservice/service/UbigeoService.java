/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.userservice.userservice.service;

import com.userservice.userservice.dto.UbigeoRequest;
import com.userservice.userservice.dto.UbigeoResponse;
import com.userservice.userservice.model.Ubigeo;
import com.userservice.userservice.repository.UbigeoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Usuario
 */
@Service
@RequiredArgsConstructor
public class UbigeoService {
    
    private final UbigeoRepository ubigeoRepository;
    
    
    public void insert(UbigeoRequest newUbigeo){
        Ubigeo ubigeo = new Ubigeo();
        ubigeo.setIdUbigeo(newUbigeo.getIdUbigeo());
        ubigeo.setDistrito(newUbigeo.getDistrito());
        ubigeo.setDepartamento(newUbigeo.getDepartamento());
        ubigeo.setProvincia(newUbigeo.getProvincia());
        ubigeo.setLatitud(newUbigeo.getLatitud());
        ubigeo.setLongitud(newUbigeo.getLongitud());
    
        ubigeoRepository.save(ubigeo);
    }
    
    public void get(){
        
    }

    public List<UbigeoResponse> getAllUbigeos() {
        List<Ubigeo> ubigeos = ubigeoRepository.findAll();
        return ubigeos.stream().map(this::mapToUbigeoResponse).toList();
    }

    private UbigeoResponse mapToUbigeoResponse(Ubigeo ubigeo){
        return UbigeoResponse .builder()
                .idUbigeo(ubigeo.getIdUbigeo())
                .distrito(ubigeo.getDistrito())
                .departamento(ubigeo.getDepartamento())
                .provincia(ubigeo.getProvincia())
                .latitud(ubigeo.getLatitud())
                .longitud(ubigeo.getLatitud())
                .build();
    }
}
