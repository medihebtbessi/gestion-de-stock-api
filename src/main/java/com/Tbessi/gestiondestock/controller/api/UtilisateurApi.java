package com.Tbessi.gestiondestock.controller.api;

import com.Tbessi.gestiondestock.dto.UtilisateurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.Tbessi.gestiondestock.utils.Constants.APP_ROOT;

public interface UtilisateurApi {
    @PostMapping(value =APP_ROOT+"/utilisateur/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto save(UtilisateurDto dto);
    @GetMapping(value = APP_ROOT+"/utilisateur/{idUtilisateur}",produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findById(Integer id);
    @GetMapping(value = APP_ROOT+"/utilisateur/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> findAll();
    @DeleteMapping(value = APP_ROOT+"/utilisateur/delete/{idUtilisateur}")
    void delete(Integer id);
}
