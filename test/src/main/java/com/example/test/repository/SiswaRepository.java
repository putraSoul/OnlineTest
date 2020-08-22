package com.example.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.test.model.Siswa;

public interface SiswaRepository extends JpaRepository<Siswa, Long> {
	
	@Query(value = "SELECT u FROM Siswa u WHERE u.nomorInduk=?1")
	Siswa findByNomorInduk(String string);
	
	@Query(value = "SELECT s FROM Siswa s JOIN s.nilai n WHERE (:nomorInduk is null or s.nomorInduk = :nomorInduk) AND (:nama is null or s.nama = :nama) AND (:mataPelajaran is null or n.mataPelajaran = :mataPelajaran) AND (:nilai is null or n.nilai = :nilai) GROUP BY s.nomorInduk")
	List<Siswa> findByFilter(@Param("nomorInduk") String nomorInduk, @Param("nama") String nama, @Param("mataPelajaran") String mataPelajaran, @Param("nilai") String nilai);
}
