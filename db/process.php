<?php
  $server = 'localhost';
  $username = 'root';
  $password = '';
  $db = 'testdb';

  $conn = mysqli_connect($server, $username, $password, $db) or die("unable to connect");

  $user = $_POST['username'];
  $pass = $_POST['password'];
  echo "user : $user , pass : $pass ";

  $sql = "INSERT INTO user(username,password)
  VALUES ($user,$pass)";

  if(mysqli_query($conn, $sql)){
    echo "inserted";
  }else {
    echo "not inserted";
  }
?>
