package com.ewan.apiplages.dao;

import com.ewan.apiplages.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurDao extends JpaRepository<Utilisateur, Long>  {
    Optional<Utilisateur> findByEmail(String email);
}
