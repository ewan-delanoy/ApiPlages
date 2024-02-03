package com.ewan.apiplages.dao;

import com.ewan.apiplages.entity.File;
import com.ewan.apiplages.entity.Plage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDao extends JpaRepository<File, Long> {

    File findByPlageAndNumero(Plage plage, byte numero);

}
