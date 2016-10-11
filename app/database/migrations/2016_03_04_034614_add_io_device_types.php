<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class AddIoDeviceTypes extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('barbot_io_device_types', function($table)
		{
			$table->engine = "InnoDB";
			$table->increments("id");
			$table->string("name");
			$table->timestamps();
			$table->softDeletes();
		});

		Schema::table('barbot_io_devices', function($table)
		{
			$table->integer("barbot_io_device_type_id")->after("gpio_port");
			$table->string("name")->after('barbot_io_device_type_id')->nullable();
		});
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('barbot_io_device_types');
		Schema::table('barbot_io_devices', function($table)
		{
			$table->dropColumn("barbot_io_device_type_id");
			$table->dropColumn("name");
		});
	}

}
