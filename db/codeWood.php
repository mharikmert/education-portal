<?php
$connection = new mysqli('localhost', 'root','');
$sql = "CREATE DATABASE testCreate";
if($connection->query($sql) == true){
  echo "database created succesfully.";
}else echo "database not created!";
$sql = "INSERT INTO "
 ?>
