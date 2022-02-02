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
     
     $query = "SELECT * FROM poetry";

     $result = $conn->query($query);
	 
     // Decision
     $row = $result->fetch_all(MYSQLI_ASSOC);
	 
     // Response

    if(empty($row)){
        $response = array("status" => "0","message" => "Recovrd is empty","data" => $row);
    }else{
        $response = array("status" => "1","message" => "Recovrd Available","data" => $row);
    }
    echo json_encode($response);

?>