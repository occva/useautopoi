package com.use.autoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelColumnInfo {

	private String headerName;
	private int columnIndex;
}

