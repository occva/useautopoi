package com.use.autoapi.service.impl;

import com.use.autoapi.dto.ExcelParseResult;
import com.use.autoapi.service.ExcelAutoParseService;
import com.use.autoapi.util.ExcelAnalyzeUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelAutoParseServiceImpl implements ExcelAutoParseService {

	@Override
	public ExcelParseResult parseXlsx(MultipartFile file) {
		try {
			ExcelParseResult res = ExcelAnalyzeUtil.parse(file.getInputStream());
			return res;
		} catch (Exception e) {
			throw new RuntimeException("Failed to parse xlsx", e);
		}
	}

	@Override
	public List<ExcelParseResult> parseAllSheets(MultipartFile file) {
		try (InputStream in = file.getInputStream(); Workbook wb = WorkbookFactory.create(in)) {
			List<ExcelParseResult> results = new ArrayList<>();
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				Sheet sheet = wb.getSheetAt(i);
				ExcelParseResult r = ExcelAnalyzeUtil.parseSheet(sheet);
				r.setSheetName(sheet.getSheetName());
				results.add(r);
			}
			return results;
		} catch (Exception e) {
			throw new RuntimeException("Failed to parse all sheets", e);
		}
	}
}

