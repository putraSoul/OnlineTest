package com.example.test.controller;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;

@Component
public class Utility {
	@Value("${config.upload.path}")
	private String folderURL;
	
	public List<String[]> getFileCsv(String filename) throws Exception{
		FileReader filereader = new FileReader(folderURL+filename+".csv"); 
		
		CSVReader csvReader = new CSVReader(filereader);	    
		List<String[]> list = new ArrayList<>();
	    list = csvReader.readAll();
	    filereader.close();
	    csvReader.close();
	    return list;
	}
}
