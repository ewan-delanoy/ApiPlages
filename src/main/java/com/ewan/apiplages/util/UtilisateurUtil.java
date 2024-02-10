package com.ewan.apiplages.util;

import com.ewan.apiplages.entity.Client;
import com.ewan.apiplages.entity.Concessionnaire;
import com.ewan.apiplages.entity.Utilisateur;
import com.ewan.apiplages.output.UtilisateurOutput;

public class UtilisateurUtil {

    public static UtilisateurOutput toOutput(Utilisateur utilisateur) {
        if(utilisateur instanceof Client client) {
            return client.clientToUtilisateurOutput();
        }
        if(utilisateur instanceof Concessionnaire concessionnaire) {
            return concessionnaire.concessionnaireToUtilisateurOutput();
        }
        return null;
    }


}
