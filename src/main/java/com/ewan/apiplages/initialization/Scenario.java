package com.ewan.apiplages.initialization;

import com.ewan.apiplages.dto.EmplacementDto;
import com.ewan.apiplages.dto.PreparationFormulaireDto;
import com.ewan.apiplages.dao.*;
import com.ewan.apiplages.enumeration.LienDeParenteEnum;
import com.ewan.apiplages.input.ReservationInput;
import com.ewan.apiplages.input.SelectionEquipementInput;
import com.ewan.apiplages.service.ApiPlagesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class Scenario implements CommandLineRunner {
    private final ClientDao clientDao;
    private final ConcessionnaireDao concessionnaireDao;
    private final EquipementDao equipementDao;
    private final FileDao fileDao;
    private final LienDeParenteDao lienDeParenteDao;
    private final ParasolDao parasolDao;
    private final PaysDao paysDao;
    private final PlageDao plageDao;
    private final ReservationDao reservationDao;
    private final StatutDao statutDao;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    private final ApiPlagesService apiPlagesService;

    private final boolean IS_CURRENTLY_ACTIVE = true ;
    @Override
    public void run(String...args) throws Exception {
        if(this.IS_CURRENTLY_ACTIVE) {
            runWhenActive(args);
        }
    }
    public void runWhenActive(String...args)  {
       /*
        List<Client> clients = clientDao.findAll();
        System.out.println(clients);
        */

       Long clientId = 5L;
       Long plageId = 1L;
       String aucunLien = LienDeParenteEnum.AUCUN_LIEN.getNom();
       LocalDate dateDebut = LocalDate.of(2020, 6, 4);
       LocalDate dateFin = LocalDate.of(2020, 6, 19);
       PreparationFormulaireDto prep = apiPlagesService.preparerFormulaire(plageId, dateDebut, dateFin);
       List<EmplacementDto> emplacements = prep.emplacementsDisponibles;
       System.out.println("Nb d'emplacements : " + emplacements.size());
       // System.out.println("P : " +prep.toString());
       Long idx1 = emplacements.get(60).getParasolId();
       Long idx2 = emplacements.get(120).getParasolId();
       System.out.println("idx1 = " +idx1);
       System.out.println("idx2 = " +idx2);
       List<SelectionEquipementInput> selections= List.of(
               new SelectionEquipementInput(idx1,(byte)1,(byte)0),
                new SelectionEquipementInput(idx2,(byte)0,(byte)1)
        );
       ReservationInput reservationInput=new ReservationInput(clientId,plageId,selections,dateDebut,dateFin,aucunLien);
       Long newResId =apiPlagesService.effectuerReservation(reservationInput);
       System.out.println("newResId = " +newResId);
       // apiPlagesService.supprimerReservation(10L);

    }
    public Scenario(
            ClientDao clientDao,
            ConcessionnaireDao concessionnaireDao,
            EquipementDao equipementDao,
            FileDao fileDao,
            LienDeParenteDao lienDeParenteDao,
            ParasolDao parasolDao,
            PaysDao paysDao,
            PlageDao plageDao,
            ReservationDao reservationDao,
            StatutDao statutDao,
            ApiPlagesService apiPlagesService

    ) {
        this.clientDao = clientDao;
        this.concessionnaireDao = concessionnaireDao;
        this.equipementDao = equipementDao;
        this.fileDao = fileDao;
        this.lienDeParenteDao = lienDeParenteDao;
        this.parasolDao = parasolDao;
        this.paysDao = paysDao;
        this.plageDao = plageDao;
        this.reservationDao = reservationDao;
        this.statutDao = statutDao;
        this.apiPlagesService = apiPlagesService;

    }
}
