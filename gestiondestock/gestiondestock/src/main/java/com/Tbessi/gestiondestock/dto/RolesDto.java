package com.Tbessi.gestiondestock.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolesDto {

    private String roleName;
    private UtilisateurDto utilisateur;
}
