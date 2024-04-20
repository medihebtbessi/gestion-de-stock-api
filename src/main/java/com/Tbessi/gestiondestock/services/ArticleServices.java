package com.Tbessi.gestiondestock.services;

import com.Tbessi.gestiondestock.dto.ArticleDto;

import java.util.List;

public interface ArticleServices {

    ArticleDto save(ArticleDto dto);
    ArticleDto findById(Integer id);
    ArticleDto findByCodeArticle(String codeArticle);
    List<ArticleDto> findAll();
    void delete(Integer id);
}
