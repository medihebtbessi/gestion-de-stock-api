package com.Tbessi.gestiondestock.services;

import com.Tbessi.gestiondestock.dto.ClientDto;
import com.Tbessi.gestiondestock.dto.MvtStkDto;

import java.util.List;

public interface MvtStkService {
    MvtStkDto save(MvtStkDto dto);
    MvtStkDto findById(Integer id);
    List<MvtStkDto> findAll();
    void delete(Integer id);
}
