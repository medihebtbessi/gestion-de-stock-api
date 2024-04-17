package com.Tbessi.gestiondestock.dto;

import com.Tbessi.gestiondestock.model.Fournisseur;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
@Data
@Builder
public class CommandeFournisseurDto {


    private String code;

    private Instant dateCommande;

    private FournisseurDto fournisseur;

    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;
}
