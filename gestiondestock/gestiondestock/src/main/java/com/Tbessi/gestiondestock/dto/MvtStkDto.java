package com.Tbessi.gestiondestock.dto;

import com.Tbessi.gestiondestock.model.TypeMvtStk;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
@Data
@Builder
public class MvtStkDto {


    private Instant dateMvt;


    private BigDecimal quantite;


    private ArticleDto article;


    private TypeMvtStk typeMvt;
}
