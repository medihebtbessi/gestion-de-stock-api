package com.Tbessi.gestiondestock.validator;

import com.Tbessi.gestiondestock.dto.CategoryDto;
import com.Tbessi.gestiondestock.model.Category;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {

    public static List<String> validate(CategoryDto categoryDto){
        List<String> errors =new ArrayList<>();

        if (categoryDto==null || !StringUtils.hasLength(categoryDto.getCode())){
            errors.add("Veuillez renseigner le code de la categorie");
        }

        return errors;

    }
}
