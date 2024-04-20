package com.Tbessi.gestiondestock.services.impl;

import com.Tbessi.gestiondestock.dto.ClientDto;
import com.Tbessi.gestiondestock.dto.MvtStkDto;
import com.Tbessi.gestiondestock.exception.EntityNotFoundException;
import com.Tbessi.gestiondestock.exception.ErrorCodes;
import com.Tbessi.gestiondestock.exception.InvalidEntityException;
import com.Tbessi.gestiondestock.repository.MvtStkRepository;
import com.Tbessi.gestiondestock.services.MvtStkService;
import com.Tbessi.gestiondestock.validator.ClientValidator;
import com.Tbessi.gestiondestock.validator.MvtStkValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MvtStkServiceImpl implements MvtStkService {
    private MvtStkRepository mvtStkRepository;
    @Autowired
    public MvtStkServiceImpl(MvtStkRepository mvtStkRepository) {
        this.mvtStkRepository = mvtStkRepository;
    }

    @Override
    public MvtStkDto save(MvtStkDto dto) {
        List<String> errors= MvtStkValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("MvtStk is not valid {}",dto);
            throw new InvalidEntityException("MvtStk n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID,errors);
        }

        return MvtStkDto.fromEntity(mvtStkRepository.save(
                MvtStkDto.toEntity(dto)));
    }

    @Override
    public MvtStkDto findById(Integer id) {
        if (id==null){
            log.error("MvtStk id is null");
            return null;
        }
        return mvtStkRepository.findById(id)
                .map(MvtStkDto::fromEntity).
                orElseThrow(()->new EntityNotFoundException("Aucunne MvtStk avec L'id = "+id+" n'ete trouve dans la base de donne",
                        ErrorCodes.MVT_STK_NOT_FOUND));
    }

    @Override
    public List<MvtStkDto> findAll() {
        return mvtStkRepository.findAll().stream()
                .map(MvtStkDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("MvtStk id is null");
            return;
        }
        mvtStkRepository.deleteById(id);
    }
}
