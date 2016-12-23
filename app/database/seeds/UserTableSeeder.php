<?php

class UserTableSeeder extends Seeder {

    public function run()
    {
    	DB::table("users")->truncate();
    	
        User::create(array('email' => 'alexhorstmann@hotmail.com', 'name' => 'tchabz'));
        User::create(array('email' => 'tcote9194@gmail.com', 'name' => 'term'));
    }

}