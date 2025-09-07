package com.use.autoapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExcelParseResult {

	private List<ExcelColumnInfo> columns;
	private List<List<String>> rows;
}

