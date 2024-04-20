package com.Tbessi.gestiondestock.services;

import com.Tbessi.gestiondestock.dto.ClientDto;
import com.Tbessi.gestiondestock.dto.FournisseurDto;

import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto dto);
    FournisseurDto findById(Integer id);
    List<FournisseurDto> findAll();
    void delete(Integer id);
}
