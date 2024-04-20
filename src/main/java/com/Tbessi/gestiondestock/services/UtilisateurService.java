package com.Tbessi.gestiondestock.services;

import com.Tbessi.gestiondestock.dto.ClientDto;
import com.Tbessi.gestiondestock.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDto save(UtilisateurDto dto);
    UtilisateurDto findById(Integer id);
    List<UtilisateurDto> findAll();
    void delete(Integer id);
}
