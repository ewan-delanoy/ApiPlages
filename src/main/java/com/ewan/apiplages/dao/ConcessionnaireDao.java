package com.ewan.apiplages.dao;

import com.ewan.apiplages.entity.Concessionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcessionnaireDao extends JpaRepository<Concessionnaire, Long> {

    Concessionnaire findByNom(String nom);
}
