package com.ewan.apiplages.service.impl;

import com.ewan.apiplages.dao.*;
import com.ewan.apiplages.dto.*;
import com.ewan.apiplages.entity.*;
import com.ewan.apiplages.enumeration.StatutEnum;
import com.ewan.apiplages.service.ApiPlagesService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiPlagesServiceImpl implements ApiPlagesService {

    private final ClientDao clientDao;

    private final ConcessionnaireDao concessionnaireDao;

    private final EquipementDao equipementDao;

    private final LienDeParenteDao lienDeParenteDao;
    private final PaysDao paysDao;

    private final ParasolDao parasolDao;

    private final PlageDao plageDao;
    private final ReservationDao reservationDao;

    private final StatutDao statutDao;

    public ClientDto inscrireNouveauClient(ClientACreerDto clientDto) {
        PaysDto paysDto = clientDto.getPays();
        Pays pays = paysDao.findByCode(paysDto.getCode());

        Client client = new Client(
             clientDto.getNom(), clientDto.getPrenom(),
             clientDto.getEmail(), clientDto.getMotDePasse(), pays);

        clientDao.save(client);
        return new ClientDto(client);
    }

    public List<ReservationDto> mesReservations (Long clientId,String statutNom) {
        List<Reservation> reservations =
                reservationDao.reservationsPourClient(clientId, statutNom);
        List<ReservationDto>  reservationsDto = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationsDto.add(new ReservationDto(reservation));
        }
        return reservationsDto;
    }

    public PreparationFormulaireDto preparerFormulaire(Long plageId, LocalDate dateDebut, LocalDate dateFin) {
        Plage plage = plageDao.getReferenceById(plageId);
        List<Long> idsOccupes = parasolDao.idsDesParasolsOccupes(plage,dateDebut,dateFin);
        List<Parasol> parasols = parasolDao.findByFilePlagePlageId(plageId);
        List<EmplacementDto> emplacementsDisponibles = new ArrayList<>();

        for (Parasol parasol : parasols) {
            if(!(idsOccupes.contains(parasol.getParasolId()))) {
                emplacementsDisponibles.add(new EmplacementDto(parasol));
            }
        }

        return new PreparationFormulaireDto(
                emplacementsDisponibles,
                tousLesEquipements(),
                tousLesLiensDeParente(),
                tousLesPays()
        );
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
        List<Reservation> reservations = reservationDao.reservationsPourConcessionnaire(concessionnaireId, StatutEnum.EN_ATTENTE.getNom());
        List<ReservationDto>  reservationsDto = new ArrayList<>();

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
        List<EquipementDto> equipementsDto = new ArrayList<>();

        for (Equipement equipement : equipements) {
            equipementsDto.add(new EquipementDto(equipement));
        }
        return equipementsDto;
    }

    private List<PaysDto> tousLesPays() {
        List<Pays> lesPays = paysDao.findAll();
        List<PaysDto> lesPaysDto = new ArrayList<>();

        for (Pays pays : lesPays) {
            lesPaysDto.add(new PaysDto(pays));
        }
        return lesPaysDto;
    }

    private List<LienDeParenteDto> tousLesLiensDeParente() {
        List<LienDeParente> liensDeParente = lienDeParenteDao.findAll();
        List<LienDeParenteDto> liensDeParenteDto = new ArrayList<>();

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
                                PlageDao plageDao,
                                ReservationDao reservationDao,
                                StatutDao statutDao
                                ) {
        this.clientDao = clientDao ;
        this.concessionnaireDao = concessionnaireDao;
        this.equipementDao = equipementDao;
        this.lienDeParenteDao = lienDeParenteDao;
        this.paysDao = paysDao ;
        this.parasolDao = parasolDao ;
        this.plageDao = plageDao;
        this.reservationDao = reservationDao;
        this.statutDao = statutDao;
    }
}
