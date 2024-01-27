package com.ewan.apiplages.dao;

import com.ewan.apiplages.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDao extends JpaRepository<File, Long> {

}
