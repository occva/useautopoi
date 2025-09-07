package com.use.autoapi.controller;

import com.use.autoapi.dto.ExcelParseResult;
import com.use.autoapi.dto.TxtParseOptions;
import com.use.autoapi.service.TxtParseService;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/txt")
public class TxtFileController {

	@Resource
	private TxtParseService txtParseService;

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ExcelParseResult upload(
			@RequestPart("file") MultipartFile file,
			@RequestParam(value = "delimiter", required = false, defaultValue = "auto") String delimiter,
			@RequestParam(value = "hasHeader", required = false, defaultValue = "false") boolean hasHeader,
			@RequestParam(value = "charset", required = false, defaultValue = "UTF-8") String charset,
			@RequestParam(value = "trim", required = false, defaultValue = "true") boolean trim,
			@RequestParam(value = "skipEmptyLines", required = false, defaultValue = "false") boolean skipEmptyLines,
			@RequestParam(value = "maxLines", required = false, defaultValue = "10000") int maxLines
	) throws Exception {
		TxtParseOptions opts = new TxtParseOptions();
		opts.setDelimiter(delimiter);
		opts.setHasHeader(hasHeader);
		opts.setCharset(charset);
		opts.setTrim(trim);
		opts.setSkipEmptyLines(skipEmptyLines);
		opts.setMaxLines(maxLines);
		return txtParseService.parse(file.getInputStream(), opts);
	}
}


