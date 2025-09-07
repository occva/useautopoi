package com.use.autoapi.service.impl;

import com.use.autoapi.dto.ExcelColumnInfo;
import com.use.autoapi.dto.ExcelParseResult;
import com.use.autoapi.dto.TxtParseOptions;
import com.use.autoapi.service.TxtParseService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TxtParseServiceImpl implements TxtParseService {

	@Override
	public ExcelParseResult parse(InputStream inputStream, TxtParseOptions options) throws Exception {
		Charset cs = Charset.forName(options.getCharset() == null ? "UTF-8" : options.getCharset());
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, cs))) {
			String delimiterRegex = resolveDelimiterRegex(reader, options);
			List<String> headerList = new ArrayList<>();
			List<List<String>> rows = new ArrayList<>();

			String line;
			int lineCount = 0;
			boolean headerDone = false;
			while ((line = reader.readLine()) != null) {
				if (options.isSkipEmptyLines() && (line.isEmpty() || line.trim().isEmpty())) {
					continue;
				}
				lineCount++;
				if (lineCount > options.getMaxLines()) {
					break;
				}

				List<String> cells = splitLine(line, delimiterRegex, options.isTrim());

				if (options.isHasHeader() && !headerDone) {
					headerList = cells;
					headerDone = true;
					continue;
				}

				rows.add(cells);
			}

			int maxColumns = headerList.isEmpty() ? rows.stream().mapToInt(List::size).max().orElse(0) : headerList.size();
			// 对齐每行列数
			for (List<String> r : rows) {
				if (r.size() < maxColumns) {
					for (int k = r.size(); k < maxColumns; k++) r.add("");
				} else if (r.size() > maxColumns) {
					r.subList(maxColumns, r.size()).clear();
				}
			}

			List<ExcelColumnInfo> columns = new ArrayList<>();
			for (int i = 0; i < headerList.size(); i++) {
				columns.add(new ExcelColumnInfo(headerList.get(i), i));
			}

			ExcelParseResult result = new ExcelParseResult();
			result.setColumns(columns);
			result.setRows(rows);
			return result;
		}
	}

	private String resolveDelimiterRegex(BufferedReader reader, TxtParseOptions options) throws Exception {
		reader.mark(8192);
		String first = reader.readLine();
		reader.reset();
		String d = options.getDelimiter();
		if (d == null || d.isEmpty() || "auto".equalsIgnoreCase(d)) {
			// 仅在自动模式下考虑常见结构化分隔符：tab、逗号、分号
			int cComma = first == null ? 0 : count(first, ',');
			int cSemi = first == null ? 0 : count(first, ';');
			int cTab = first == null ? 0 : count(first, '\t');
			// 优先选择 tab，其次逗号，再次分号；避免误将普通英文句子中的空格当作分隔符
			if (cTab > 0) return "\t";
			if (cComma >= cSemi && cComma > 0) return ",";
			if (cSemi > 0) return ";";
			// 若首行无明显的结构化分隔符，则不分列（整行一个单元格）
			return null;
		}
		if (Arrays.asList("comma",";",",", "semicolon", "tab", "space").contains(d)) {
			if ("comma".equalsIgnoreCase(d) || ",".equals(d)) return ",";
			if ("semicolon".equalsIgnoreCase(d) || ";".equals(d)) return ";";
			if ("tab".equalsIgnoreCase(d)) return "\t";
			if ("space".equalsIgnoreCase(d)) return " +";
		}
		// 其他自定义分隔符，按字面匹配（必要时转义）
		return java.util.regex.Pattern.quote(d);
	}

	private int count(String s, char ch) {
		int c = 0;
		if (s == null) return 0;
		for (int i = 0; i < s.length(); i++) if (s.charAt(i) == ch) c++;
		return c;
	}

	private List<String> splitLine(String line, String delimiterRegex, boolean trim) {
		if (delimiterRegex == null) {
			List<String> single = new ArrayList<>(1);
			single.add(trim ? line.trim() : line);
			return single;
		}
		String[] arr = line.split(delimiterRegex, -1);
		List<String> list = new ArrayList<>(arr.length);
		for (String s : arr) list.add(trim ? s.trim() : s);
		return list;
	}
}


