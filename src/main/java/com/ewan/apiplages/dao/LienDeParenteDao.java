package com.ewan.apiplages.dao;

import com.ewan.apiplages.entity.LienDeParente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LienDeParenteDao extends JpaRepository<LienDeParente, Long> {
    LienDeParente findByNom(String lienDeParenteNom);

}
