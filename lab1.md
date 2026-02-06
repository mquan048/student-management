# 1️⃣ Thêm dữ liệu lớn (ít nhất 10 sinh viên)

## Câu lệnh SQL

```sql
INSERT INTO Student(id, name, age, email) VALUES
(101, 'Nguyen Van A', 20, 'a@gmail.com'),
(102, 'Tran Thi B', 21, 'b@gmail.com'),
(103, 'Le Van C', 19, 'c@gmail.com'),
(104, 'Pham Thi D', 22, 'd@gmail.com'),
(105, 'Hoang Van E', 20, 'e@gmail.com'),
(106, 'Vo Thi F', 21, 'f@gmail.com'),
(107, 'Dang Van G', 20, 'g@gmail.com'),
(108, 'Bui Thi H', 23, 'h@gmail.com'),
(109, 'Do Van I', 19, 'i@gmail.com'),
(110, 'Phan Thi K', 22, 'k@gmail.com');
```
## Kết quả 
Thêm dữ liệu thành công

# 2️⃣ Ràng buộc Primary Key

## Thử nghiệm
Cố tình insert một id đã tồn tại:

```sql
INSERT INTO Student(id, name, age, email)
VALUES (101, 'Duplicate Student', 20, 'dup@gmail.com');
```

## Thông báo lỗi

```
UNIQUE constraint failed: Student.id
```

## Giải thích

Primary Key có hai đặc tính quan trọng:

### 1. UNIQUE
- Mỗi id chỉ xuất hiện một lần
- Không cho phép trùng lặp
### 2. NOT NULL
- Không được để rỗng

### Tại sao Database chặn thao tác này?

Nếu cho phép trùng id:
- Không phân biệt được hai sinh viên
- Query trả về nhiều kết quả sai
- UPDATE/DELETE có thể tác động nhầm dữ liệu
- Mất tính toàn vẹn dữ liệu

# 3️⃣ Toàn vẹn dữ liệu (Constraints – NULL name)

## Thử nghiệm

```sql
INSERT INTO Student(id, name, age, email)
VALUES (111, NULL, 20, 'null@gmail.com');
```


### Do Không có NOT NULL
- Insert thành công
- name = NULL


# Ảnh hưởng khi đọc dữ liệu bằng Java

Ví dụ:

```java
String name = rs.getString("name");
System.out.println(name.length());
```

Nếu name = NULL:
- Gây NullPointerException
- Chương trình bị crash

## Các vấn đề có thể gặp
- Lỗi runtime
- Giao diện hiển thị "null"


---

# Giải pháp đề xuất
- Thêm NOT NULL trong Database

```sql
name VARCHAR(100) NOT NULL