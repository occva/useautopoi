package com.use.autoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileUploadResponse {

	private String fileName;
	private long fileSize;
}

