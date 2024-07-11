/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.userservice.userservice.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *
 * @author Usuario
 */
@Entity
@Table(name="ubigeo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ubigeo {
    @Id
    private String idUbigeo;
    @Nonnull
    private String departamento;
    private String provincia;
    private String distrito;
    private double latitud;
    private double longitud;
}
