<?php

     //Connection
     $servername = "localhost";
     $username = "root";
     $password = "";
     $db = "poetrydb";
 
     $conn = new mysqli($servername, $username, $password, $db);
 
     if($conn -> connect_error){
         die("connection failde: ".$conn->connect_error);
     }

     // Request
     $p_id = $_POST['poetry_id'];

     // Decision
     $query = "DELETE FROM poetry WHERE id = $p_id";

     $result = $conn->query($query);

     if($result){
        $response = array( "status" => "1","message" => "Poetry DELETED Succesfully");
     }
     else{
        $response = array( "status" => "0","message" => "Poetry NOT Delete");
     }
     echo json_encode($response);
?>