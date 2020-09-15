<?php
  $conn = new mysqli('localhost', 'root','','FikirtepeDB') or die('unable to connect');

  /*$sql = "CREATE TABLE kayit_log(
      tckn BIGINT(11) NULL,
      sifre VARCHAR(20) NULL,
      sube VARCHAR(20) NULL
  )";
  if(mysqli_query($conn,$sql)) echo"connection succesfully";
  else echo "connection failed!";
*/
    $user_id = $_POST['user_id'];
    $user_password = $_POST['user_password'];
    $user_branch = $_POST['user_branch'];

    $sql = "INSERT INTO kayit_log
    VALUES('$user_id', '$user_password', '$user_branch')
    WHERE tckn != $user_id";

  if($conn->query($sql)) echo "data inserted";
    else echo "data not inserted";
 ?>
