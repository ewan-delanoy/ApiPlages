package com.ewan.apiplages.util;

import com.ewan.apiplages.entity.Client;
import com.ewan.apiplages.entity.Concessionnaire;
import com.ewan.apiplages.entity.Utilisateur;
import com.ewan.apiplages.enumeration.LoginErrorEnum;
import com.ewan.apiplages.output.LoginOutput;
import com.ewan.apiplages.output.UtilisateurOutput;

public class Util {

    public static UtilisateurOutput utilisateurOutput(Utilisateur utilisateur) {
        if(utilisateur instanceof Client) {
            return ((Client) utilisateur).clientToUtilisateurOutput();
        }
        if(utilisateur instanceof Concessionnaire) {
            return ((Concessionnaire) utilisateur).concessionnaireToUtilisateurOutput();
        }
        return null;
    }

    public static LoginOutput loginOutputSucces(Utilisateur utilisateur) {

        return new LoginOutput(
                LoginErrorEnum.CONNEXION_OK.codeErreur(),
                utilisateurOutput(utilisateur)
        );
    }
    public static LoginOutput loginOutputEchec(LoginErrorEnum loginError) {

        return new LoginOutput(
                loginError.codeErreur(),
                new UtilisateurOutput(
                       0L,"","","",
                        null,null,
                        "",""
                )
        );
    }

}
