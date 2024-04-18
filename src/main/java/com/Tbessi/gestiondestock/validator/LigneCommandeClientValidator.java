package com.Tbessi.gestiondestock.validator;

import com.Tbessi.gestiondestock.dto.LigneCommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeClientValidator {
    public static List<String> validate(LigneCommandeClientDto dto) {
        List<String> errors = new ArrayList<>();
        if (dto==null){
            errors.add("Veuiller remplir le champ id ");
            errors.add("Veuillez remplir le champ article");
            errors.add("rentez la quantite souhaite");
            errors.add("rentez le prix unitaire");
        }

        if (dto.getId()==null){
            errors.add("Veuiller remplir le champ id ");
        }
        if (dto.getArticle()==null){
            errors.add("Veuillez remplir le champ article");
        }else {
            if (dto.getArticle().getId() == null) {
                errors.add("Veuiller remplir le champ id du l'article");
            }
            if (dto.getArticle().getCodeArticle() == null) {
                errors.add("Veuiller remplir le champ code article du l'article");
            }
        }
        if (dto.getQuantite()==null){
            errors.add("rentez la quantite souhaite");
        }
        if (dto.getPrixUnitaire()==null){
            errors.add("rentez le prix unitaire");
        }

        return errors;
    }
}
