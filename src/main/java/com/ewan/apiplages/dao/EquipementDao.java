package com.ewan.apiplages.dao;

import com.ewan.apiplages.entity.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipementDao extends JpaRepository<Equipement, Long> {

    Equipement findByNbDeLitsAndNbDeFauteuils(byte nbDeLits, byte nbDeFauteuils);

}

