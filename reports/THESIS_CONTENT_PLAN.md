# THESIS CONTENT PLAN

## 1. Mục tiêu
Lập kế hoạch nội dung tiểu luận mới theo cấu trúc PDF mẫu (trang 4+), với chủ đề:
- Hệ thống quản lý câu lạc bộ bóng đá

Nội dung kỹ thuật bắt buộc bám vào backend SoccerRest trong workspace.

## 2. Mapping cấu trúc cũ -> nội dung mới

### I. TÊN ĐỀ TÀI
- Cấu trúc giữ nguyên: Có
- Nội dung mới:
  - Xây dựng dịch vụ web REST quản lý câu lạc bộ bóng đá bằng Spring Boot và PostgreSQL.

### II. GIỚI THIỆU CHUNG
- Cấu trúc giữ nguyên: Có
- Nội dung thay thế:
  - 2.1 Mở đầu: mô tả bài toán quản lý cầu thủ và huấn luyện viên.
  - 2.2 Khái niệm nền tảng:
    - REST/REST API (giữ khung lý thuyết).
    - Công nghệ: Spring Boot 4, Spring Data JPA, PostgreSQL, OpenAPI, Docker, Gradle.
  - Bỏ toàn bộ nội dung NetBeans/GlassFish/JAX-RS cũ.

### III. THỰC HÀNH XÂY DỰNG DỊCH VỤ WEB
- Cấu trúc giữ nguyên: Có (3.1, 3.2)
- Nội dung thay thế:
  - 3.1 Cây thư mục dự án SoccerRest (theo package thực tế).
  - 3.2 Các bước triển khai:
    - 3.2.1 Khởi tạo project và dependencies (build.gradle.kts)
    - 3.2.2 Khai báo mô hình dữ liệu (Player/Coach/BaseEntity)
    - 3.2.3 Tầng repository
    - 3.2.4 Tầng service + validation theo XML rules
    - 3.2.5 Tầng controller và endpoint CRUD
    - 3.2.6 Exception handling + OpenAPI
    - 3.2.7 Chạy hệ thống local/Docker và kiểm thử endpoint

### IV. ĐÁNH GIÁ KẾT QUẢ VÀ HƯỚNG MỞ RỘNG
- Cấu trúc giữ nguyên: Có
- Nội dung thay thế:
  - 4.1 Đánh giá dựa trên hệ thống thật:
    - ưu điểm: phân lớp rõ, có OpenAPI, có validation cấu hình ngoài code.
    - hạn chế: chưa có auth, chưa có DTO, test còn ít.
  - 4.2 Hướng mở rộng:
    - bổ sung bảo mật JWT/OAuth2,
    - phân trang/lọc/sắp xếp,
    - test tự động,
    - CI/CD,
    - tối ưu dữ liệu và quan hệ domain.

### KẾT LUẬN
- Cấu trúc giữ nguyên: Có
- Nội dung mới:
  - Tổng kết kết quả đạt được từ backend SoccerRest và khả năng mở rộng thành hệ thống hoàn chỉnh.

### TÀI LIỆU THAM KHẢO
- Cấu trúc giữ nguyên: Có
- Nội dung mới:
  - Tài liệu Spring Boot, Spring Data JPA, Springdoc OpenAPI, PostgreSQL, Docker, REST best practices.

## 3. Dàn ý dữ liệu kỹ thuật bắt buộc đưa vào tiểu luận
- Endpoint thực tế:
  - /players, /players/{id}
  - /coaches, /coaches/{id}
- Validation:
  - rule từ validation-rules.xml cho Player/Coach.
- Mô hình dữ liệu:
  - field thật của Player và Coach.
- Xử lý lỗi:
  - GlobalExceptionHandler với ValidationException (HTTP 400).
- Vận hành:
  - Docker Compose có app + postgres.
  - application.properties nhận biến môi trường.
- Tài liệu API:
  - /swagger-ui.html, /v3/api-docs.

## 4. Placeholder/TODO bắt buộc minh bạch
- TODO: Ảnh chụp màn hình Postman/Swagger chưa được tự động trích từ PDF mẫu, cần bổ sung thủ công nếu trường yêu cầu hình minh họa.
- TODO: Chưa có số liệu benchmark hiệu năng thực nghiệm.
- TODO: Chưa có bằng chứng module security hoặc phân quyền người dùng.

## 5. Tiêu chí kiểm tra trước khi chốt bài
- Không có chức năng nào vượt quá phạm vi Player/Coach CRUD + validation + OpenAPI + docker runtime đã có trong code.
- Không dùng chi tiết từ 3 trang đầu PDF mẫu.
- Mọi tuyên bố kỹ thuật đều truy vết được về source code hoặc phần cấu trúc từ PDF trang 4+.
