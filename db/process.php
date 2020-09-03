<?php
$server = 'localhost';
$username = 'root';
$password = '';
$db = 'fikirtepedb';

$con = mysqli_connect($server, $username, $password);

if(!$con){
  echo "connection failed! ";
}
if(!mysqli_select_db($con, $db)){
  echo "database not selected";
}

$id = $_POST['user_id'];
$password = $_POST['user_password'];
$sql = "INSERT INTO kayit_log(id, password) VALUES ('$id', '$password')";

if(!mysqli_query($con, $sql)){
  echo " not inserted";
}else echo "inserted";
?>
