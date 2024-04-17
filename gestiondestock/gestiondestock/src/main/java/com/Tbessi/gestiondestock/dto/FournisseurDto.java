package com.Tbessi.gestiondestock.dto;

import com.Tbessi.gestiondestock.model.Adresse;
import com.Tbessi.gestiondestock.model.CommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class FournisseurDto {

    private String nom;

    private String prenom;

    private AdresseDto adresse;

    private String photo;

    private String mail;

    private String numTel;

    private List<CommandeFournisseurDto> commandeFournisseurs;
}
