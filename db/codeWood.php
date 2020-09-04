<?php
$connection = new mysqli('localhost', 'root','');
$sql = "CREATE DATABASE testCreate";
if($connection->query($sql)){
  echo "database created succesfully.";
}else echo "database not created!";
$connection = new mysqli('localhost', 'root','', 'testCreate');
$table = "CREATE TABLE testTable(
  tckn INT(11) NOT NULL,
  password VARCHAR(20) NOT NULL,
  branch VARCHAR(20) NOT NULL
)";

if($connection->query($table)){
  echo " table test table is created successfully";
}else echo "error creating table : " . $connection->error;
$connection->close();
?>
