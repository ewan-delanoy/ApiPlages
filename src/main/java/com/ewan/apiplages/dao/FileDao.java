package com.ewan.apiplages.dao;

import com.ewan.apiplages.entity.File;
import com.ewan.apiplages.entity.Plage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileDao extends JpaRepository<File, Long> {

    File findByPlageAndNumero(Plage plage, byte numero);

    File findByPlagePlageIdAndNumero(Long plageId, byte numero);

    List<File> findByPlagePlageId(Long plageId);

}
