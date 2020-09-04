<?php
  $server = 'localhost';
  $username = 'root';
  $password = '';
  $db = 'testdb';

  $conn = mysqli_connect($server, $username, $password, $db) or die("unable to connect");

  $user_id = $_POST['user_id'];
  $user_password = $_POST['user_password'];
  echo "user : $user_id , pass : $user_password ";

  $sql = "INSERT INTO user(username,password)
  VALUES ('$user_id','$user_password')";

  if(mysqli_query($conn,$sql)){
    echo "inserted";
  }else {
    echo "not inserted";
  }
?>
