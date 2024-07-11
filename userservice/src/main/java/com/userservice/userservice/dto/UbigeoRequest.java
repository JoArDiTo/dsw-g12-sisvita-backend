package com.userservice.userservice.dto;

import jakarta.annotation.Nonnull;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UbigeoRequest {
    private String idUbigeo;
    private String departamento;
    private String provincia;
    private String distrito;
    private double latitud;
    private double longitud;
}
