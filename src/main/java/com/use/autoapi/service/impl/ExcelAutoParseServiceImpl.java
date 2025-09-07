package com.use.autoapi.service.impl;

import com.use.autoapi.dto.ExcelParseResult;
import com.use.autoapi.service.ExcelAutoParseService;
import com.use.autoapi.util.ExcelAnalyzeUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelAutoParseServiceImpl implements ExcelAutoParseService {

	@Override
	public ExcelParseResult parseXlsx(MultipartFile file) {
		try {
			return ExcelAnalyzeUtil.parse(file.getInputStream());
		} catch (Exception e) {
			throw new RuntimeException("Failed to parse xlsx", e);
		}
	}
}

