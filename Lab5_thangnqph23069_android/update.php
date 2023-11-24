<?php
require_once('./db_config.php');
function updateProduct()
{
    global $conn;
    $pid = $_POST['pid'];
    $strName = $_POST['name'];
    $strPrice = $_POST['price'];
    $strDes = $_POST['description'];
    $sql = "UPDATE products SET name = '$strName', price = '$strPrice', description = '$strDes' WHERE id = $pid";

    if ($conn->query($sql) === TRUE) {
        echo "Sửa đổi dữ liệu thành công";
    } else {
        echo "Lỗi khi sửa đổi dữ liệu: " . $conn->error;
    }
}
updateProduct();
$conn->close();
?>