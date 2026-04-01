# EXPORT INSTRUCTIONS

## Lý do chưa tạo được file .docx trực tiếp
Trong môi trường hiện tại, lệnh pandoc không khả dụng (không tìm thấy executable pandoc). Vì vậy chưa thể tự động sinh:
- thesis/football-club-management-thesis.docx

## Cách 1: Cài Pandoc rồi xuất DOCX (khuyến nghị)

### Bước 1. Cài Pandoc
- Tải tại: https://pandoc.org/installing.html
- Trên Windows có thể dùng winget:

```powershell
winget install --id JohnMacFarlane.Pandoc -e
```

### Bước 2. Xuất DOCX
Chạy tại thư mục gốc dự án:

```powershell
pandoc "thesis/football-club-management-thesis.md" -o "thesis/football-club-management-thesis.docx"
```

### Bước 3. (Tuỳ chọn) Dùng template Word
Nếu cần format theo mẫu trường:

```powershell
pandoc "thesis/football-club-management-thesis.md" --reference-doc="thesis/template.docx" -o "thesis/football-club-management-thesis.docx"
```

## Cách 2: Xuất thủ công bằng Microsoft Word
1. Mở Word.
2. Open file markdown: thesis/football-club-management-thesis.md.
3. Kiểm tra heading, bảng, mã code block.
4. Save As -> Word Document (*.docx).

## Kiểm tra sau khi xuất
- Mục lục có đúng số chương/mục không.
- Bảng endpoint hiển thị đúng.
- Ký tự tiếng Việt không lỗi font.
- Các mục TODO có được xử lý hoặc giữ đúng chủ đích.
