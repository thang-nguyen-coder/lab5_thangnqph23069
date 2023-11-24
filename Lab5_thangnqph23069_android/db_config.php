<?php
$host = "localhost";
$username = "root";
$password = "";
$dbname = "lab5_massv";

// Create connection
$conn = new mysqli($host, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}
?>