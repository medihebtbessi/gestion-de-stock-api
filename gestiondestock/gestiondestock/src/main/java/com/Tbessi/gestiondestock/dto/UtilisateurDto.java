package com.Tbessi.gestiondestock.dto;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
@Data
@Builder
public class UtilisateurDto {


    private String nom;


    private String prenom;


    private String email;


    private Instant dateDeNaissance;


    private String moteDePasse;


    private AdresseDto adresse;


    private String photo;


    private EntrepriseDto entreprise;



    private List<RolesDto> roles;
}
