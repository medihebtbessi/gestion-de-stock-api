package com.Tbessi.gestiondestock.controller.api;

import com.Tbessi.gestiondestock.dto.CategoryDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.Tbessi.gestiondestock.utils.Constants.APP_ROOT;

public interface CategoryApi {

    @PostMapping(value =APP_ROOT+"/category/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto save(@RequestBody CategoryDto dto);
    @GetMapping(value = APP_ROOT+"/category/{idCategory}",produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findById(@PathVariable("idCategory") Integer id);
    @GetMapping(value = APP_ROOT+"/category/{codeCategory}",produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findByCode(@PathVariable("codeCategory") String code);
    @GetMapping(value = APP_ROOT+"/category/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDto> findAll();
    @DeleteMapping(value = APP_ROOT+"/category/delete/{idCategory}")
    void delete(@PathVariable("idCategory") Integer id);
}
