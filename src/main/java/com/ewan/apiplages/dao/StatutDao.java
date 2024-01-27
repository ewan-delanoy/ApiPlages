package com.ewan.apiplages.dao;

import com.ewan.apiplages.entity.Reservation;
import com.ewan.apiplages.entity.Statut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutDao extends JpaRepository<Statut, Long> {

    public Statut findByNom(String nom);
}

