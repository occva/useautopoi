package com.use.autoapi.service;

import com.use.autoapi.dto.ExcelParseResult;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelAutoParseService {

	ExcelParseResult parseXlsx(MultipartFile file);
}

