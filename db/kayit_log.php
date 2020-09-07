<?php
  $conn = mysqli_connect('localhost', 'root','','FikirtepeDB');
  if($conn){
    echo"database connected..";
  }else echo"database not connected";

  $user_id = $_POST['user_id'];
  $user_password= $_POST['user_password'];
  $user_branch = $_POST['user_branch'];

  $sql = "INSERT INTO kayit_log(tckn,password,branch)
  VALUES('$user_id', '$user_password','$user_branch')";

  if(mysqli_query($conn,$sql)) {
    echo "inserted";
  }else echo "not inserted";
 ?>
