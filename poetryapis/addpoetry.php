<?php

    $servername = "localhost";
    $username = "root";
    $password = "";
    $db = "poetrydb";

    $conn = new mysqli($servername, $username, $password, $db);
    // Connection
    if($conn -> connect_error){
        die("connection failde: ".$conn->connect_error);
    }
    // Request
    $POETRY = $_POST['poetry']; 
    $POET_NAME = $_POST['poet_name'];
    
    // Decision
    $query = "INSERT INTO poetry(poetry_data, poet_name) VALUES('$POETRY','$POET_NAME')";

    $result = $conn->query($query);

    // Checking API Response
    if($result == 1 ){
        $response = array("status" => "1","message"=> "Poetry Succesfully inserted");
    }
    else{
        $response = array("status" => "0","message"=> "Poetry NOT Succesfully inserted");
    }

    // SHOW Response
    echo($response);
?>