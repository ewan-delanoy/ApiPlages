package com.ewan.apiplages.dao;

import com.ewan.apiplages.entity.Emplacement;
import com.ewan.apiplages.entity.File;
import com.ewan.apiplages.entity.Plage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmplacementDao extends JpaRepository<Emplacement, Long> {

	@Query(
            "SELECT e.emplacementId " +
      		"FROM Emplacement e " +
      		"INNER JOIN e.affectations a " +
      		"WHERE " +
      		"(e.file.plage = :plage) AND " +
      		"((a.reservation.dateDebut BETWEEN :dateDebut AND :dateFin) " +
      		"OR " +
      		"(a.reservation.dateFin BETWEEN :dateDebut AND :dateFin)) "
      		)
	List<Long> idsDesEmplacementsOccupes(@Param("plage") Plage plage,
									 @Param("dateDebut") LocalDate dateDebut,
									 @Param("dateFin") LocalDate dateFin);
	Emplacement findByFileAndNumEmplacement(File file,byte numEmplacement);

	Emplacement findByFileNumeroAndNumEmplacement(byte numeroFile,byte numEmplacement);
	List<Emplacement> findByFilePlagePlageId(Long plageId);

	// Emplacement findByEmplacementId(Long emplacementId);
}
