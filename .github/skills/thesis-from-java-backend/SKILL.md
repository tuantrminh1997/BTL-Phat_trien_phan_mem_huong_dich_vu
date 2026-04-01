---
name: thesis-from-java-backend
description: Đọc backend Java và PDF tiểu luận mẫu, bỏ qua 3 trang đầu của PDF, rồi tạo bài tiểu luận mới theo cấu trúc mẫu.
---

# Khi nào dùng skill này
Dùng khi user muốn:
- đọc hiểu backend Java,
- đọc bài tiểu luận mẫu dạng PDF trong repo,
- tái sinh một bài tiểu luận mới theo cấu trúc mẫu,
- chủ đề mới là hệ thống quản lý câu lạc bộ bóng đá.

# Input bắt buộc cần có
- đường dẫn tới file PDF mẫu, ví dụ:
  `docs/sample-thesis/sample-thesis.pdf`

# Workflow chuẩn

## Bước 1: Đọc backend
Quét toàn bộ:
- README
- build files
- config files
- controllers/resources
- services
- DTOs
- entities
- repositories
- security
- exception handling

Xuất:
`reports/BACKEND_SYSTEM_ANALYSIS.md`

## Bước 2: Đọc PDF mẫu
Yêu cầu:
- bỏ qua trang 1, 2, 3
- chỉ phân tích từ trang 4 trở đi

Cần trích xuất:
- cấu trúc chương
- cấu trúc mục
- logic triển khai nội dung
- phần nào là nội dung cũ cần thay
- các heading quan trọng
- các bảng/biểu/sơ đồ nếu có đề cập trong text parse được

Xuất:
`reports/SAMPLE_THESIS_STRUCTURE_ANALYSIS.md`

## Bước 3: Lập kế hoạch nội dung
Sinh:
`reports/THESIS_CONTENT_PLAN.md`

Tài liệu này phải có:
- mapping chapter-by-chapter
- cấu trúc nào giữ
- nội dung nào thay
- placeholder/TODO nào cần thêm

## Bước 4: Soạn bài tiểu luận
Sinh:
`thesis/football-club-management-thesis.md`

Yêu cầu:
- bám cấu trúc PDF mẫu
- bám source code backend thật
- chủ đề: quản lý câu lạc bộ bóng đá
- không bịa tính năng
- nếu cần giả định thì ghi rõ

## Bước 5: Xuất docx
Thử:
- kiểm tra `pandoc --version`
- nếu có thì export sang `.docx`
- nếu không có, ghi hướng dẫn fallback

# Chất lượng bắt buộc
- Tài liệu phải có tính học thuật
- Nội dung phải có logic giữa:
  - giới thiệu
  - khảo sát yêu cầu
  - phân tích hệ thống
  - thiết kế hệ thống
  - mô hình dữ liệu
  - API/chức năng
  - kết luận
- Nếu source code cho thấy kiến trúc cụ thể, phải phản ánh vào bài viết
- Không dùng "sample text" vô nghĩa