package com.ewan.apiplages.dao;

import com.ewan.apiplages.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDao extends JpaRepository<Client, Long> {
    Client findByNom(String nom);
}
