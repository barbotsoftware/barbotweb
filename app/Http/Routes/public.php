<?php

Route::get('/', function()
{
    return View::make('hello');
});
Route::get('/order', function()
{
    return View::make('order');
});

Route::post("/login", function()
{
    $validator = Validator::make(
        Input::get(),
        array(
            'email'    => 'required|string',
            'password' => 'required|string'
        )
    );

    if($validator->passes())
    {
        if (Auth::attempt(array('email' => Input::get("email"), 'password' => Input::get("password"))))
        {
            return array(
                "result" => "success"
            );
        }
        else
        {
            return array(
                'result' => 'error'
            );
        }
    }
    else
    {
        return $validator->messages();
    }
});
