<?php
 if($_SERVER["REQUEST_METHOD"] == "POST"){
   $mysql_host = "localhost";
   $mysql_username = "root";
   $mysql_password = "";
   $mysql_database = "fikirtepedb";

   $u_id = filter_var($_POST["user_id"], FILTER_SANITIZE_INT);
   $u_password = filter_var($POST["user_password"], FILTER_SANITIZE_STRING);
   $u_branch = filter_var($POST["user_branch"], FILTER_SANITIZE_STRING);

   print "id -> ". $u_name " <br> password -> " . $u_password;

   $msyqli = new mysqli($mysql_host, $mysql_username,$mysql_password,$mysql_database);
   if($mysqli->contect_error){
     die('error : ('. $mysqli->connect_errno .')'.$mysqli->connect_error);
   }
   $statement = $mysqli->prepare("INSERT INTO kayit_log (user_id, user_password, user_branch) VALUES(?,?,?)");
   $statement = $mysqli->bind_param('sss', $u_id, $u_password, $u_branch);

   if($statement_>execute()){
     print "got id -> " . $u_id;
   }else{
     print $mysqli->error;
   }
}
?>
