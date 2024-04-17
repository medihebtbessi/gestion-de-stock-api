package com.Tbessi.gestiondestock.dto;

import com.Tbessi.gestiondestock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class LigneVenteDto {


    private Ventes ventes;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;
}
