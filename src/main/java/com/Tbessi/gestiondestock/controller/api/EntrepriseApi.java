package com.Tbessi.gestiondestock.controller.api;

import com.Tbessi.gestiondestock.dto.EntrepriseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.Tbessi.gestiondestock.utils.Constants.APP_ROOT;

public interface EntrepriseApi {
    @PostMapping(value =APP_ROOT+"/entreprise/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto save(EntrepriseDto dto);
    @GetMapping(value = APP_ROOT+"/entreprise/{idEntreprise}",produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto findById(Integer id);
    @GetMapping(value = APP_ROOT+"/entreprise/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<EntrepriseDto> findAll();
    @DeleteMapping(value = APP_ROOT+"/entreprise/delete/{idEntreprise}")
    void delete(Integer id);
}
