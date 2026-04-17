# Lab 01 - Kiểm định phần mềm

Chương trình Java phân loại học lực và các ca kiểm thử JUnit 5.

## Nội dung

- `src/main/java/vn/phenikaa/ktpm/GradeClassifier.java`
  Chứa phương thức `classify(double[] scores)` có **vòng lặp** (for-each duyệt mảng điểm)
  và **lệnh rẽ nhánh** (if/else if/else kiểm tra tính hợp lệ và xếp loại).

## Yêu cầu

- JDK 17+
- Maven 3.9+

## Chạy chương trình

```bash
mvn -q compile exec:java -Dexec.mainClass=vn.phenikaa.ktpm.GradeClassifier
```

Hoặc build jar rồi chạy:

```bash
mvn -q package
java -cp target/classes vn.phenikaa.ktpm.GradeClassifier
```

## Chạy toàn bộ test JUnit

```bash
mvn -q test
```

## Cấu trúc test

- `GradeClassifierStatementCoverageTest` – các ca bao phủ **tất cả câu lệnh** (statement coverage).
- `GradeClassifierPathCoverageTest` – các ca bao phủ **tất cả đường đi** độc lập (path coverage).

## Quy ước phân loại

| Trung bình | Xếp loại |
|---|---|
| `< 5.0` | `YEU` |
| `5.0 – < 6.5` | `TRUNG_BINH` |
| `6.5 – < 8.0` | `KHA` |
| `>= 8.0` | `GIOI` |
| Điểm `< 0` hoặc `> 10`, hoặc mảng `null`/rỗng | `INVALID` |
