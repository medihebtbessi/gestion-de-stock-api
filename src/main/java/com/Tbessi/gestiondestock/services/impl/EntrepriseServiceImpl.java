package com.Tbessi.gestiondestock.services.impl;
import com.Tbessi.gestiondestock.dto.EntrepriseDto;
import com.Tbessi.gestiondestock.exception.EntityNotFoundException;
import com.Tbessi.gestiondestock.exception.ErrorCodes;
import com.Tbessi.gestiondestock.exception.InvalidEntityException;
import com.Tbessi.gestiondestock.repository.EntrepriseRepository;
import com.Tbessi.gestiondestock.services.EntrepriseService;
import com.Tbessi.gestiondestock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;
    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        List<String> errors= EntrepriseValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Entreprise is not valid {}",dto);
            throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID,errors);
        }

        return EntrepriseDto.fromEntity(entrepriseRepository.save(
                EntrepriseDto.toEntity(dto)));
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if (id==null){
            log.error("Entreprise id is null");
            return null;
        }
        return entrepriseRepository.findById(id)
                .map(EntrepriseDto::fromEntity).
                orElseThrow(()->new EntityNotFoundException("Aucunne entreprise avec L'id = "+id+" n'ete trouve dans la base de donne",
                        ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Entreprise id is null");
            return;
        }
        entrepriseRepository.deleteById(id);
    }
}
