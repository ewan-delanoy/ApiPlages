package com.ewan.apiplages.dao;

import com.ewan.apiplages.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDao extends JpaRepository<Reservation, Long> {

  public Reservation findByReservationId(Long reservationId);

	@Query(
			"SELECT r,a " +
					"FROM Reservation r " +
					"INNER JOIN r.affectations a " +
					"WHERE " +
					"(r.client.utilisateurId = :clientId) "
	)
	public List<Object> reservationsPourClient(
			@Param("clientId")Long ClientId
	);

	@Query(
			"SELECT r,a " +
					"FROM Reservation r " +
					"INNER JOIN r.affectations a " +
					"WHERE " +
					"(a.emplacement.file.plage.concessionnaire.utilisateurId = :concessionnaireId) "
	)
	public List<Object> reservationsPourConcessionnaire(
			@Param("concessionnaireId")Long ConcessionnaireId
	);



  public void deleteByReservationId(Long reservationId);

}

