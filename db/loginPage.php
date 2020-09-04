<?php
 $conn = mysqli_connect('localhost', 'root','','fikirtepe') or die ("unable to connect");

 if($_SERVER['REQUEST_METHOD'] == 'POST'){
  $user_id = $_POST['user_id'];
  $user_password = $_POST['user_password'];
  $user_branch = $_POST['user_branch'];

$sql = "INSERT INTO kayit_log(tckn, password, branch)
VALUES('$user_id','$user_password','$user_branch')";
}
if($conn->query($sql)){
  echo "data saved to table";
}else echo "data couldn't save the table";
header("location: ../text/web_menu1.html");
 ?>
