package com.Tbessi.gestiondestock.controller.api;

import com.Tbessi.gestiondestock.dto.ClientDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.Tbessi.gestiondestock.utils.Constants.APP_ROOT;

public interface ClientApi {
    @PostMapping(value =APP_ROOT+"/client/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto save(ClientDto dto);
    @GetMapping(value = APP_ROOT+"/client/{idClient}",produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto findById(Integer id);
    @GetMapping(value = APP_ROOT+"/client/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClientDto> findAll();
    @DeleteMapping(value = APP_ROOT+"/client/delete/{idClient}")
    void delete(Integer id);
}
