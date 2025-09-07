# useautopoi

A modern Spring Boot service for dynamic Excel (.xlsx/.xls) and text file upload and parsing powered by EasyPOI.

## 来源
- 本项目来源于开源项目 [jeecgboot/autopoi](https://github.com/jeecgboot/autopoi)，在此致以感谢。

## ✨ Features

![image-20250907225120223](https://s2.loli.net/2025/09/07/mJHRkLOhAvBC75K.png)

### 🎨 Modern UI Design
- **渐变背景**: 优雅的蓝色渐变背景 (RGB 240,246,254 → RGB 225,231,253)
- **拖拽上传**: 直观的拖拽文件上传界面，支持点击选择
- **动画效果**: 流畅的悬停动画和过渡效果
- **响应式设计**: 适配各种屏幕尺寸的现代化界面

### 📁 File Support
- **Excel文件**: 支持 .xlsx 和 .xls 格式
- **文本文件**: 支持 .txt 格式解析
- **多工作表**: Excel多工作表标签页切换预览
- **文件验证**: 智能文件类型检测和错误提示

### 📊 Data Display
- **模态预览**: 全屏模态窗口展示解析结果
- **行号显示**: 30px宽度居中对齐的行号列
- **水平滚动**: 大表格水平滚动支持
- **进度跟踪**: 底部实时进度条显示上传和解析状态

### 🔧 Technical Features
- 后端动态解析首行表头，返回结构化 JSON 数据
- 控制台美化表格输出用于调试
- RESTful API 设计
- 异常处理和用户友好的错误提示

## 🏃‍♂️ 快速开始

### 运行项目
```bash
mvn clean spring-boot:run
```

### 访问应用
打开浏览器访问: `http://localhost:9000/upload.html`

### 使用方式
1. **拖拽上传**: 直接将文件拖拽到上传区域
2. **点击上传**: 点击上传区域选择文件
3. **支持格式**: .xlsx, .xls, .txt
4. **实时预览**: 上传后自动在模态窗口中预览解析结果

## 🚀 API Endpoints

### Excel文件上传

![image-20250907225146952](https://s2.loli.net/2025/09/07/275OahtpsJn9ECd.png)

- **POST** `/excel/upload-xlsx-sheets`: 上传Excel文件并解析所有工作表
  - 参数: `file` (multipart/form-data)
  - 响应: 工作表数组，每个包含 columns 和 rows

### 文本文件上传

![image-20250907225516026](https://s2.loli.net/2025/09/07/N8fzoTrKMOGhm1Z.png)

- **POST** `/txt/upload`: 上传文本文件并解析
  - 参数: `file` (multipart/form-data)
  - 响应: 单个解析结果对象

## 📦 项目结构

### Java包结构
```
com.use.autoapi
├─ UseautopoiApplication.java          # 主启动类
├─ controller/
│  ├─ MultiFileController.java         # Excel文件上传控制器
│  └─ TxtFileController.java           # 文本文件上传控制器
├─ service/
│  ├─ ExcelAutoParseService.java       # Excel解析服务接口
│  ├─ FileProcessingService.java       # 文件处理服务接口
│  ├─ TxtParseService.java             # 文本解析服务接口
│  └─ impl/
│     ├─ ExcelAutoParseServiceImpl.java # Excel解析服务实现
│     └─ TxtParseServiceImpl.java      # 文本解析服务实现
├─ dto/
│  ├─ ExcelColumnInfo.java             # Excel列信息DTO
│  ├─ ExcelParseResult.java            # Excel解析结果DTO
│  ├─ FileUploadResponse.java          # 文件上传响应DTO
│  └─ TxtParseOptions.java             # 文本解析选项DTO
└─ util/
   └─ ExcelAnalyzeUtil.java            # Excel分析工具类
```



