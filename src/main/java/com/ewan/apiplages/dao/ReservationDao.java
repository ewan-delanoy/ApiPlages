package com.ewan.apiplages.dao;

import com.ewan.apiplages.entity.Plage;
import com.ewan.apiplages.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDao extends JpaRepository<Reservation, Long> {

  public Reservation findByReservationId(Long reservationId);

  @Query("""
			FROM Reservation r   
			INNER JOIN r.parasols pa
			WHERE 
			(pa.file.plage.concessionnaire.utilisateurId = :concessionnaireId) AND
			(r.statut.nom = :statutNom)  
			""")
  public List<Reservation> reservationsAvecStatut(
      @Param("concessionnaireId")Long ConcessionnaireId,
      @Param("statutNom")String statutNom
  );
}

