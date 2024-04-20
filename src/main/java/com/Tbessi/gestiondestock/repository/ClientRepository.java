package com.Tbessi.gestiondestock.repository;

import com.Tbessi.gestiondestock.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository< Client,Integer> {
}
