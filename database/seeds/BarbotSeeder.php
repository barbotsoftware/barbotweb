<?php

class BarbotSeeder extends Seeder {

    public function run()
    {
    	DB::table('barbots')->truncate();
    	DB::table('barbot_containers')->truncate();
    	DB::table('barbot_io_devices')->truncate();
    	DB::table('barbot_pumps')->truncate();
    	DB::table('barbot_valves')->truncate();

        $barbot = Barbot::create(array('name' => 'sloshnet'));

        BarbotContainer::create(array(
			'barbot_id' => $barbot->id,
        	'number' => 1,
        	'ingredient_id' => 1,
        	'size' => 1
        ));

        BarbotContainer::create(array(
			'barbot_id' => $barbot->id,
        	'number' => 2,
        	'ingredient_id' => 2,
        	'size' => 1
        ));

        BarbotContainer::create(array(
			'barbot_id' => $barbot->id,
            'number' => 3,
            'ingredient_id' => 3,
            'size' => 1
        ));

        BarbotContainer::create(array(
            'barbot_id' => $barbot->id,
            'number' => 4,
            'ingredient_id' => 4,
            'size' => 1
        ));

        BarbotContainer::create(array(
            'barbot_id' => $barbot->id,
            'number' => 5,
            'ingredient_id' => 5,
            'size' => 1
        ));

        BarbotContainer::create(array(
            'barbot_id' => $barbot->id,
            'number' => 6,
            'ingredient_id' => 6,
            'size' => 1
        ));

        BarbotContainer::create(array(
            'barbot_id' => $barbot->id,
            'number' => 7,
            'ingredient_id' => 7,
            'size' => 1
        ));

        BarbotContainer::create(array(
            'barbot_id' => $barbot->id,
            'number' => 8,
            'ingredient_id' => 8,
            'size' => 1
        ));

        BarbotContainer::create(array(
            'barbot_id' => $barbot->id,
            'number' => 9,
            'ingredient_id' => 9,
            'size' => 1
        ));

        BarbotContainer::create(array(
            'barbot_id' => $barbot->id,
            'number' => 10,
            'ingredient_id' => 10,
            'size' => 1
        ));

        BarbotContainer::create(array(
            'barbot_id' => $barbot->id,
            'number' => 11,
            'ingredient_id' => 11,
            'size' => 1
        ));

        BarbotContainer::create(array(
            'barbot_id' => $barbot->id,
            'number' => 12,
            'ingredient_id' => 12,
            'size' => 1
        ));

        BarbotContainer::create(array(
            'barbot_id' => $barbot->id,
            'number' => 13,
            'ingredient_id' => 13,
            'size' => 1
        ));

        BarbotContainer::create(array(
            'barbot_id' => $barbot->id,
            'number' => 14,
            'ingredient_id' => 14,
            'size' => 1
        ));

        BarbotContainer::create(array(
            'barbot_id' => $barbot->id,
            'number' => 15,
            'ingredient_id' => 15,
            'size' => 1
        ));

        BarbotContainer::create(array(
            'barbot_id' => $barbot->id,
            'number' => 16,
            'ingredient_id' => 16,
            'size' => 1
        ));

        BarbotIODevice::create(array(
        	'barbot_id' => 1,
        	'gpio_port' => 17,
        	'barbot_io_device_type_id' => 1,
        	'name' => 'pump1'
        ));

        BarbotIODevice::create(array(
        	'barbot_id' => 1,
        	'gpio_port' => 16,
        	'barbot_io_device_type_id' => 2,
        	'name' => 'valve1'
        ));

        BarbotIODevice::create(array(
        	'barbot_id' => 1,
        	'gpio_port' => 15,
        	'barbot_io_device_type_id' => 1,
        	'name' => 'pump2'
        ));

        BarbotIODevice::create(array(
        	'barbot_id' => 1,
        	'gpio_port' => 14,
        	'barbot_io_device_type_id' => 2,
        	'name' => 'valve2'
        ));

        BarbotIODevice::create(array(
        	'barbot_id' => 1,
        	'gpio_port' => 13,
        	'barbot_io_device_type_id' => 3,
        	'name' => 'mixermotor'
        ));

        BarbotIODevice::create(array(
            'barbot_id' => 1,
            'gpio_port' => 12,
            'barbot_io_device_type_id' => 2,
            'name' => 'mixervalve'
        ));

        BarbotIODevice::create(array(
            'barbot_id' => 1,
            'gpio_port' => 11,
            'barbot_io_device_type_id' => 1,
            'name' => 'pump3'
        ));

        BarbotIODevice::create(array(
        	'barbot_id' => 1,
        	'gpio_port' => 10,
        	'barbot_io_device_type_id' => 2,
        	'name' => 'valve3'
        ));

        BarbotPump::create(array(
        	'barbot_io_device_id' => 1,
        	'barbot_container_id' => 1
        ));
		
		BarbotValve::create(array(
        	'barbot_io_device_id' => 2,
        	'barbot_container_id' => 1
        ));

        BarbotPump::create(array(
        	'barbot_io_device_id' => 3,
        	'barbot_container_id' => 2
        ));

        BarbotValve::create(array(
        	'barbot_io_device_id' => 4,
        	'barbot_container_id' => 2
        ));

        BarbotPump::create(array(
            'barbot_io_device_id' => 7,
            'barbot_container_id' => 3
        ));

        BarbotValve::create(array(
            'barbot_io_device_id' => 8,
            'barbot_container_id' => 3
        ));
    }

}