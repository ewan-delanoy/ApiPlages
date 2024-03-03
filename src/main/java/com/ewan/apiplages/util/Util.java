package com.ewan.apiplages.util;

import com.ewan.apiplages.entity.Client;
import com.ewan.apiplages.entity.Concessionnaire;
import com.ewan.apiplages.entity.Utilisateur;
import com.ewan.apiplages.enumeration.LoginErrorEnum;
import com.ewan.apiplages.enumeration.StatutEnum;
import com.ewan.apiplages.output.LoginOutput;
import com.ewan.apiplages.output.ReservationOutput;
import com.ewan.apiplages.output.TripleReservationOutput;
import com.ewan.apiplages.output.UtilisateurOutput;

import java.util.List;
import java.util.stream.Collectors;

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

    public static TripleReservationOutput tripleReservationOutput(List<ReservationOutput> reservations) {
        return new TripleReservationOutput(
                Util.selectionnerReservationsParStatut(reservations,StatutEnum.EN_ATTENTE),
                Util.selectionnerReservationsParStatut(reservations,StatutEnum.ACCEPTEE),
                Util.selectionnerReservationsParStatut(reservations,StatutEnum.REFUSEE)
        );
    }

    private static List<ReservationOutput> selectionnerReservationsParStatut
            (List<ReservationOutput> reservations, StatutEnum statutEnum) {
         String statutNom = statutEnum.getNom();
         return reservations
                 .stream()
                 .filter(r -> r.statutNom().equals(statutNom) )
                 .collect(Collectors.toList());
    }
}
