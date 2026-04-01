# SoccerRest API

REST API quản lý cầu thủ và huấn luyện viên bóng đá — Spring Boot 4 + PostgreSQL.

---

## 🐳 Chạy bằng Docker (khuyến nghị)

> Yêu cầu: [Docker Desktop](https://www.docker.com/products/docker-desktop/) đang chạy.

### Lần đầu hoặc sau khi sửa code → build lại

```bash
docker compose up --build
```

### Chỉ khởi động lại (không build lại)

```bash
docker compose up
```

### Dừng project

```bash
docker compose down
```

### Dừng + xóa sạch dữ liệu DB

```bash
docker compose down -v
```

---

## 💻 Chạy local (không cần Docker)

> Yêu cầu: Java 21, PostgreSQL đang chạy tại `localhost:5432`.

```bash
gradlew.bat bootRun
```

Hoặc nếu muốn chỉ chạy DB bằng Docker còn app chạy local:

```bash
docker compose up db
gradlew.bat bootRun
```

---

## 🌐 Endpoints

| URL | Mô tả |
|-----|-------|
| `http://localhost:8080/swagger-ui.html` | Swagger UI |
| `http://localhost:8080/v3/api-docs` | OpenAPI JSON |
| `http://localhost:8080/players` | API cầu thủ |
| `http://localhost:8080/coaches` | API huấn luyện viên |

---

## ⚙️ Biến môi trường mặc định

| Biến | Giá trị mặc định |
|------|-----------------|
| `JDBC_DATABASE_URL` | `jdbc:postgresql://localhost:5432/soccer_rest` |
| `JDBC_DATABASE_USERNAME` | `postgres` |
| `JDBC_DATABASE_PASSWORD` | `123456` |
| `SPRING_JPA_HIBERNATE_DDL_AUTO` | `update` |

