<?php
  $server = "localhost";
  $username = "root";
  $password = "";
  $db = "fikirtepedb";

    $conn = new mysqli($server, $username, $password, $db) or die('unable to connect');
    //$conn = mysqli_connect($server, $username, $password, $db);
    $conn-> close();
    //mysqli_close($conn);
  echo "connection succedded";
?>
