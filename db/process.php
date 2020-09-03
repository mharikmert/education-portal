<?php
$server = 'localhost';
$username = 'root';
$password = '';
$db = 'testdb';

$con = new mysqli($server, $username, $password, $db);
if(!$con){
  echo "connection failed! ";
}else echo "connection succedded ";

if(!mysqli_select_db($con, $db)){
  echo "database not selected";
}else echo "database -> $db ";

$id = $_POST["user_id"];
$password = $_POST["user_password"];
// echo "id : $id password: $password"; //it is working

$sql = "INSERT INTO user (username, password) VALUES ($id,$password)";

if($con->query($sql) == TRUE){
  echo "succesfully";
}else echo "ups not inserted";
// header("refresh:2; url = web_menu.html");
?>
