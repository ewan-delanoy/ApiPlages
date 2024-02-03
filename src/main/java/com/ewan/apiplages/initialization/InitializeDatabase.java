package com.ewan.apiplages.initialization;


import com.ewan.apiplages.entity.*;
import com.ewan.apiplages.dao.*;
import com.ewan.apiplages.enumeration.EquipementEnum;
import com.ewan.apiplages.enumeration.LienDeParenteEnum;
import com.ewan.apiplages.enumeration.StatutEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    private final List<Client> clientsEnregistres = new ArrayList<>();
    private final List<Concessionnaire> concessionnairesEnregistres = new ArrayList<>();

    private final List<Equipement> equipementsEnregistres = new ArrayList<>();
    private final List<LienDeParente> liensDeParenteEnregistres = new ArrayList<>();

    private final List<Pays> paysEnregistres = new ArrayList<>();
    private final List<Plage> plagesEnregistrees = new ArrayList<>();

    private final List<Statut> statutsEnregistres = new ArrayList<>();
    @Override
    public void run(String...args)  {

          runWhenActive(false);

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
            concessionnaireDao.saveAll(List.of(concessionnaire1,concessionnaire2));
            concessionnairesEnregistres.addAll(List.of(concessionnaire1,concessionnaire2));
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

            equipementDao.saveAll(List.of(equipement1,equipement2,equipement3, equipement4,equipement5));
            equipementsEnregistres.addAll(List.of(equipement1,equipement2,equipement3, equipement4,equipement5));
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
            paysDao.saveAll(List.of(paysFR,paysGB,paysIT,paysPT));
            paysEnregistres.addAll(List.of(paysFR,paysGB,paysIT,paysPT));
        }
    }

    private void ajouterPlages() {
        if (plageDao.count() == 0) {
            Plage plage1 = new Plage("Cala Goloritzé", recupererConcessionnaireEnregistre(1));
            Plage plage2 = new Plage("Acceptée", recupererConcessionnaireEnregistre(2));

            plageDao.saveAll(Arrays.asList(plage1,plage2));
            plagesEnregistrees.add(plage1);
            plagesEnregistrees.add(plage2);
        }
    }



    private void resa(Plage plage,Client client,int numFile,int numEmplacement,
                       int year, int month, int dayOfMonth,
                       LienDeParente lienDeParente,Statut statut) {
        Reservation reservation = new Reservation(
                client,
                  LocalDate.of(year, month, dayOfMonth),
                LocalDate.of(year, month, dayOfMonth+2),
                lienDeParente,statut);
        reservationDao.save(reservation);
        File file1 = fileDao.findByPlageAndNumero(plage,(byte)numFile);
        Emplacement emplacement1 = emplacementDao.findByFileAndNumEmplacement(file1,(byte)numEmplacement);
        Affectation affectation1 = new Affectation(emplacement1,recupererEquipementEnregistre(1),reservation);
        Emplacement emplacement2 = emplacementDao.findByFileAndNumEmplacement(file1,(byte)(numEmplacement+1));
        Affectation affectation2 = new Affectation(emplacement2,recupererEquipementEnregistre(4),reservation);
        affectationDao.saveAll(List.of(affectation1,affectation2));

    }

    private void iresa(int plage,int client,int numFile,int numEmplacement,
                       int year, int month, int dayOfMonth,
                       int lienDeParente,int statut) {
        resa(recupererPlageEnregistree(plage),
                recupererClientEnregistre(client),numFile,numEmplacement,year,month,dayOfMonth,
                recupererLienDeParenteEnregistre(lienDeParente),recupererStatutEnregistre(statut));
    }

    private void ajouterReservations() {
        if (reservationDao.count()==0) {
           iresa(1,1,4,18,2020,6,3,1,2);
           iresa(1,1,4,21,2020,6,10,1,2);
           iresa(1,1,4,24,2021,6,17,1,2);

           iresa(1,2,4,20,2020,6,4,2,2);
           iresa(1,2,4,23,2020,6,11,2,2);
           iresa(1,2,4,26,2020,6,18,2,2);


           iresa(1,3,3,22,2020,6,5,3,2);
           iresa(1,3,3,25,2020,6,12,3,2);
           iresa(2,3,3,28,2020,7,19,3,3);

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

    private Equipement recupererEquipementEnregistre(int idx) { return equipementsEnregistres.get(idx-1);}
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
