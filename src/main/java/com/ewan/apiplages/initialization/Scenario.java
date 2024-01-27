package com.ewan.apiplages.initialization;

import com.ewan.apiplages.dto.ParasolDto;
import com.ewan.apiplages.dto.PreparationFormulaireDto;
import com.ewan.apiplages.entity.*;
import com.ewan.apiplages.dao.*;
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

       Client client1 = clientDao.findByNom("DE ALMEIDA");
       System.out.println("Client id : " + client1.getUtilisateurId());
       LocalDate dateDebut = LocalDate.of(2020, 6, 4);
       LocalDate dateFin = LocalDate.of(2020, 6, 19);
       PreparationFormulaireDto prep = apiPlagesService.preparerFormulaire(1L, dateDebut, dateFin);
       List<ParasolDto> parasolsDto = prep.parasols;
       System.out.println("Nb de parasols : " + parasolsDto.size());

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
