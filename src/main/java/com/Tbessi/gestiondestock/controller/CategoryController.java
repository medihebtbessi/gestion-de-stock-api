package com.Tbessi.gestiondestock.controller;

import com.Tbessi.gestiondestock.controller.api.CategoryApi;
import com.Tbessi.gestiondestock.dto.CategoryDto;
import com.Tbessi.gestiondestock.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class CategoryController implements CategoryApi {
    private CategoryServices categoryServices;
    @Autowired
    public CategoryController(CategoryServices categoryServices) {
        this.categoryServices = categoryServices;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        return categoryServices.save(dto);
    }

    @Override
    public CategoryDto findById(Integer id) {
        return categoryServices.findById(id);
    }

    @Override
    public CategoryDto findByCode(String code) {
        return categoryServices.findByCode(code);
    }


    @Override
    public List<CategoryDto> findAll() {
        return categoryServices.findAll();
    }

    @Override
    public void delete(Integer id) {
        categoryServices.delete(id);
    }
}
