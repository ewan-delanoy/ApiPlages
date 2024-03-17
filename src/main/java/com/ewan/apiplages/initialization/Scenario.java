package com.ewan.apiplages.initialization;

import com.ewan.apiplages.dao.*;
import com.ewan.apiplages.entity.Client;
import com.ewan.apiplages.enumeration.LienDeParenteEnum;
import com.ewan.apiplages.input.PreparationReservationInput;
import com.ewan.apiplages.input.ReservationInput;
import com.ewan.apiplages.input.AffectationInput;
import com.ewan.apiplages.output.ParasolOutput;
import com.ewan.apiplages.output.PreparationReservationOutput;
import com.ewan.apiplages.service.ApiPlagesService;
import com.ewan.apiplages.util.KeepCompilerQuiet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class Scenario implements CommandLineRunner {
    private final ClientDao clientDao;
    // private final ConcessionnaireDao concessionnaireDao;
    // private final EquipementDao equipementDao;
    // private final FileDao fileDao;
    // private final LienDeParenteDao lienDeParenteDao;
    // private final PaysDao paysDao;
    // private final PlageDao plageDao;
    // private final ReservationDao reservationDao;
    // private final StatutDao statutDao;
    // private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final ApiPlagesService apiPlagesService;


    @Override
    public void run(String...args)  {
       boolean isActive = false;
       isActive = KeepCompilerQuiet.doNotModifyBoolean(isActive);
        if (isActive) {
            runWhenActive();
        }

    }
    public void runWhenActive()  {

        List<Client> clients = clientDao.findAll();
        System.out.println(clients);


       Long clientId = 5L;
       Long plageId = 1L;
       String aucunLien = LienDeParenteEnum.AUCUN_LIEN.getNom();
       LocalDate dateDebut = LocalDate.of(2020, 6, 4);
       LocalDate dateFin = LocalDate.of(2020, 6, 19);
       PreparationReservationInput preparationReservationInput = new PreparationReservationInput(plageId, dateDebut, dateFin);
       PreparationReservationOutput prep = apiPlagesService.preparerFormulaire(preparationReservationInput);
       List<ParasolOutput> emplacements = prep.parasols();
       System.out.println("Nb d'emplacements : " + emplacements.size());
       // System.out.println("P : " +prep.toString());
       long idx1 = 60L; // emplacements.get(60).emplacementId();
       long idx2 = 61L; // emplacements.get(120).emplacementId();
       System.out.println("idx1 = " +idx1);
       System.out.println("idx2 = " +idx2);
       List<AffectationInput> selections= Arrays.asList(
               new AffectationInput((byte) 3, (byte) 4, (byte) 1, (byte) 0),
               new AffectationInput((byte) 4, (byte) 3, (byte) 0, (byte) 1)
       );
       ReservationInput reservationInput=new ReservationInput(clientId,plageId,selections,dateDebut,dateFin,aucunLien,"0000000000000000", (byte) 1, (short) 2027, "321");
       KeepCompilerQuiet.doNothingWithLong(reservationInput.clientId());
       // Long newResId =apiPlagesService.effectuerReservation(reservationInput);
       // System.out.println("newResId = " +newResId);
       // apiPlagesService.supprimerReservation(10L);

    }

    public Scenario(
            ClientDao clientDao,
            // ConcessionnaireDao concessionnaireDao,
            //EquipementDao equipementDao,
            //FileDao fileDao,
            // LienDeParenteDao lienDeParenteDao,
            // PaysDao paysDao,
            //PlageDao plageDao,
            // ReservationDao reservationDao,
            // StatutDao statutDao,
            ApiPlagesService apiPlagesService

    ) {
        this.clientDao = clientDao;
        // this.concessionnaireDao = concessionnaireDao;
        // this.equipementDao = equipementDao;
        // this.fileDao = fileDao;
        // this.lienDeParenteDao = lienDeParenteDao;
        // this.paysDao = paysDao;
        // this.plageDao = plageDao;
        // this.reservationDao = reservationDao;
        // this.statutDao = statutDao;
        this.apiPlagesService = apiPlagesService;

    }
}
