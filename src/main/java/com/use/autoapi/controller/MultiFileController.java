package com.use.autoapi.controller;

import com.use.autoapi.service.ExcelAutoParseService;
import com.use.autoapi.dto.ExcelParseResult;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class MultiFileController {

	private final ExcelAutoParseService excelAutoParseService;

	public MultiFileController(ExcelAutoParseService excelAutoParseService) {
		this.excelAutoParseService = excelAutoParseService;
	}

	@PostMapping(value = "/upload-xlsx", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ExcelParseResult> uploadXlsx(MultipartFile file) {
		ExcelParseResult result = excelAutoParseService.parseXlsx(file);
		return ResponseEntity.ok(result);
	}

	@PostMapping(value = "/upload-xlsx-sheets", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<List<ExcelParseResult>> uploadXlsxAllSheets(MultipartFile file) {
		List<ExcelParseResult> results = excelAutoParseService.parseAllSheets(file);
		return ResponseEntity.ok(results);
	}

	@PostMapping(value = "/upload-multi", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<List<ExcelParseResult>> uploadMultiple(MultipartFile[] files) {
		List<ExcelParseResult> results = new ArrayList<>();
		for (MultipartFile f : files) {
			results.add(excelAutoParseService.parseXlsx(f));
		}
		return ResponseEntity.ok(results);
	}
}

