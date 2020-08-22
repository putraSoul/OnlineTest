package com.example.test.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Siswa {
	@Id
	private String nomorInduk;
	private String nama;
	@JsonIgnore
	private String kelas;
	@OneToMany(mappedBy="siswa", cascade = CascadeType.ALL)
	private List<Nilai> nilai = new ArrayList<>();
	
	public String getNomorInduk() {
		return nomorInduk;
	}
	public void setNomorInduk(String nomorInduk) {
		this.nomorInduk = nomorInduk;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getKelas() {
		return kelas;
	}
	public void setKelas(String kelas) {
		this.kelas = kelas;
	}
	public List<Nilai> getNilai() {
		return nilai;
	}
	public void setNilai(List<Nilai> nilai) {
		this.nilai = nilai;
	}
	public void addNilai(Nilai nilaiData) {
		nilaiData.setId(nilaiData.getMataPelajaran()+this.getNomorInduk());
		this.nilai.add(nilaiData);
		nilaiData.setSiswa(this);
	}
	public void removeNilai(Nilai nilaiData) {
		this.nilai.remove(nilaiData);
		nilaiData.setSiswa(null);
	}
	
}
