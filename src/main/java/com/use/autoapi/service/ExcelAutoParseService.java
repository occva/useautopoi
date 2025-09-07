package com.use.autoapi.service;

import com.use.autoapi.dto.ExcelParseResult;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelAutoParseService {

	ExcelParseResult parseXlsx(MultipartFile file);

	/**
	 * 遍历 Excel 的所有 Sheet，分别解析并返回结果列表。
	 */
	java.util.List<ExcelParseResult> parseAllSheets(MultipartFile file);
}

