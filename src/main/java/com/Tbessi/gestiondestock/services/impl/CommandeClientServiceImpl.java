package com.Tbessi.gestiondestock.services.impl;

import com.Tbessi.gestiondestock.dto.CommandeClientDto;
import com.Tbessi.gestiondestock.dto.LigneCommandeClientDto;
import com.Tbessi.gestiondestock.exception.EntityNotFoundException;
import com.Tbessi.gestiondestock.exception.ErrorCodes;
import com.Tbessi.gestiondestock.exception.InvalidEntityException;
import com.Tbessi.gestiondestock.model.Article;
import com.Tbessi.gestiondestock.model.Client;
import com.Tbessi.gestiondestock.model.CommandeClient;
import com.Tbessi.gestiondestock.model.LigneCommandeClient;
import com.Tbessi.gestiondestock.repository.ArticleRepository;
import com.Tbessi.gestiondestock.repository.ClientRepository;
import com.Tbessi.gestiondestock.repository.CommandeClientRepository;
import com.Tbessi.gestiondestock.repository.LigneCommandeClientRepository;
import com.Tbessi.gestiondestock.services.CommandeClientService;
import com.Tbessi.gestiondestock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;
    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository, ClientRepository clientRepository, ArticleRepository articleRepository,LigneCommandeClientRepository ligneCommandeClientRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeClientRepository=ligneCommandeClientRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {

        List<String> errors= CommandeClientValidator.validate(dto);

        if (!errors.isEmpty()){
            log.error("Commande client n'est pas valide");
            throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID,errors);
        }
       Optional<Client> client=clientRepository.findById(dto.getClient().getId());
        if (client.isEmpty()){
            log.warn("Client with id {} was not found in the DB",dto.getClient().getId());
            throw new EntityNotFoundException("Aucun client avec l'ID"+dto.getClient().getId()+" n'a ete trouve dans la BDD",ErrorCodes.CLIENT_NOT_FOUND);

        }
        List<String> articleErrors=new ArrayList<>();

        if (dto.getLigneCommandeClients()!=null){
            dto.getLigneCommandeClients().forEach(ligCmdClt->{
                if (ligCmdClt.getArticle()!=null) {
                    Optional<Article> article = articleRepository.findById(ligCmdClt.getArticle().getId());
                    if (article.isEmpty()){
                        articleErrors.add("L'article avec l'id "+ligCmdClt.getArticle().getId()+"n'existe pas");
                    }
                }else {
                    articleErrors.add("Impossible d'enregistrer une commande avec un article null");
                }
            });
        }
        if (!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("Article n'existe pas dans la BDD",ErrorCodes.ARTICLE_NOT_FOUND,articleErrors);
        }

        CommandeClient savedCmdClt= commandeClientRepository.save(CommandeClientDto.toEntity(dto));

        if (dto.getLigneCommandeClients()!=null) {
            dto.getLigneCommandeClients().forEach(ligCmdClt -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdClt);
                ligneCommandeClient.setCommandeClient(savedCmdClt);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }
        return CommandeClientDto.fromEntity(savedCmdClt);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if (id==null){
            log.error("Commande client ID is null");
            return null;
        }
        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                        "Aucune commande client n'a ete trouve avec l'ID "+id,ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (StringUtils.hasLength(code)){
            log.error("Commande client code is null");
            return null;
        }
        return commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                        "Aucune commande client n'a ete trouve avec le code "+code,ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll()
                .stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Commande client ID is null");
            return ;
        }
        commandeClientRepository.deleteById(id);
    }
}
