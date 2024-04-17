package com.Tbessi.gestiondestock.dto;

import com.Tbessi.gestiondestock.model.CommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class LigneCommandeFournisseurDto {

    private ArticleDto article;
    private CommandeFournisseurDto commandeFournisseur;

    private BigDecimal quantite;


    private BigDecimal prixUnitaire;

    private Integer idEntreprise;
}
