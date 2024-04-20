package com.Tbessi.gestiondestock.services;

import com.Tbessi.gestiondestock.dto.ArticleDto;
import com.Tbessi.gestiondestock.dto.CategoryDto;

import java.util.List;

public interface CategoryServices {
    CategoryDto save(CategoryDto dto);
    CategoryDto findById(Integer id);
    CategoryDto findByCode(String code);
    List<CategoryDto> findAll();
    void delete(Integer id);
}
