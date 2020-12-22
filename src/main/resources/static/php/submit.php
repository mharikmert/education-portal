<?php
    $url = "https://localhost/users";
    $username = "root";
    $password = "";
    $user_id = $_POST['user_id'];
    $user_password = $_POST['user_password'];

    $data_json = array(
        'user_id' => $user_id,
        'user_password' => $user_password
    );

    $json = json_encode($data_json);
    $ch = curl_init();

    curl_setopt($ch, CURLOPT_POST,1);
    curl_setopt($ch, CURLOPT_USERPWD, $username . ":" . $password);
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('application/json'));
    //json objesi eklendi
    curl_setopt($ch, CURLOPT_POSTFIELDS, $json);
    //servis adreis eklendi
    curl_setopt($ch, CURLOPT_URL, $url); // set the url
    curl_setopt($ch, CURLOPT_POSTFIELDS, $json);

    $responseJson = curl_exec($ch);
    curl_close($ch);

    echo $responseJson;
?>