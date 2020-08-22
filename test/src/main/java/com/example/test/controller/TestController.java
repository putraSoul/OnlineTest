package com.example.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.model.Nilai;
import com.example.test.model.Raport;
import com.example.test.model.Siswa;
import com.example.test.repository.NilaiRepository;
import com.example.test.repository.SiswaRepository;

@RestController
public class TestController {
	@Autowired
	private Utility util;
	@Autowired
	private SiswaRepository siswaRepo;
	@Autowired
	private NilaiRepository nilaiRepo;

	@GetMapping("/upload")
	public String upload() {
		Siswa siswa = new Siswa();
		Nilai nilai = new Nilai();
		
		try {
			List<String[]> nilaiCsv = util.getFileCsv("nilai");
			List<String[]> siswaCsv = util.getFileCsv("siswa");
			int nomorInduk1=-1,mataPelajaran=-1,nilaiSiswa=-1,nomorInduk2=-1,nama=-1,kelas=-1;
			
			for(int i=0; i<siswaCsv.get(0).length; i++) {
				String title = siswaCsv.get(0)[i];
				if(title.equals("nomor_induk")) nomorInduk1=i;
				if(title.equals("nama")) nama=i;
				if(title.equals("kelas")) kelas=i;
			}
			
			for(int i=0; i<nilaiCsv.get(0).length; i++) {
				String title = nilaiCsv.get(0)[i];
				if(title.equals("nomor_induk")) nomorInduk2=i;
				if(title.equals("mata_pelajaran")) mataPelajaran=i;
				if(title.equals("nilai")) nilaiSiswa=i;
			}
			
			for(int i=1; i<siswaCsv.size(); i++) {
				for (int j = 0; j < siswaCsv.get(i).length; j++) {
					if(j==nomorInduk1) siswa.setNomorInduk(siswaCsv.get(i)[j]);
					if(j==nama) siswa.setNama(siswaCsv.get(i)[j]);
					if(j==kelas) siswa.setKelas(siswaCsv.get(i)[j]);
				}
				
				siswaRepo.save(siswa);
			}

 			for(int i=1; i<nilaiCsv.size(); i++) {
				for (int j = 0; j < nilaiCsv.get(i).length; j++) {
					if(j==mataPelajaran) nilai.setMataPelajaran(nilaiCsv.get(i)[j]);
					if(j==nilaiSiswa) nilai.setNilai(nilaiCsv.get(i)[j]);
				}

				Siswa getSiswa = siswaRepo.findByNomorInduk(nilaiCsv.get(i)[nomorInduk2]);
				getSiswa.addNilai(nilai);
				siswaRepo.save(getSiswa);
			}

			return "Data berhasil diupload";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error saat upload";
		}
	}
	
	@PostMapping("/raport")
	public List<Siswa> getRaport(@RequestBody(required = false) Raport filter){
		if(filter!=null) {
			List<Siswa> listSiswa = new ArrayList<>();
			listSiswa = siswaRepo.findByFilter(filter.getNomorInduk(), filter.getNama(), filter.getMataPelajaran(), filter.getNilai());
			
			return listSiswa;
		} else {
			return siswaRepo.findAll();
		}
	}
	
	@PostMapping("/updateNilai")
	public String updateNilai(@RequestBody(required = false) Raport req) {
		if(req.getNomorInduk()==null) return "nomor induk harus diisi";
		if(req.getMataPelajaran()==null) return "mata pelajaran harus diisi";
		if(req.getNilai()==null) return "nilai harus diisi";
		
		Nilai getNilai = nilaiRepo.findById(req.getMataPelajaran()+req.getNomorInduk());
		getNilai.setNilai(req.getNilai());
		nilaiRepo.save(getNilai);
		return "Data berhasil diupdate";
	}
	
	@GetMapping("/hapusMataPelajaran")
	public String hapusMataPelajaran(@RequestParam String mataPelajaran) {
		nilaiRepo.removeMataPelajaran(mataPelajaran);
		return "Mata Pelajaran telah dihapus";
	}
}
