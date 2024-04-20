package com.Tbessi.gestiondestock.repository;

import com.Tbessi.gestiondestock.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeClientRepository extends JpaRepository< CommandeClient,Integer> {

    Optional<CommandeClient> findCommandeClientByCode(String code);
}
