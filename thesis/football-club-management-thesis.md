# TIỂU LUẬN
## HỆ THỐNG QUẢN LÝ CÂU LẠC BỘ BÓNG ĐÁ

---

## I. TÊN ĐỀ TÀI
Xây dựng dịch vụ web theo kiến trúc REST cho bài toán quản lý câu lạc bộ bóng đá, tập trung vào quản lý cầu thủ và huấn luyện viên, sử dụng Spring Boot, Spring Data JPA và PostgreSQL.

---

## II. GIỚI THIỆU CHUNG

### 2.1 Mở đầu
Trong hoạt động quản lý câu lạc bộ bóng đá, việc lưu trữ và xử lý thông tin cầu thủ, huấn luyện viên là nhu cầu cốt lõi. Một hệ thống backend theo mô hình RESTful giúp:
- Chuẩn hóa truy cập dữ liệu qua HTTP.
- Hỗ trợ các thao tác CRUD một cách thống nhất.
- Tạo nền tảng mở rộng cho các hệ thống quản lý thi đấu, thống kê, chuyển nhượng trong tương lai.

Trong phạm vi đề tài này, hệ thống backend được xây dựng và phân tích dựa trên mã nguồn SoccerRest, với 2 nhóm tài nguyên chính:
- Player (cầu thủ)
- Coach (huấn luyện viên)

Mục tiêu thực hiện:
- Thiết kế và triển khai API REST rõ ràng, nhất quán.
- Áp dụng phân lớp Controller - Service - Repository - Entity.
- Triển khai cơ chế kiểm tra dữ liệu đầu vào bằng luật XML.
- Cung cấp tài liệu API bằng OpenAPI/Swagger.
- Đóng gói và vận hành bằng Docker.

### 2.2 Các khái niệm và công nghệ nền tảng của hệ thống

#### 2.2.1 REST
REST (Representational State Transfer) là phong cách kiến trúc cho dịch vụ web, trong đó tài nguyên được định danh qua URI và thao tác chủ yếu bằng HTTP.

Các nguyên tắc chính thể hiện trong hệ thống:
- Stateless: mỗi request mang đủ ngữ cảnh xử lý.
- Uniform interface: truy cập tài nguyên nhất quán qua URI.
- Dùng các HTTP method chuẩn:
  - GET: đọc dữ liệu
  - POST: tạo dữ liệu
  - PUT: cập nhật dữ liệu
  - DELETE: xóa dữ liệu

Ví dụ endpoint trong dự án:
- GET /players
- GET /players/{id}
- POST /players
- PUT /players/{id}
- DELETE /players/{id}
- GET /coaches
- GET /coaches/{id}
- POST /coaches
- PUT /coaches/{id}
- DELETE /coaches/{id}

#### 2.2.2 REST API trong bài toán quản lý CLB bóng đá
REST API đóng vai trò cầu nối giữa client (web/mobile/admin tool) và hệ quản trị dữ liệu.

Trong SoccerRest:
- Tài nguyên Player quản lý thông tin cầu thủ (tên, tuổi, vị trí, số áo, chân thuận, giá trị thị trường, câu lạc bộ...).
- Tài nguyên Coach quản lý thông tin huấn luyện viên (tên, tuổi, quốc tịch, bằng cấp, số năm kinh nghiệm, sơ đồ ưa thích...).

Nhờ định nghĩa endpoint rõ ràng, hệ thống dễ mở rộng cho các module tiếp theo như đội hình thi đấu, trận đấu, lịch tập, hợp đồng.

#### 2.2.3 Công nghệ và công cụ sử dụng trong dự án
1. Spring Boot 4.0.4
- Nền tảng chính để xây dựng REST API.
- Cấu hình nhanh, dễ triển khai.

2. Spring Web MVC
- Hỗ trợ xây dựng controller và ánh xạ endpoint.

3. Spring Data JPA + Hibernate
- Mapping entity sang bảng dữ liệu quan hệ.
- Tối giản thao tác CRUD thông qua JpaRepository.

4. PostgreSQL
- CSDL quan hệ dùng để lưu trữ dữ liệu Player/Coach.

5. XML Validation Rules (custom)
- Luật nghiệp vụ được khai báo trong validation-rules.xml.
- Runtime parse XML bằng DOM và áp dụng validation trong service layer.

6. springdoc OpenAPI
- Sinh tài liệu API:
  - /v3/api-docs
  - /swagger-ui.html

7. Docker + Docker Compose
- Container hóa ứng dụng và database.
- Hỗ trợ chạy môi trường ổn định giữa các máy.

8. Gradle Kotlin DSL
- Quản lý build và dependency qua build.gradle.kts.

---

## III. THỰC HÀNH XÂY DỰNG DỊCH VỤ WEB

### 3.1 Cấu trúc cây thư mục tổng thể của dự án
Cấu trúc chính phục vụ backend:
- src/main/java/org/ptit/soccerrest
  - SoccerRestApplication.java
  - bootstrap/DataInitializer.java
  - config/GlobalExceptionHandler.java, OpenApiConfig.java
  - controller/PlayerController.java, CoachController.java
  - domain/model/BaseEntity.java, Player.java, Coach.java
  - domain/repository/PlayerRepository.java, CoachRepository.java
  - service/PlayerService.java, CoachService.java
  - util/XMLValidationHelper.java, ValidationException.java
- src/main/resources
  - application.properties
  - validation-rules.xml
  - enviroment/local.env
- docker-compose.yml, Dockerfile
- build.gradle.kts

TODO: Chèn hình cây thư mục thực tế nếu bản nộp cần screenshot.

### 3.2 Các bước triển khai thực hiện dự án

#### 3.2.1 Khởi tạo project và cấu hình dependency
Project dùng Java 21 và Spring Boot 4.0.4.

Các dependency quan trọng:
- spring-boot-starter-webmvc
- spring-boot-starter-data-jpa
- postgresql
- springdoc-openapi-starter-webmvc-ui
- lombok

Ý nghĩa:
- Có đủ stack cho REST + JPA + PostgreSQL + tài liệu API tự động.

#### 3.2.2 Xây dựng mô hình dữ liệu
Hệ thống có lớp cha BaseEntity với id tự tăng, sau đó 2 entity chính kế thừa:

1. Player (bảng players)
- name, age, nationality
- position, jerseyNumber
- clubName, preferredFoot
- marketValue
- active

2. Coach (bảng coaches)
- name, age, nationality
- licenseLevel, experienceYears
- clubName, preferredFormation
- active

Việc tách Player và Coach giúp mô hình bám sát nghiệp vụ quản lý nhân sự bóng đá ở mức nền tảng.

#### 3.2.3 Tạo tầng Repository
Hai repository kế thừa JpaRepository:
- PlayerRepository extends JpaRepository<Player, Long>
- CoachRepository extends JpaRepository<Coach, Long>

Nhờ đó có sẵn các thao tác:
- findAll, findById, save, delete

#### 3.2.4 Tạo tầng Service và áp dụng validation
Service layer gồm:
- PlayerService
- CoachService

Mỗi thao tác save/update đều gọi XMLValidationHelper để kiểm tra dữ liệu đầu vào theo file validation-rules.xml.

Các rule điển hình:
- Player:
  - age: 15..45
  - jerseyNumber: 1..99
  - preferredFoot: Left|Right|Both
  - marketValue >= 0
- Coach:
  - age: 25..80
  - licenseLevel thuộc tập cho phép
  - preferredFormation thuộc tập sơ đồ cho phép

Ưu điểm của cách làm này:
- Luật nghiệp vụ tách khỏi code service.
- Dễ bảo trì khi thay đổi điều kiện kiểm tra.

#### 3.2.5 Tạo tầng Controller và endpoint REST
Hai controller chính:
- PlayerController ánh xạ /players
- CoachController ánh xạ /coaches

Mỗi controller cung cấp đầy đủ CRUD cơ bản.

Bảng endpoint:

| Tài nguyên | Method | Endpoint | Mô tả |
|---|---|---|---|
| Player | GET | /players | Lấy toàn bộ cầu thủ |
| Player | GET | /players/{id} | Lấy cầu thủ theo id |
| Player | POST | /players | Tạo cầu thủ mới |
| Player | PUT | /players/{id} | Cập nhật cầu thủ |
| Player | DELETE | /players/{id} | Xóa cầu thủ |
| Coach | GET | /coaches | Lấy toàn bộ huấn luyện viên |
| Coach | GET | /coaches/{id} | Lấy huấn luyện viên theo id |
| Coach | POST | /coaches | Tạo huấn luyện viên mới |
| Coach | PUT | /coaches/{id} | Cập nhật huấn luyện viên |
| Coach | DELETE | /coaches/{id} | Xóa huấn luyện viên |

Lưu ý thiết kế:
- Cả hai controller đang mở CORS toàn bộ bằng @CrossOrigin("*").
- Chưa có phân quyền theo vai trò.

#### 3.2.6 Xử lý lỗi và tài liệu API
1. Xử lý lỗi
- ValidationException được bắt bởi GlobalExceptionHandler.
- Khi dữ liệu sai, API trả HTTP 400 với cấu trúc:
  - timestamp
  - status
  - message
  - errors (danh sách lỗi)

Ví dụ phản hồi lỗi:

```json
{
  "timestamp": "2026-04-01T10:00:00",
  "status": 400,
  "message": "Dữ liệu không hợp lệ",
  "errors": [
    "Tuổi cầu thủ phải ít nhất 15",
    "Số áo phải từ 1 trở lên"
  ]
}
```

2. Tài liệu API
- OpenApiConfig định nghĩa metadata API.
- Truy cập:
  - http://localhost:8080/swagger-ui.html
  - http://localhost:8080/v3/api-docs

#### 3.2.7 Chạy hệ thống và kiểm thử
Có 2 cách chạy chính:

1. Chạy local
- Yêu cầu PostgreSQL chạy sẵn.
- Lệnh: gradlew.bat bootRun

2. Chạy bằng Docker Compose
- Lệnh: docker compose up --build
- Service db dùng postgres:17-alpine
- Port map host 5433 -> container 5432
- App map cổng 8080

Ngoài ra hệ thống có DataInitializer để seed dữ liệu mẫu khi database đang trống.

TODO: Bổ sung ảnh chụp Swagger/Postman khi đóng gói bản nộp cuối.

---

## IV. ĐÁNH GIÁ KẾT QUẢ VÀ HƯỚNG MỞ RỘNG

### 4.1 Đánh giá kết quả

#### 4.1.1 Ưu điểm
- Kiến trúc rõ ràng theo lớp, dễ đọc và bảo trì.
- API CRUD cho 2 thực thể chính đã đầy đủ ở mức cơ bản.
- Validation nghiệp vụ tách ra file XML giúp dễ chỉnh luật.
- Có GlobalExceptionHandler cho phản hồi lỗi có cấu trúc.
- Có OpenAPI/Swagger hỗ trợ kiểm thử và tài liệu hóa.
- Có Dockerfile và docker-compose giúp triển khai nhanh.

#### 4.1.2 Hạn chế/vấn đề đã gặp và giải pháp đề xuất
1. Chưa có bảo mật API
- Hiện trạng: chưa có authentication/authorization.
- Đề xuất: tích hợp Spring Security + JWT.

2. Chưa tách DTO
- Hiện trạng: controller nhận/trả trực tiếp entity.
- Đề xuất: bổ sung DTO + mapper để tách domain với contract API.

3. Test còn tối thiểu
- Hiện trạng: chỉ có test contextLoads.
- Đề xuất: thêm unit test cho service validation và integration test cho controller.

4. CORS đang mở hoàn toàn
- Hiện trạng: @CrossOrigin("*").
- Đề xuất: giới hạn origin theo môi trường triển khai thực tế.

5. Chưa có logic nghiệp vụ nâng cao
- Hiện trạng: mới quản lý thông tin nền cho player/coach.
- Đề xuất: mở rộng module đội bóng, lịch thi đấu, thống kê, hợp đồng.

### 4.2 Hướng mở rộng
1. Mở rộng domain quản lý CLB
- Thêm Team, Match, TrainingSession, Contract, Transfer.

2. Bổ sung truy vấn nâng cao
- Phân trang, lọc theo câu lạc bộ/vị trí, sắp xếp theo marketValue.

3. Chuẩn hóa validation
- Kết hợp Bean Validation (jakarta.validation) với rule hiện tại.

4. Nâng cấp bảo mật
- JWT/OAuth2, RBAC theo vai trò admin/manager/coach.

5. Nâng chất lượng vận hành
- Thêm CI/CD, health check, logging tập trung, monitoring.

6. Tối ưu dữ liệu và hiệu năng
- Thiết kế index phù hợp.
- Caching cho endpoint đọc nhiều.

7. Hoàn thiện tài liệu
- API guideline.
- Sơ đồ kiến trúc và sơ đồ luồng xử lý.

---

## KẾT LUẬN
Đề tài đã xây dựng được một backend REST cho quản lý câu lạc bộ bóng đá trên nền Spring Boot, với hai nhóm tài nguyên trọng tâm là cầu thủ và huấn luyện viên. Hệ thống đã thể hiện các thành phần quan trọng của một dịch vụ web hiện đại:
- phân lớp rõ ràng,
- API CRUD hoạt động theo chuẩn REST,
- validation nghiệp vụ,
- xử lý lỗi tập trung,
- tài liệu API tự động,
- khả năng chạy bằng Docker.

Kết quả này phù hợp mục tiêu học thuật ở mức thực hành triển khai backend. Tuy nhiên, để tiệm cận môi trường thực tế, hệ thống cần tiếp tục nâng cấp bảo mật, kiểm thử, chuẩn hóa contract API và mở rộng domain nghiệp vụ.

---

## TÀI LIỆU THAM KHẢO
1. Spring Boot Reference Documentation.
2. Spring Data JPA Documentation.
3. springdoc-openapi Documentation.
4. PostgreSQL Documentation.
5. Docker Documentation.
6. RESTful API Design Best Practices.

---

## Phụ lục truy vết nguồn nội dung
- Mã nguồn backend: thư mục src/main/java, src/main/resources.
- Cấu hình build/deploy: build.gradle.kts, docker-compose.yml, Dockerfile, README.md.
- Cấu trúc mẫu tiểu luận: docs/sample-thesis/sample-thesis.pdf (chỉ sử dụng từ trang 4 trở đi, qua file reports/sample-thesis-page4plus.txt).
