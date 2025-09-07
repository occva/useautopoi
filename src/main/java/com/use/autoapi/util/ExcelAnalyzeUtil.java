package com.use.autoapi.util;

import com.use.autoapi.dto.ExcelColumnInfo;
import com.use.autoapi.dto.ExcelParseResult;
import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelAnalyzeUtil {

	public static ExcelParseResult parse(InputStream in) {
		try (Workbook wb = WorkbookFactory.create(in)) {
			Sheet sheet = wb.getSheetAt(0);
			Row header = sheet.getRow(0);
			if (header == null) {
				throw new IllegalArgumentException("Empty sheet header");
			}
			List<ExcelColumnInfo> cols = new ArrayList<>();
			for (int c = 0; c < header.getLastCellNum(); c++) {
				Cell cell = header.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cols.add(new ExcelColumnInfo(cell.toString(), c));
			}
			List<List<String>> rows = new ArrayList<>();
			for (int r = 1; r <= sheet.getLastRowNum(); r++) {
				Row row = sheet.getRow(r);
				if (row == null) continue;
				List<String> vals = new ArrayList<>();
				for (int c = 0; c < cols.size(); c++) {
					Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					vals.add(getCellString(cell));
				}
				rows.add(vals);
			}
			ExcelParseResult res = new ExcelParseResult();
			res.setColumns(cols);
			res.setRows(rows);
			return res;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static ExcelParseResult parseSheet(Sheet sheet) {
		Row header = sheet.getRow(0);
		if (header == null) {
			throw new IllegalArgumentException("Empty sheet header");
		}
		List<ExcelColumnInfo> cols = new ArrayList<>();
		for (int c = 0; c < header.getLastCellNum(); c++) {
			Cell cell = header.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cols.add(new ExcelColumnInfo(cell.toString(), c));
		}
		List<List<String>> rows = new ArrayList<>();
		for (int r = 1; r <= sheet.getLastRowNum(); r++) {
			Row row = sheet.getRow(r);
			if (row == null) continue;
			List<String> vals = new ArrayList<>();
			for (int c = 0; c < cols.size(); c++) {
				Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				vals.add(getCellString(cell));
			}
			rows.add(vals);
		}
		ExcelParseResult res = new ExcelParseResult();
		res.setColumns(cols);
		res.setRows(rows);
		return res;
	}

	private static String getCellString(Cell cell) {
		if (cell == null) return "";
		if (cell.getCellType() == CellType.NUMERIC) {
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue().toString();
			}
			return String.valueOf(cell.getNumericCellValue());
		}
		if (cell.getCellType() == CellType.BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		}
		return cell.toString();
	}
}

