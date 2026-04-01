# BACKEND SYSTEM ANALYSIS

## 1. Mục tiêu phân tích
Phân tích hệ thống backend Java trong workspace SoccerRest để làm cơ sở viết tiểu luận chủ đề quản lý câu lạc bộ bóng đá.

## 2. Nguồn bằng chứng đã đọc
- README.md
- build.gradle.kts, settings.gradle.kts
- docker-compose.yml, Dockerfile
- src/main/resources/application.properties, src/main/resources/validation-rules.xml, src/main/resources/enviroment/local.env
- src/main/java/org/ptit/soccerrest/** (toàn bộ lớp Java hiện có)
- src/test/java/org/ptit/soccerrest/SoccerRestApplicationTests.java

## 3. Tổng quan kiến trúc backend
- Kiến trúc phân lớp cơ bản: Controller -> Service -> Repository -> Entity.
- Framework chính: Spring Boot 4.0.4, Spring MVC, Spring Data JPA, PostgreSQL, springdoc OpenAPI.
- Java toolchain: Java 21.
- CSDL: PostgreSQL, cấu hình theo biến môi trường với giá trị mặc định local.
- API hiện tại tập trung vào 2 tài nguyên nghiệp vụ:
  - Cầu thủ (Player)
  - Huấn luyện viên (Coach)

## 4. Cấu trúc mã nguồn và vai trò
- src/main/java/org/ptit/soccerrest/SoccerRestApplication.java
  - Entry point ứng dụng, có bước chuẩn hóa timezone từ alias Asia/Saigon sang Asia/Ho_Chi_Minh.
- src/main/java/org/ptit/soccerrest/controller
  - PlayerController, CoachController: cung cấp REST endpoint CRUD cơ bản.
- src/main/java/org/ptit/soccerrest/service
  - PlayerService, CoachService: xử lý nghiệp vụ chính và validation trước khi ghi dữ liệu.
- src/main/java/org/ptit/soccerrest/domain/model
  - BaseEntity, Player, Coach: mô hình JPA.
- src/main/java/org/ptit/soccerrest/domain/repository
  - PlayerRepository, CoachRepository: JpaRepository.
- src/main/java/org/ptit/soccerrest/util
  - XMLValidationHelper: đọc luật validation từ XML và áp rule runtime.
  - ValidationException: ngoại lệ nghiệp vụ validation.
- src/main/java/org/ptit/soccerrest/config
  - GlobalExceptionHandler: chuẩn hóa phản hồi lỗi validation.
  - OpenApiConfig: metadata OpenAPI.
- src/main/java/org/ptit/soccerrest/bootstrap/DataInitializer.java
  - Seed dữ liệu mẫu cho players/coaches khi bảng trống.

## 5. Mô hình dữ liệu nghiệp vụ
### 5.1 Player (bảng players)
Thuộc tính chính quan sát được trong entity:
- id (kế thừa từ BaseEntity)
- name
- age
- nationality
- position
- jerseyNumber
- clubName
- preferredFoot
- marketValue
- active

### 5.2 Coach (bảng coaches)
Thuộc tính chính quan sát được trong entity:
- id (kế thừa từ BaseEntity)
- name
- age
- nationality
- licenseLevel
- experienceYears
- clubName
- preferredFormation
- active

## 6. Validation nghiệp vụ
Hệ thống đang dùng luật validation khai báo ngoài mã Java tại file:
- src/main/resources/validation-rules.xml

Cách hoạt động:
- Service gọi XMLValidationHelper.validatePlayer hoặc validateCoach trước save/update.
- XMLValidationHelper nạp validation-rules.xml, parse DOM, áp dụng rule theo entity/field.
- Rule đã thấy: required, minLength, maxLength, min, max, allowedValues.

Ví dụ ràng buộc tiêu biểu:
- Player:
  - age từ 15 đến 45
  - jerseyNumber từ 1 đến 99
  - preferredFoot thuộc Left/Right/Both
  - marketValue >= 0
- Coach:
  - age từ 25 đến 80
  - licenseLevel thuộc A, B, C, PRO, UEFA Pro, FIFA Pro, UEFA Elite
  - preferredFormation thuộc một tập chiến thuật định sẵn

## 7. API hiện có
### 7.1 Player API (/players)
- GET /players: lấy danh sách cầu thủ
- GET /players/{id}: lấy cầu thủ theo id
- POST /players: tạo mới cầu thủ
- PUT /players/{id}: cập nhật cầu thủ
- DELETE /players/{id}: xóa cầu thủ

### 7.2 Coach API (/coaches)
- GET /coaches: lấy danh sách huấn luyện viên
- GET /coaches/{id}: lấy huấn luyện viên theo id
- POST /coaches: tạo mới huấn luyện viên
- PUT /coaches/{id}: cập nhật huấn luyện viên
- DELETE /coaches/{id}: xóa huấn luyện viên

Đặc điểm triển khai API:
- Controller trả trực tiếp entity, chưa dùng DTO.
- HTTP 404 được trả khi không tìm thấy bản ghi trong get/update/delete.
- CORS mở toàn bộ qua @CrossOrigin("*") tại cả PlayerController và CoachController.

## 8. Xử lý lỗi
GlobalExceptionHandler bắt ValidationException và trả body JSON gồm:
- timestamp
- status = 400
- message = "Dữ liệu không hợp lệ"
- errors = danh sách lỗi chi tiết theo rule

## 9. Dữ liệu khởi tạo và môi trường chạy
### 9.1 Seed dữ liệu
DataInitializer (@PostConstruct) tự thêm dữ liệu mẫu khi bảng rỗng:
- 3 coach mẫu
- 3 player mẫu

### 9.2 Cấu hình môi trường
application.properties sử dụng biến môi trường:
- JDBC_DATABASE_URL
- JDBC_DATABASE_USERNAME
- JDBC_DATABASE_PASSWORD
- JDBC_DATABASE_DRIVER
- SPRING_JPA_HIBERNATE_DDL_AUTO

### 9.3 Docker
- docker-compose.yml có 2 service:
  - db: postgres:17-alpine, map cổng host 5433 -> container 5432
  - app: build từ Dockerfile, map cổng 8080
- Dockerfile build jar bằng Gradle rồi chạy bằng JRE 21 alpine.

## 10. OpenAPI/Swagger
Có tích hợp springdoc:
- /swagger-ui.html
- /v3/api-docs
Metadata API được cấu hình trong OpenApiConfig.

## 11. Bảo mật, test, và giới hạn hiện tại
### 11.1 Bảo mật
- Không thấy cấu hình Spring Security.
- Chưa có authentication/authorization trong mã nguồn đã đọc.

### 11.2 DTO/Use case riêng
- Không thấy package DTO hoặc lớp DTO.
- Service thao tác trực tiếp entity.

### 11.3 Test
- Hiện có 1 test context load cơ bản, chưa có test nghiệp vụ CRUD/validation.

### 11.4 DDL/SQL script
- Thư mục src/main/resources/sql hiện trống.
- DDL chủ yếu do JPA auto schema theo cấu hình ddl-auto.

## 12. Kết luận phân tích backend
Hệ thống SoccerRest hiện là backend REST quản lý cầu thủ và huấn luyện viên ở mức CRUD + validation theo XML, có OpenAPI và đóng gói Docker. Nền tảng đủ để phát triển thành bài tiểu luận kỹ thuật chủ đề quản lý câu lạc bộ bóng đá.

## 13. TODO/Assumptions cần minh bạch khi viết tiểu luận
- TODO: Không có bằng chứng về phân quyền người dùng, nên phần bảo mật nâng cao chỉ có thể nêu định hướng.
- TODO: Không có UI frontend trong repo, phần giao diện phải mô tả ở mức giả định hoặc đề xuất.
- TODO: Không có benchmark tải, nên phần hiệu năng phải ghi là chưa đo đạc thực nghiệm.
