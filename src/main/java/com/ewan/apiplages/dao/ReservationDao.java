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
            SELECT r,a
			FROM Reservation r   
			INNER JOIN r.affectations a
			WHERE 
			(r.client.utilisateurId = :clientId) AND
			(r.statut.nom = :statutNom)  
			""")
	public List<Object> affectationsPourClient(
			@Param("clientId")Long ClientId,
			@Param("statutNom")String statutNom
	);

	@Query("""
            SELECT r,a
			FROM Reservation r   
			INNER JOIN r.affectations a
			WHERE 
			(a.emplacement.file.plage.concessionnaire.utilisateurId = :concessionnaireId) AND
			(r.statut.nom = :statutNom)  
			""")
	public List<Object> affectationsPourConcessionnaire(
			@Param("concessionnaireId")Long ConcessionnaireId,
			@Param("statutNom")String statutNom
	);



  public void deleteByReservationId(Long reservationId);

}

