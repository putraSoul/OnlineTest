package com.example.test.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Nilai {
	@Id
	private String Id;
	private String mataPelajaran;
	private String nilai;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Siswa siswa;

	@JsonIgnore
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getMataPelajaran() {
		return mataPelajaran;
	}
	public void setMataPelajaran(String mataPelajaran) {
		this.mataPelajaran = mataPelajaran;
	}
	public String getNilai() {
		return nilai;
	}
	public void setNilai(String nilai) {
		this.nilai = nilai;
	}
	public Siswa getSiswa() {
		return siswa;
	}
	public void setSiswa(Siswa siswa) {
		this.siswa = siswa;
	}
	
}
