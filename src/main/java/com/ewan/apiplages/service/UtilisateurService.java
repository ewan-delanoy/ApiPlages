package com.ewan.apiplages.service;

import com.ewan.apiplages.dao.UtilisateurDao;
import com.ewan.apiplages.entity.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurService {
    private final UtilisateurDao utilisateurDao;

    public UtilisateurService(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    public List<Utilisateur> allUtilisateurs() {

        return new ArrayList<>(utilisateurDao.findAll());
    }
}