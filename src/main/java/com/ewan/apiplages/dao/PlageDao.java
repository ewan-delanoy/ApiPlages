package com.ewan.apiplages.dao;

import com.ewan.apiplages.entity.Pays;
import com.ewan.apiplages.entity.Plage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlageDao extends JpaRepository<Plage, Long> {

    Plage findByPlageId(Long plageId);
}

