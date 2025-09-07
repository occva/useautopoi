package com.use.autoapi.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileProcessingService {

	File saveTemp(MultipartFile file);
}

