package com.use.autoapi.dto;

import lombok.Data;

@Data
public class TxtParseOptions {

	/** 分隔符：auto(默认)、comma、semicolon、tab、space 或自定义字符 */
	private String delimiter = "auto";

	/** 首行是否为表头 */
	private boolean hasHeader = true;

	/** 字符集，默认 UTF-8 */
	private String charset = "UTF-8";

	/** 去除单元格前后空白 */
	private boolean trim = true;

	/** 跳过空行 */
	private boolean skipEmptyLines = true;

	/** 解析最大行数，防止过大文件占用内存 */
	private int maxLines = 10000;
}


