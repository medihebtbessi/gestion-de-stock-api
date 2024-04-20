package com.Tbessi.gestiondestock.services.impl;

import com.Tbessi.gestiondestock.dto.ClientDto;
import com.Tbessi.gestiondestock.exception.EntityNotFoundException;
import com.Tbessi.gestiondestock.exception.ErrorCodes;
import com.Tbessi.gestiondestock.exception.InvalidEntityException;
import com.Tbessi.gestiondestock.repository.ClientRepository;
import com.Tbessi.gestiondestock.services.ClientService;
import com.Tbessi.gestiondestock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

        private ClientRepository clientRepository;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        List<String> errors= ClientValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Client is not valid {}",dto);
            throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.CLIENT_NOT_VALID,errors);
        }

        return ClientDto.fromEntity(clientRepository.save(
                ClientDto.toEntity(dto)));
    }

    @Override
    public ClientDto findById(Integer id) {
        if (id==null){
            log.error("Client id is null");
            return null;
        }
        return clientRepository.findById(id)
                .map(ClientDto::fromEntity).
                orElseThrow(()->new EntityNotFoundException("Aucunne client avec L'id = "+id+" n'ete trouve dans la base de donne",
                        ErrorCodes.CLIENT_NOT_FOUND));
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Client id is null");
            return;
        }
        clientRepository.deleteById(id);

    }
}
