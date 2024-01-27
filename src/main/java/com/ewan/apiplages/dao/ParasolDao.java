package com.ewan.apiplages.dao;

import com.ewan.apiplages.entity.Parasol;
import com.ewan.apiplages.entity.Plage;
import com.ewan.apiplages.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ParasolDao extends JpaRepository<Parasol, Long> {


       @Query("""
			FROM Parasol p   
			INNER JOIN p.reservations r 
			WHERE 
			(p.file.plage = :plage) AND
			((r.dateDebut BETWEEN :dateDebut AND :dateFin)  
			OR
			(r.dateFin BETWEEN :dateDebut AND :dateFin)) 
			""")
       List<Object[]> parasolsDisponibles(@Param("plage") Plage plage,
                                         @Param("dateDebut") LocalDate dateDebut,
                                         @Param("dateFin") LocalDate dateFin);



        Parasol findByFilePlageAndFileNumeroAndNumEmplacement(
                Plage plage, byte numeroFile, byte numEmplacement
        );

}
