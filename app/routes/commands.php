<?php

Route::get("/command/{cmd}", function($cmd)
{
    $context = new ZmqContext();
    $socket = $context->getSocket(ZMQ::SOCKET_PUSH, 'pusher');
    $socket->connect("tcp://localhost:5555");

    $socket->send(json_encode(array(
        "command"   => $cmd,
        'args'      => array (
            "recipeid" => Input::get("recipe_id"),
            "barbotid" => Input::get("barbot_id")
        )
    )));
});