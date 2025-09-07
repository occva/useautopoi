# useautopoi

A Spring Boot service for dynamic Excel (.xlsx/.xls) upload and parsing powered by EasyPOI.

## 致谢与来源
- 本项目来源于开源项目 [jeecgboot/autopoi](https://github.com/jeecgboot/autopoi)，在此致以感谢。

## Features
- Upload Excel files via `/upload.html` (Tailwind UI)
- Backend parses first-row headers and returns `{ columns, rows }` JSON
- Modal table preview with horizontal scrolling; bottom progress bar
- Console pretty table print for diagnostics

## How to Run
```
mvn clean spring-boot:run
```
Open `http://localhost:9000/upload.html` and upload an Excel file.

## API
- POST `/excel/upload-xlsx`: multipart file param `file`, response:
```
{
  "columns": [{ "headerName": "Address", "columnIndex": 0 }],
  "rows": [["0xabc", "12.3"]]
}
```

## Packages
```
com.use.autoapi
├─ UseautopoiApplication.java
├─ controller
│  └─ MultiFileController.java
├─ service
│  ├─ ExcelAutoParseService.java
│  ├─ FileProcessingService.java
│  └─ impl
│     └─ ExcelAutoParseServiceImpl.java
├─ dto
│  ├─ ExcelColumnInfo.java
│  ├─ ExcelParseResult.java
│  └─ FileUploadResponse.java
└─ util
   └─ ExcelAnalyzeUtil.java
```

## Credits
- EasyPOI / AutoPOI for simplifying Excel operations.