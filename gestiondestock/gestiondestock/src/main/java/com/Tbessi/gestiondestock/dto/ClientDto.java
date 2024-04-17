package com.Tbessi.gestiondestock.dto;

import com.Tbessi.gestiondestock.model.Adresse;
import com.Tbessi.gestiondestock.model.CommandeClient;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class ClientDto {


    private String nom;

    private String prenom;

    private Adresse adresse;

    private String photo;

    private String mail;

    private String numTel;

    private List<CommandeClientDto> commandeClients;
}
