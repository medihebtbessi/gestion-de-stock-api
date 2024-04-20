package com.Tbessi.gestiondestock.services.impl;

import com.Tbessi.gestiondestock.dto.ArticleDto;
import com.Tbessi.gestiondestock.exception.EntityNotFoundException;
import com.Tbessi.gestiondestock.exception.ErrorCodes;
import com.Tbessi.gestiondestock.exception.InvalidEntityException;
import com.Tbessi.gestiondestock.model.Article;
import com.Tbessi.gestiondestock.repository.ArticleRepository;
import com.Tbessi.gestiondestock.services.ArticleServices;
import com.Tbessi.gestiondestock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleServices {

    private ArticleRepository articleRepository;
    @Autowired
    public ArticleServiceImpl(
            ArticleRepository articleRepository
    ) {
        this.articleRepository=articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        List<String> errors= ArticleValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Article is not valid {}",dto);
            throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID,errors);
        }

        return ArticleDto.fromEntity(articleRepository.save(
                ArticleDto.toEntity(dto)));
    }

    @Override
    public ArticleDto findById(Integer id) {
        if (id==null){
            log.error("Article id is null");
            return null;
        }
        Optional<Article> article=articleRepository.findById(id);
        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(()->new EntityNotFoundException("Aucun article avec L'id = "+id+" n'ete trouve dans la base de donne",
                ErrorCodes.ARTICLE_NOT_FOUND));
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        if (!StringUtils.hasLength(codeArticle)){
            log.error("Article code is null");
            return null;
        }
        Optional<Article> article=articleRepository.findArticleByCodeArticle(codeArticle);
        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(()->new EntityNotFoundException("Aucun article avec le code = "+codeArticle+" n'ete trouve dans la base de donne",
                ErrorCodes.ARTICLE_NOT_FOUND));
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Article id is null");
            return;
        }
        articleRepository.deleteById(id);
    }
}
