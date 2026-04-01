---
mode: agent
description: Đọc backend Java + đọc PDF mẫu từ trang 4 trở đi + sinh bài tiểu luận mới về quản lý câu lạc bộ bóng đá.
---

Hãy dùng agent hiện tại và skill thesis-from-java-backend để thực hiện toàn bộ workflow sau trong một lần chạy:

## Input
- PDF mẫu nằm tại: {{pdf_path}}

## Nhiệm vụ
1. Đọc hiểu toàn bộ source code backend Java trong workspace.
2. Tạo báo cáo phân tích hệ thống backend.
3. Đọc file PDF mẫu tại đường dẫn đã cho, nhưng phải bỏ qua hoàn toàn 3 trang đầu; chỉ phân tích từ trang 4 trở đi.
4. Rút ra cấu trúc bài tiểu luận mẫu và xác định phần nào là thông tin cũ cần thay thế.
5. Dựa trên source code backend Java hiện tại, sinh bài tiểu luận mới với chủ đề:
   "Hệ thống quản lý câu lạc bộ bóng đá"
6. Bài mới phải bám cấu trúc bài mẫu nhưng thay toàn bộ nội dung kỹ thuật bằng thông tin phù hợp với source code backend hiện có.
7. Tạo các file:
   - reports/BACKEND_SYSTEM_ANALYSIS.md
   - reports/SAMPLE_THESIS_STRUCTURE_ANALYSIS.md
   - reports/THESIS_CONTENT_PLAN.md
   - thesis/football-club-management-thesis.md
8. Ưu tiên xuất thêm:
   - thesis/football-club-management-thesis.docx
9. Nếu không thể xuất docx trực tiếp, tạo:
   - thesis/EXPORT_INSTRUCTIONS.md
   và giải thích rõ lý do.

## Quy tắc bắt buộc
- Không dùng nội dung 3 trang đầu của PDF mẫu cho phần cấu trúc nội dung chính.
- Không được bịa chức năng backend.
- Nếu thiếu bằng chứng từ source code thì ghi TODO hoặc placeholder rõ ràng.
- Mọi kết luận quan trọng phải truy vết được về source code hoặc PDF mẫu.
- Chủ đề phải nhất quán là quản lý câu lạc bộ bóng đá.

## Cách cập nhật tiến độ trong chat
Mỗi lần báo tiến độ phải ghi:
1. Phase hiện tại
2. Việc vừa hoàn thành
3. File đã tạo/cập nhật
4. Điểm còn mơ hồ
5. Bước tiếp theo

Bắt đầu ngay.