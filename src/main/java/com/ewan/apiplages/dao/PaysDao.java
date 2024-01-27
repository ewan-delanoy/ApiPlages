package com.ewan.apiplages.dao;

import com.ewan.apiplages.entity.Pays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaysDao extends JpaRepository<Pays, Long> {

    Pays findByCode(String code);
}
