package com.userservice.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UbigeoResponse {
    private String idUbigeo;
    private String departamento;
    private String provincia;
    private String distrito;
    private double latitud;
    private double longitud;
}
