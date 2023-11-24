<?php
    require_once('./db_config.php');
    function deleteProduct(){
        global $conn;
        require_once('./db_config.php');
        //nhận dữ liệu id từ android studio
        $id_to_delete = $_POST['pid'];
        //xóa dữ liệu từ bảng
        $sql = "DELETE FORM products WHERE id = $id_to_delete";
        if($conn->query($sql) === true){
            echo "Xóa thành công";
        }else{
            echo "Xóa thất bại" . $conn->error;
        }
    }
    deleteProduct();
    $conn->close();
?>