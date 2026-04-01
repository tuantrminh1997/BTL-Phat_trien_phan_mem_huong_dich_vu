# Repository Copilot Instructions

Bạn đang làm việc trong một repository có mục tiêu:
1. đọc hiểu toàn bộ source code backend Java trong workspace,
2. đọc một file PDF bài tiểu luận mẫu đặt trong repo,
3. bỏ qua 3 trang đầu của file PDF mẫu, chỉ dùng nội dung từ trang 4 trở đi,
4. suy ra cấu trúc tài liệu, đề mục, phong cách trình bày và các phần thông tin cần thay thế,
5. tạo ra một bài tiểu luận mới có chủ đề "quản lý câu lạc bộ bóng đá",
6. nội dung kỹ thuật của bài mới phải bám vào source code backend Java thật trong repo,
7. ưu tiên tạo file Word (.docx) nếu môi trường hỗ trợ; nếu không hỗ trợ thì phải tạo bản Markdown hoàn chỉnh và thêm script/hướng dẫn chuyển đổi.

## Quy tắc bắt buộc
- Không được bịa chức năng backend nếu source code không có bằng chứng.
- Mọi kết luận về nghiệp vụ, kiến trúc, API, thực thể dữ liệu, luồng xử lý đều phải truy vết được về source code.
- Không được dùng 3 trang đầu của file PDF mẫu để suy ra cấu trúc nội dung chính.
- Chỉ đọc và phân tích nội dung PDF từ trang 4 trở đi.
- Khi dùng PDF mẫu, phải phân biệt rõ:
  - phần nào là cấu trúc mẫu cần giữ,
  - phần nào là thông tin cũ phải thay bằng dữ liệu của source code hiện tại.
- Nếu PDF mẫu có thông tin không thể suy ra tương ứng từ source code mới, phải ghi TODO hoặc chèn placeholder có chú thích rõ.
- Không được xóa bỏ cấu trúc quan trọng của bài mẫu khi chưa có lý do rõ ràng.
- Mọi output lớn phải có:
  1. findings từ backend,
  2. findings từ PDF mẫu,
  3. mapping cấu trúc cũ -> nội dung mới,
  4. file đã tạo,
  5. assumptions/TODO.

## Khi đọc backend Java phải kiểm tra tối thiểu
- README.md
- build.gradle / build.gradle.kts / pom.xml
- settings.gradle / settings.gradle.kts
- docker-compose.yml
- Dockerfile
- application.properties / application.yml
- main application class
- controllers / resources
- services / use cases
- DTOs
- entities / models
- repositories
- validators
- exception handlers
- security/auth config
- CORS config
- swagger/openapi nếu có

## Output ưu tiên
1. reports/BACKEND_SYSTEM_ANALYSIS.md
2. reports/SAMPLE_THESIS_STRUCTURE_ANALYSIS.md
3. reports/THESIS_CONTENT_PLAN.md
4. thesis/football-club-management-thesis.md
5. thesis/football-club-management-thesis.docx (nếu môi trường hỗ trợ)
6. thesis/EXPORT_INSTRUCTIONS.md nếu không xuất được .docx trực tiếp