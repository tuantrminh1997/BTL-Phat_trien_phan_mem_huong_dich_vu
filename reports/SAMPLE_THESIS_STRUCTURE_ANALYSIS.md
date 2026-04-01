# SAMPLE THESIS STRUCTURE ANALYSIS

## 1. Phạm vi phân tích PDF
- File mẫu: docs/sample-thesis/sample-thesis.pdf
- Đã trích xuất text bằng lệnh pdftotext với tham số bắt đầu từ trang 4.
- File trích xuất: reports/sample-thesis-page4plus.txt

Khẳng định tuân thủ ràng buộc:
- Đã bỏ qua hoàn toàn trang 1, 2, 3.
- Chỉ dùng dữ liệu từ trang 4 trở đi để suy ra cấu trúc nội dung chính.

## 2. Findings từ PDF mẫu (trang 4+)
Các phần khung chính quan sát được:
- I. TÊN ĐỀ TÀI
- II. GIỚI THIỆU CHUNG
  - 2.1 Mở đầu
  - 2.2 Các khái niệm và công nghệ nền tảng
    - 2.2.1 REST
    - 2.2.2 API REST
    - 2.2.3 Công nghệ và công cụ sử dụng trong dự án
- III. THỰC HÀNH XÂY DỰNG DỊCH VỤ WEB
  - 3.1 Cấu trúc cây thư mục tổng thể
  - 3.2 Các bước triển khai dự án (chia nhỏ theo từng artefact: data/model/service/resource/application/deploy-test)
- IV. ĐÁNH GIÁ KẾT QUẢ VÀ HƯỚNG MỞ RỘNG
  - 4.1 Đánh giá kết quả
    - 4.1.1 Ưu điểm
    - 4.1.2 Hạn chế/vấn đề & giải pháp
  - 4.2 Hướng mở rộng
- KẾT LUẬN
- TÀI LIỆU THAM KHẢO

## 3. Phong cách trình bày mẫu
- Trình bày theo chương mục có đánh số rõ ràng, dùng số La Mã cho chương lớn.
- Mỗi mục có:
  - mô tả lý thuyết ngắn,
  - mô tả thực hành theo từng bước,
  - ví dụ endpoint hoặc đoạn code minh họa.
- Có xu hướng giải thích thuật ngữ trong ngoặc, phù hợp văn phong tiểu luận môn học.
- Chương III thiên về mô tả quy trình xây dựng dự án theo artefact kỹ thuật cụ thể.
- Chương IV cân bằng giữa đánh giá kết quả đạt được và kế hoạch mở rộng.

## 4. Những phần thông tin cũ phải thay thế
Thông tin của dự án cũ trong PDF mẫu cần thay toàn bộ:
- Miền bài toán cũ: quản lý sinh viên.
- Công nghệ cũ: JAX-RS, NetBeans, GlassFish, XML data store (sinhvien.xml).
- Endpoint cũ: /api/sinhvien/... theo mã số/lớp.
- Tên class cũ: SinhVien, SinhVienService, SinhVienResource, RestApplication (ngữ cảnh dự án mẫu).
- Mã mẫu JSON/XML xoay quanh dữ liệu sinh viên.

## 5. Các thành phần cấu trúc nên giữ
- Bộ khung chương mục I -> IV, Kết luận, Tài liệu tham khảo.
- Cách viết kết hợp lý thuyết REST + công nghệ + quy trình triển khai.
- Cách trình bày bước thực hành theo chuỗi artefact để người đọc theo dõi dễ.
- Mục đánh giá ưu/nhược điểm và hướng mở rộng ở cuối.

## 6. Các thành phần cần điều chỉnh khi tái sử dụng cấu trúc
- Chương III cần đổi từ quy trình JAX-RS + XML sang Spring Boot + JPA + PostgreSQL + Docker.
- Ví dụ API phải chuyển thành /players và /coaches.
- Dữ liệu minh họa phải phản ánh field thật của Player và Coach.
- Validation cần mô tả theo validation-rules.xml của dự án hiện tại (không phải XML data source).
- Bổ sung rõ rằng hệ thống hiện chưa có security/auth và chưa có DTO, tránh suy diễn.

## 7. Các điểm mơ hồ khi parse PDF
- PDF có một số nội dung hình ảnh/screenshot (đặc biệt phần kiểm thử bằng Postman) không trích đầy đủ bằng text parser.
- Vì vậy, phần luận văn mới sẽ giữ placeholder cho ảnh minh họa triển khai/kiểm thử nếu cần nộp bản hoàn chỉnh có hình.

## 8. Kết luận
Cấu trúc mẫu từ trang 4+ đủ rõ để tái sử dụng gần như nguyên khung cho đề tài mới. Cần thay toàn bộ nội dung kỹ thuật bên trong bằng dữ liệu có bằng chứng từ backend SoccerRest.
