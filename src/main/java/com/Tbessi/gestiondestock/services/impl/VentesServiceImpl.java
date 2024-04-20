package com.Tbessi.gestiondestock.services.impl;

import com.Tbessi.gestiondestock.dto.LigneVenteDto;
import com.Tbessi.gestiondestock.dto.VentesDto;
import com.Tbessi.gestiondestock.exception.EntityNotFoundException;
import com.Tbessi.gestiondestock.exception.ErrorCodes;
import com.Tbessi.gestiondestock.exception.InvalidEntityException;
import com.Tbessi.gestiondestock.model.Article;
import com.Tbessi.gestiondestock.model.LigneVente;
import com.Tbessi.gestiondestock.model.Ventes;
import com.Tbessi.gestiondestock.repository.ArticleRepository;
import com.Tbessi.gestiondestock.repository.LigneVenteRepository;
import com.Tbessi.gestiondestock.repository.VentesRepository;
import com.Tbessi.gestiondestock.services.VentesService;
import com.Tbessi.gestiondestock.validator.VentesValidator;
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
public class VentesServiceImpl implements VentesService {

    private ArticleRepository articleRepository;
    private VentesRepository ventesRepository;
    private LigneVenteRepository ligneVenteRepository;

    public VentesServiceImpl(ArticleRepository articleRepository, VentesRepository ventesRepository, LigneVenteRepository ligneVenteRepository) {
        this.articleRepository = articleRepository;
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Autowired

    @Override
    public VentesDto save(VentesDto dto) {

        List<String> errors = VentesValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("ventes n'est pas valide");
            throw new InvalidEntityException("L'objet vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID,errors);
        }
        List<String> articleErrors=new ArrayList<>();

        dto.getLigneVentes().forEach(ligneVenteDto -> {
            Optional<Article> article=articleRepository.findById(ligneVenteDto.getArticle().getId());
            if (article.isEmpty()){
                articleErrors.add("Aucun article avec l'id "+ligneVenteDto.getArticle().getId()+" n'a ete trouve dans la BDD");
            }
        });
        if (!articleErrors.isEmpty()){
            log.error("ONE OR MORE ARTICLE were not found in the DB ,{} ",errors);
            throw new InvalidEntityException("Un ou plusieurs n'ont pas ete trouve dans la BDD", ErrorCodes.VENTE_NOT_VALID,errors);
        }

        Ventes savedVentes = ventesRepository.save(VentesDto.toEntity(dto));
        dto.getLigneVentes().forEach(ligneVenteDto -> {
            LigneVente ligneVente= LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(savedVentes);
            ligneVenteRepository.save(ligneVente);
        });
        return VentesDto.fromEntity(savedVentes);
    }

    @Override
    public VentesDto findById(Integer id) {
        if (id==null){
            log.error("Ventes Id is null");
            return null;
        }
        return ventesRepository.findById(id)
                .map(VentesDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Aucun vente n'a ete trouve dans la BDD",ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public VentesDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Ventes code is null");
            return null;
        }
        return ventesRepository.findVentesByCode(code)
                .map(VentesDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Aucun vente n'a ete trouve dans la BDD"+code,ErrorCodes.VENTE_NOT_FOUND));
    }
    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream()
                .map(VentesDto::fromEntity)
                .collect(Collectors.toList());
    }
    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Vente ID is  null");
            return ;
        }
        ventesRepository.deleteById(id);

        }
    }

