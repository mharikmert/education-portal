<?php
  $server = "localhost";
  $username = "root";
  $password = "";
  $db = "fikirtepedb";
    $db = new mysqli($server, $username, $password, $db) or die('unable to connect');
  echo "connection succedded";
?>
