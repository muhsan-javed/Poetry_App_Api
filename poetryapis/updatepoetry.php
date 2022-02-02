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
    $POETRY = $_POST['poetry_data'];
    $ID = $_POST['id'];

    // De
    $query = "UPDATE poetry SET poetry_data = '$POETRY' WHERE id = '$ID'";

    $result = $conn->query($query);

    // Response
    if($result){
        $response = array( "status" => "1","message" => "Poetry Update Succesfully");
    }
    else{
        $response = array( "status" => "0","message" => "Poetry Not Update Succesfully");
    }

    echo json_encode($response);

?>