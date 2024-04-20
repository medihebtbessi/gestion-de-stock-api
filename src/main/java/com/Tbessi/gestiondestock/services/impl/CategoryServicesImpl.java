package com.Tbessi.gestiondestock.services.impl;

import com.Tbessi.gestiondestock.dto.ArticleDto;
import com.Tbessi.gestiondestock.dto.CategoryDto;
import com.Tbessi.gestiondestock.exception.EntityNotFoundException;
import com.Tbessi.gestiondestock.exception.ErrorCodes;
import com.Tbessi.gestiondestock.exception.InvalidEntityException;
import com.Tbessi.gestiondestock.model.Article;
import com.Tbessi.gestiondestock.model.Category;
import com.Tbessi.gestiondestock.repository.CategoryRepository;
import com.Tbessi.gestiondestock.services.CategoryServices;
import com.Tbessi.gestiondestock.validator.ArticleValidator;
import com.Tbessi.gestiondestock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServicesImpl implements CategoryServices {

    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryServicesImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        List<String> errors= CategoryValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Category is not valid {}",dto);
            throw new InvalidEntityException("Category n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID,errors);
        }

        return CategoryDto.fromEntity(categoryRepository.save(
                CategoryDto.toEntity(dto)));
    }

    @Override
    public CategoryDto findById(Integer id) {
        if (id==null){
            log.error("Category id is null");
            return null;
        }
        Optional<Category> category=categoryRepository.findById(id);
        return Optional.of(CategoryDto.fromEntity(category.get())).orElseThrow(()->new EntityNotFoundException("Aucunne category avec L'id = "+id+" n'ete trouve dans la base de donne",
                ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public CategoryDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("category code is null");
            return null;
        }
        Optional<Category> category=categoryRepository.findCategoryByCode(code);
        return Optional.of(CategoryDto.fromEntity(category.get())).orElseThrow(()->new EntityNotFoundException("Aucun category avec le code = "+code+" n'ete trouve dans la base de donne",
                ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id==null){
            log.error("Category id is null");
            return;
        }
        categoryRepository.deleteById(id);
    }
}
