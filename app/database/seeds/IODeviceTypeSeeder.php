<?php

class IODeviceTypeSeeder extends Seeder {

    public function run()
    {
    	DB::table("barbot_io_device_types")->truncate();
    	
        IODeviceType::create(array("name" => "pump"));
        IODeviceType::create(array("name" => "valve"));
    }

}