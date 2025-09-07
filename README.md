# UseAutoPOI

A Spring Boot service for dynamic Excel (.xlsx/.xls) file upload and parsing, powered by EasyPOI. This service provides intelligent Excel parsing with automatic header detection and structured data extraction.

<img src="https://s2.loli.net/2025/09/07/Ymsx5g3GBUDQoT4.png" alt="image-20250907185947270" width="75%" />

<img src="https://s2.loli.net/2025/09/07/VlaYRzAoecJvNyu.png" alt="image-20250907190121091" width="75%" />


## 🚀 Features

- **Smart Excel Upload**: Web interface with drag-and-drop support via `/upload.html`
- **Dynamic Parsing**: Automatically detects first-row headers and extracts structured data
- **Rich UI**: Modal table preview with horizontal scrolling and progress indicators
- **Multiple Formats**: Supports both `.xlsx` and `.xls` file formats
- **Developer Tools**: Console pretty-print for debugging and diagnostics
- **RESTful API**: Clean JSON response format for easy integration

## 🛠️ Quick Start

### Prerequisites
- Java 8 or higher
- Maven 3.6+

### Running the Application
```bash
mvn clean spring-boot:run
```

Once started, navigate to `http://localhost:9000/upload.html` to access the upload interface.

## 📡 API Reference

### Upload Excel File
**Endpoint:** `POST /excel/upload-xlsx`

**Parameters:**
- `file` (multipart/form-data): Excel file to parse



## 🏗️ Project Structure

```
com.use.autoapi/
├── UseautopoiApplication.java          # Main application entry point
├── controller/
│   └── MultiFileController.java        # REST endpoints for file upload
├── service/
│   ├── ExcelAutoParseService.java      # Service interface
│   ├── FileProcessingService.java      # File processing utilities
│   └── impl/
│       └── ExcelAutoParseServiceImpl.java  # Core parsing implementation
├── dto/
│   ├── ExcelColumnInfo.java           # Column metadata model
│   ├── ExcelParseResult.java          # Parse result wrapper
│   └── FileUploadResponse.java        # API response model
└── util/
    └── ExcelAnalyzeUtil.java          # Excel analysis utilities
```

## 🔧 Configuration

The application runs on port `9000` by default. You can modify this in `application.properties`:

```properties
server.port=9000
```

## 🤝 Acknowledgments

This project is built upon the excellent work of:
- [jeecgboot/autopoi](https://github.com/jeecgboot/autopoi) - Core Excel processing capabilities
- EasyPOI community for simplifying Excel operations in Java
