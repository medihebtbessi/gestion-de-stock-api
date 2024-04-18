package com.Tbessi.gestiondestock.validator;

import com.Tbessi.gestiondestock.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {
   public static List<String> validate(ArticleDto dto){
       List<String> errors =new ArrayList<>();
       if (dto==null){
           errors.add("Veuillez renseigner le code de l'article");
           errors.add("Veuillez renseigner la designation de l'article");
           errors.add("Veuillez renseigner le prix unitaire ht de l'article");
           errors.add("Veuillez renseigner le taux tva de l'article");
           errors.add("Veuillez renseigner le prix unitaire tt de l'article");
           errors.add("Veuillez selectionnner une categorie");
           return errors;
       }
       if (!StringUtils.hasLength(dto.getCodeArticle())){
           errors.add("Veuillez renseigner le code de l'article");
       }
       if (!StringUtils.hasLength(dto.getDesignation())){
           errors.add("Veuillez renseigner la designation de l'article");
       }
       if (dto.getPrixUnitaireHt()==null){
           errors.add("Veuillez renseigner le prix unitaire ht de l'article");
       }
       if (dto.getTauxTva()==null){
           errors.add("Veuillez renseigner le taux tva de l'article");
       }
       if (dto.getPrixUnitaireTtc()==null){
           errors.add("Veuillez renseigner le prix unitaire tt de l'article");
       }
       if (dto.getCategory()==null){
           errors.add("Veuillez selectionnner une categorie");
       }

       return errors;
   }
}
