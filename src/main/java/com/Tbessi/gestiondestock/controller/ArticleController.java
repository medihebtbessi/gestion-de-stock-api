package com.Tbessi.gestiondestock.controller;

import com.Tbessi.gestiondestock.controller.api.ArticleApi;
import com.Tbessi.gestiondestock.dto.ArticleDto;
import com.Tbessi.gestiondestock.services.ArticleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ArticleController implements ArticleApi {

    private ArticleServices articleServices;
    @Autowired
    public ArticleController(ArticleServices articleServices) {
        this.articleServices=articleServices;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        return articleServices.save(dto);
    }

    @Override
    public ArticleDto findById(Integer id) {
        return articleServices.findById(id);
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        return articleServices.findByCodeArticle(codeArticle);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleServices.findAll();
    }

    @Override
    public void delete(Integer id) {
        articleServices.delete(id);
    }
}
