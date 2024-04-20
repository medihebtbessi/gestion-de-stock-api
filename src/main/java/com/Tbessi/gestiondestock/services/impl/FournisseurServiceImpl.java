package com.Tbessi.gestiondestock.services.impl;

import com.Tbessi.gestiondestock.dto.FournisseurDto;
import com.Tbessi.gestiondestock.exception.EntityNotFoundException;
import com.Tbessi.gestiondestock.exception.ErrorCodes;
import com.Tbessi.gestiondestock.exception.InvalidEntityException;
import com.Tbessi.gestiondestock.repository.FournisseurRepository;
import com.Tbessi.gestiondestock.services.FournisseurService;
import com.Tbessi.gestiondestock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository fournisseurRepository;
    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {
        List<String> errors= FournisseurValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Fournisseur is not valid {}",dto);
            throw new InvalidEntityException("Le fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID,errors);
        }

        return FournisseurDto.fromEntity(fournisseurRepository.save(
                FournisseurDto.toEntity(dto)));
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if (id==null){
            log.error("Fournisseur id is null");
            return null;
        }
        return fournisseurRepository.findById(id)
                .map(FournisseurDto::fromEntity).
                orElseThrow(()->new EntityNotFoundException("Aucunne fournisseur avec L'id = "+id+" n'ete trouve dans la base de donne",
                        ErrorCodes.FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id==null){
            log.error("Fournisseur id is null");
            return;
        }
        fournisseurRepository.deleteById(id);

    }
}
