package com.use.autoapi.service;

import com.use.autoapi.dto.ExcelParseResult;
import com.use.autoapi.dto.TxtParseOptions;

import java.io.InputStream;

public interface TxtParseService {

	ExcelParseResult parse(InputStream inputStream, TxtParseOptions options) throws Exception;
}


