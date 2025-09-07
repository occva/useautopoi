package com.test;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class multisheet {

    @Test
    public void testDetectAndPrintSheets() throws IOException {
        String filePath = "C:\\Users\\wjt12\\Downloads\\5.22test.xlsx";
        Path path = Path.of(filePath);

        if (!Files.exists(path)) {
            System.out.println("文件不存在: " + filePath);
            return;
        }

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            int sheetCount = workbook.getNumberOfSheets();
            if (sheetCount > 1) {
                System.out.println("检测到多个Sheet，共" + sheetCount + "个：");
                for (int i = 0; i < sheetCount; i++) {
                    System.out.println((i + 1) + ". " + workbook.getSheetName(i));
                }
            } else if (sheetCount == 1) {
                System.out.println("仅1个Sheet：" + workbook.getSheetName(0));
            } else {
                System.out.println("未检测到任何Sheet");
            }
        }
    }
}


