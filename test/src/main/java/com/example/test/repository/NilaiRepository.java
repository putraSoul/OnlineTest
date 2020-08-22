package com.example.test.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.test.model.Nilai;

public interface NilaiRepository extends JpaRepository<Nilai, Long> {
	
	@Query(value = "SELECT n FROM Nilai n WHERE n.id=?1")
	Nilai findById(String id);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM Nilai n WHERE n.mataPelajaran = ?1")
	void removeMataPelajaran(String mataPelajaran);
}