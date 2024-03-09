package com.ewan.apiplages.initialization;


import com.ewan.apiplages.entity.*;
import com.ewan.apiplages.dao.*;
import com.ewan.apiplages.enumeration.EquipementEnum;
import com.ewan.apiplages.enumeration.LienDeParenteEnum;
import com.ewan.apiplages.enumeration.StatutEnum;
import org.springframework.boot.CommandLineRunner;
import com.ewan.apiplages.util.crypto.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InitializeDatabase implements CommandLineRunner {

    private final AffectationDao affectationDao;
    private final ClientDao clientDao;
    private final ConcessionnaireDao concessionnaireDao;
    private final EmplacementDao emplacementDao;
    private final EquipementDao equipementDao;
    private final FileDao fileDao;
    private final LienDeParenteDao lienDeParenteDao;
    private final PaysDao paysDao;
    private final PlageDao plageDao;
    private final ReservationDao reservationDao;
    private final StatutDao statutDao;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final List<Client> clientsEnregistres = new ArrayList<>();
    private final List<Concessionnaire> concessionnairesEnregistres = new ArrayList<>();

    // private final List<Equipement> equipementsEnregistres = new ArrayList<>();
    private final List<LienDeParente> liensDeParenteEnregistres = new ArrayList<>();

    private final List<Pays> paysEnregistres = new ArrayList<>();
    private final List<Plage> plagesEnregistrees = new ArrayList<>();

    private final List<Statut> statutsEnregistres = new ArrayList<>();
    @Override
    public void run(String...args)  {

          runWhenActive(true);

    }
    public void runWhenActive(boolean is_active)  {
        if(!is_active) return;
        ajouterEquipements();
        ajouterLiensDeParente();
        ajouterPays();
        ajouterConcessionnaires();
        ajouterClients();
        ajouterStatuts();
        ajouterPlages();
        ajouterFiles();
        ajouterEmplacements();
        ajouterReservations();
        System.out.println("Initialization finished");

    }
    private void ajouterClients() {
        if (clientDao.count() == 0) {
            Client client1 = new Client(
                    "DUPONT", "Alexandre", "alexdupond@sfr.fr",
                    encoder.encode("abcdefghi"), recupererPaysEnregistre(1)
            );
            Client client2 = new Client(
                    "SEYMOUR", "Jane", "jseymour@gmail.uk",
                    encoder.encode("abcdefghi"), recupererPaysEnregistre(2)
            );
            Client client3 = new Client(
                    "DE ALMEIDA", "Pedro", "pdealmeida@yahoo.pt",
                    encoder.encode("abcdefghi"), recupererPaysEnregistre(4)
            );
            clientDao.saveAll(Arrays.asList(client1,client2,client3));
            clientsEnregistres.add(client1);
            clientsEnregistres.add(client2);
            clientsEnregistres.add(client3);
        }
    }
    private void ajouterConcessionnaires() {
        if (concessionnaireDao.count() == 0) {
            Concessionnaire concessionnaire1 = new Concessionnaire(
                    "ROSSI", "Giuseppe", "peppe@humanbooster.fr",
                    encoder.encode("12345678"), "0612345678");
            Concessionnaire concessionnaire2 = new Concessionnaire(
                    "BULL", "John", "johnthebull@wanadoo.uk",
                    encoder.encode("87654321"), "0687654321");
            concessionnaireDao.saveAll(Arrays.asList(concessionnaire1,concessionnaire2));
            concessionnairesEnregistres.addAll(Arrays.asList(concessionnaire1,concessionnaire2));
        }
    }
    private void ajouterEmplacements() {
        // On teste si des emplacements sont déjà en base
        if (emplacementDao.count()==0) {
            for (File file: fileDao.findAll()) {
                // Pour chaque file, on ajoute 36 emplacements
                for (byte numEmplacement = 1; numEmplacement <=36; numEmplacement++) {
                    emplacementDao.save(new Emplacement(file, numEmplacement));
                }
            }
        }
    }

    private void ajouterEquipements() {
        // On teste si des equipements sont déjà en base
        if (equipementDao.count()==0) {
            // il n'y a pas encore d'équipements en base, on en ajoute 5
            Equipement equipement1 = new Equipement(EquipementEnum.UN_LIT);
            Equipement equipement2 = new Equipement(EquipementEnum.DEUX_LITS);
            Equipement equipement3 = new Equipement(EquipementEnum.UN_LIT_UN_FAUTEUIL);
            Equipement equipement4 = new Equipement(EquipementEnum.UN_FAUTEUIL);
            Equipement equipement5 = new Equipement(EquipementEnum.DEUX_FAUTEUILS);

            equipementDao.saveAll(Arrays.asList(equipement1,equipement2,equipement3, equipement4,equipement5));
            // equipementsEnregistres.addAll(Arrays.asList(equipement1,equipement2,equipement3, equipement4,equipement5));
        }
    }

    private void ajouterFiles() {
        // On teste si des files sont déjà en base
        if (fileDao.count()==0) {
            for (Plage plage: plageDao.findAll()) {
                // Pour chaque plage, on ajoute 8 files
                for (byte i = 1; i <=8; i++) {
                    fileDao.save(new File(plage, i));
                }
            }

        }
    }
    private void ajouterLiensDeParente() {
        if (lienDeParenteDao.count()==0) {
            LienDeParente frereSoeur = new LienDeParente(LienDeParenteEnum.FRERE_SOEUR);
            LienDeParente cousin = new LienDeParente(LienDeParenteEnum.COUSIN_E);
            LienDeParente aucunLien = new LienDeParente(LienDeParenteEnum.AUCUN_LIEN);
            lienDeParenteDao.saveAll(Arrays.asList(frereSoeur,cousin,aucunLien));
            liensDeParenteEnregistres.add(frereSoeur);
            liensDeParenteEnregistres.add(cousin);
            liensDeParenteEnregistres.add(aucunLien);
        }
    }


    private void ajouterPays() {
        if (paysDao.count() == 0) {
            Pays paysFR = new Pays("FR", "France");
            Pays paysGB = new Pays("GB", "Royaume-Uni");
            Pays paysIT = new Pays("IT", "Italie");
            Pays paysPT = new Pays("PT", "Portugal");
            paysDao.saveAll(Arrays.asList(paysFR,paysGB,paysIT,paysPT));
            paysEnregistres.addAll(Arrays.asList(paysFR,paysGB,paysIT,paysPT));
        }
    }

    private void ajouterPlages() {
        if (plageDao.count() == 0) {
            Plage plage1 = new Plage("Cala Goloritzé", recupererConcessionnaireEnregistre(1));
            Plage plage2 = new Plage("Polignano", recupererConcessionnaireEnregistre(2));

            plageDao.saveAll(Arrays.asList(plage1,plage2));
            plagesEnregistrees.add(plage1);
            plagesEnregistrees.add(plage2);
        }
    }


    private Reservation createEmptyReservation(Client client,
                      int year, int month, int firstDay,int lastDay,
                      LienDeParente lienDeParente,Statut statut) {
        Reservation reservation = new Reservation(
                client,
                LocalDate.of(year, month, firstDay),
                LocalDate.of(year, month, lastDay),
                lienDeParente,statut);
        reservationDao.save(reservation);
        return reservation;
    }

    private Reservation createEmptyReservationWithIndices(int client,
                                        int year, int month, int firstDay,int lastDay,
                                        int lienDeParente,int statut) {
        return createEmptyReservation(
                recupererClientEnregistre(client),
                year, month, firstDay, lastDay,
                recupererLienDeParenteEnregistre(lienDeParente),recupererStatutEnregistre(statut));
    }


    private void addAffectationToReservation(Plage plage,int numFile,int numEmplacement,int nbDeLits,int nbDeFauteuils,Reservation reservation) {

        File file = fileDao.findByPlageAndNumero(plage,(byte)numFile);
        Emplacement emplacement = emplacementDao.findByFileAndNumEmplacement(file,(byte)numEmplacement);
        Equipement equipement = equipementDao.findByNbDeLitsAndNbDeFauteuils((byte)nbDeLits,(byte)nbDeFauteuils);
        Affectation affectation = new Affectation(emplacement,equipement,reservation);
        affectationDao.save(affectation);

    }

    private void addAffectationToReservationWithIndex(int plage,int numFile,int numEmplacement,int nbDeLits,int nbDeFauteuils,Reservation reservation) {
        addAffectationToReservation(recupererPlageEnregistree(plage),numFile,numEmplacement,nbDeLits,nbDeFauteuils,reservation);
    }

    private void ajouterReservations() {
        if (reservationDao.count()==0) {

            Reservation r1 = createEmptyReservationWithIndices(2,2020,5,5,7,2,1);
            addAffectationToReservationWithIndex(1,1,10,0,1,r1);
            addAffectationToReservationWithIndex(1,2,11,0,2,r1);
            addAffectationToReservationWithIndex(1,3,12,1,0,r1);
            addAffectationToReservationWithIndex(1,3,13,1,1,r1);
            addAffectationToReservationWithIndex(1,8,13,2,0,r1);

            Reservation r2 = createEmptyReservationWithIndices(2,2020,6,7,9,3,2);
            addAffectationToReservationWithIndex(1,4,10,0,1,r2);

            Reservation r3 = createEmptyReservationWithIndices(2,2020,7,9,11,1,3);
            addAffectationToReservationWithIndex(1,5,10,0,1,r3);

            Reservation r4 = createEmptyReservationWithIndices(2,2020,8,11,13,2,1);
            addAffectationToReservationWithIndex(1,6,10,0,1,r4);

            Reservation r5 = createEmptyReservationWithIndices(1,2019,8,11,13,2,1);
            addAffectationToReservationWithIndex(2,6,10,0,1,r5);

        }

    }
    private void ajouterStatuts() {
        if (statutDao.count()==0) {
            Statut enAttente = new Statut(StatutEnum.EN_ATTENTE.getNom());
            Statut acceptee = new Statut(StatutEnum.ACCEPTEE.getNom());
            Statut refusee = new Statut(StatutEnum.REFUSEE.getNom());
            statutDao.saveAll(Arrays.asList(enAttente,acceptee,refusee));
            statutsEnregistres.add(enAttente);
            statutsEnregistres.add(acceptee);
            statutsEnregistres.add(refusee);
        }
    }

    private Client recupererClientEnregistre(int idx) {
        return clientsEnregistres.get(idx-1);
    }

    private Concessionnaire recupererConcessionnaireEnregistre(int idx) {return concessionnairesEnregistres.get(idx-1);}


    // private Equipement recupererEquipementEnregistre(int idx) { return equipementsEnregistres.get(idx-1);}

    private LienDeParente recupererLienDeParenteEnregistre(int idx) {
        return liensDeParenteEnregistres.get(idx-1);
    }
    private Statut recupererStatutEnregistre(int idx) {
        return statutsEnregistres.get(idx-1);
    }

    private Pays recupererPaysEnregistre(int idx) {
        return paysEnregistres.get(idx-1);
    }
    private Plage recupererPlageEnregistree(int idx) {
        return plagesEnregistrees.get(idx-1);
    }

    public InitializeDatabase(
            AffectationDao affectationDao,
            ClientDao clientDao,
            ConcessionnaireDao concessionnaireDao,
            EmplacementDao emplacementDao,
            EquipementDao equipementDao,
            FileDao fileDao,
            LienDeParenteDao lienDeParenteDao,
            PaysDao paysDao,
            PlageDao plageDao,
            ReservationDao reservationDao,
            StatutDao statutDao

    ) {
        this.affectationDao = affectationDao;
        this.clientDao = clientDao;
        this.concessionnaireDao = concessionnaireDao;
        this.emplacementDao = emplacementDao;
        this.equipementDao = equipementDao;
        this.fileDao = fileDao;
        this.lienDeParenteDao = lienDeParenteDao;
        this.paysDao = paysDao;
        this.plageDao = plageDao;
        this.reservationDao = reservationDao;
        this.statutDao = statutDao;

    }

}
