package com.ewan.apiplages.service.impl;

import com.ewan.apiplages.dao.*;
import com.ewan.apiplages.dto.*;
import com.ewan.apiplages.entity.*;
import com.ewan.apiplages.enumeration.StatutEnum;
import com.ewan.apiplages.service.ApiPlagesService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class ApiPlagesServiceImpl implements ApiPlagesService {

    private ClientDao clientDao;

    private ConcessionnaireDao concessionnaireDao;

    private EquipementDao equipementDao;

    private LienDeParenteDao lienDeParenteDao;
    private PaysDao paysDao;

    private ParasolDao parasolDao;

    private ReservationDao reservationDao;

    private StatutDao statutDao;

    public ClientDto inscrireNouveauClient(ClientACreerDto clientDto) {
        PaysDto paysDto = clientDto.getPays();
        Pays pays = paysDao.findByCode(paysDto.getCode());

        Client client = new Client(
             clientDto.getNom(), clientDto.getPrenom(),
             clientDto.getEmail(), clientDto.getMotDePasse(), pays);

        clientDao.save(client);
        return new ClientDto(client);
    }

    public PreparationFormulaireDto preparerFormulaire(PlageDto plageDto, LocalDate dateDebut, LocalDate dateFin) {
      List<Object[]> rows = parasolDao.parasolsDisponibles(new Plage(plageDto),dateDebut,dateFin);
      List<ParasolDto> parasolsDtoDisponibles = Arrays.asList();

        for (Object[] aRow : rows) {
            Parasol p = (Parasol) aRow[0];
            parasolsDtoDisponibles.add(new ParasolDto(p));
        }

        PreparationFormulaireDto preparation = new PreparationFormulaireDto();
        preparation.parasolsDisponibles = parasolsDtoDisponibles;
        preparation.equipements=tousLesEquipements();
        preparation.liensDeParente=tousLesLiensDeParente();
        preparation.paysEnvisageables=tousLesPays();
        return preparation;
    }

    public ReservationDto effectuerReservation(ReservationACreerDto reservationACreerDto) {
       Reservation reservation = new Reservation(reservationACreerDto);
       reservation.setStatut(statutDao.findByNom(StatutEnum.EN_ATTENTE.getNom()));
        reservationDao.save(reservation);
        return new ReservationDto(reservation);
    }


    public ConcessionnaireDto inscrireNouveauConcessionnaire(ConcessionnaireACreerDto concessionnaireDto) {


        Concessionnaire concessionnaire = new Concessionnaire(
                concessionnaireDto.getNom(), concessionnaireDto.getPrenom(),
                concessionnaireDto.getEmail(), concessionnaireDto.getMotDePasse(),
                concessionnaireDto.getNumeroDeTelephone()
                );

        concessionnaireDao.save(concessionnaire);
        return new ConcessionnaireDto(concessionnaire);
    }

    public List<ReservationDto> visualiserReservationsNonTraitees (Long concessionnaireId) {
        List<Reservation> reservations = reservationDao.reservationsAvecStatut(concessionnaireId, StatutEnum.EN_ATTENTE.getNom());
        List<ReservationDto>  reservationsDto = Arrays.asList();

        for (Reservation reservation : reservations) {
            reservationsDto.add(new ReservationDto(reservation));
        }
        return reservationsDto;
    }

    public void accepterReservation (Long reservationId) {
        Reservation reservation = reservationDao.findByReservationId(reservationId);
        reservation.setStatut(statutDao.findByNom(StatutEnum.ACCEPTEE.getNom()));
        reservationDao.save(reservation);
    }

    public void refuserReservation (Long reservationId) {
        Reservation reservation = reservationDao.findByReservationId(reservationId);
        reservation.setStatut(statutDao.findByNom(StatutEnum.REFUSEE.getNom()));
        reservationDao.save(reservation);
    }

    private List<EquipementDto> tousLesEquipements() {
        List<Equipement> equipements = equipementDao.findAll();
        List<EquipementDto> equipementsDto = Arrays.asList();

        for (Equipement equipement : equipements) {
            equipementsDto.add(new EquipementDto(equipement));
        }
        return equipementsDto;
    }

    private List<PaysDto> tousLesPays() {
        List<Pays> lesPays = paysDao.findAll();
        List<PaysDto> lesPaysDto = Arrays.asList();

        for (Pays pays : lesPays) {
            lesPaysDto.add(new PaysDto(pays));
        }
        return lesPaysDto;
    }

    private List<LienDeParenteDto> tousLesLiensDeParente() {
        List<LienDeParente> liensDeParente = lienDeParenteDao.findAll();
        List<LienDeParenteDto> liensDeParenteDto = Arrays.asList();

        for (LienDeParente lienDeParente : liensDeParente) {
            liensDeParenteDto.add(new LienDeParenteDto(lienDeParente));
        }
        return liensDeParenteDto;
    }

    public ApiPlagesServiceImpl(ClientDao clientDao,
                                ConcessionnaireDao concessionnaireDao,
                                EquipementDao equipementDao,
                                LienDeParenteDao lienDeParenteDao,
                                PaysDao paysDao,
                                ParasolDao parasolDao,
                                ReservationDao reservationDao,
                                StatutDao statutDao
                                ) {
        this.clientDao = clientDao ;
        this.concessionnaireDao = concessionnaireDao;
        this.equipementDao = equipementDao;
        this.lienDeParenteDao = lienDeParenteDao;
        this.paysDao = paysDao ;
        this.parasolDao = parasolDao ;
        this.reservationDao = reservationDao;
        this.statutDao = statutDao;
    }
}
