package com.Tbessi.gestiondestock.services.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.Tbessi.gestiondestock.dto.*;
import com.Tbessi.gestiondestock.exception.EntityNotFoundException;
import com.Tbessi.gestiondestock.exception.ErrorCodes;
import com.Tbessi.gestiondestock.exception.InvalidEntityException;
import com.Tbessi.gestiondestock.exception.InvalidOperationException;
import com.Tbessi.gestiondestock.model.*;
import com.Tbessi.gestiondestock.repository.ArticleRepository;
import com.Tbessi.gestiondestock.repository.CommandeFournisseurRepository;
import com.Tbessi.gestiondestock.repository.FournisseurRepository;
import com.Tbessi.gestiondestock.repository.LigneCommandeFournisseurRepository;
import com.Tbessi.gestiondestock.services.CommandeFournisseurService;
import com.Tbessi.gestiondestock.validator.ArticleValidator;
import com.Tbessi.gestiondestock.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;
    private MvtStkServiceImpl mvtStkService;

    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository,
                                          FournisseurRepository fournisseurRepository, ArticleRepository articleRepository,
                                          LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository, MvtStkServiceImpl mvtStkService) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
        this.mvtStkService = mvtStkService;
    }


    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        return null;
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        return null;
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        return null;
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}