<?php
require_once('./db_config.php');
// Hàm để lấy dữ liệu từ bảng products và hiển thị dưới dạng JSON
function getProducts()
{
    global $conn;

    // Thực hiện truy vấn SQL
    $sql = "SELECT * FROM products";
    $result = $conn->query($sql);

    // Kiểm tra nếu có kết quả
    if ($result->num_rows > 0) {
        // Duyệt qua từng dòng kết quả và lưu vào mảng
        $products = array();
        while ($row = $result->fetch_assoc()) {
            $products[] = $row;
        }

        // Hiển thị dữ liệu dưới dạng JSON
        echo json_encode($products);
    } else {
        // Hiển thị thông báo nếu không có kết quả
        echo json_encode(array('message' => 'No products found.'));
    }
    
}

// Gọi hàm để lấy và hiển thị dữ liệu
getProducts();

// Đóng kết nối
$conn->close();
?>