package com.Tbessi.gestiondestock.repository;

import com.Tbessi.gestiondestock.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeClientRepository extends JpaRepository< CommandeClient,Integer> {
}
