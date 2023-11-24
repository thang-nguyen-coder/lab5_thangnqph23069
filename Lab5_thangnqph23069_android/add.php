<?php
require_once('./db_config.php');
function addProduct()
{
    global $conn;
    //Nhận dữ liệu được gửi từ android studio
    $name = $_POST['name'];
    $price = $_POST['price'];
    $description = $_POST['description'];
    //insert dữ liệu nhận được vào csdl
    // Sử dụng prepared statement để tránh SQL injection
    $sql = "INSERT INTO products (name, price, description) VALUES (?, ?, ?)";
    $stmt = $conn->prepare($sql);

    if (!$stmt) {
        die("Prepare failed: " . $conn->error);
    }

    // Binds parameters và thực thi truy vấn
    $stmt->bind_param("sss", $name, $price, $description);
    if ($stmt->execute()) {
        echo "Thêm thành công";
    } else {
        echo "Thêm thất bại: " . $stmt->error;
    }
}
addProduct();

$conn->close();
