<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateDb extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('users', function($table)
		{
			$table->engine = "InnoDB";
			$table->increments("id");
			$table->string("email");
			$table->string("password");
			$table->timestamps();
			$table->softDeletes();
		});

		Schema::create('barbots', function($table)
		{
			$table->engine = "InnoDB";
			$table->increments("id");
			$table->string("name");
			$table->timestamps();
			$table->softDeletes();
		});

		Schema::create('ingredients', function($table)
		{
			$table->engine = "InnoDB";
			$table->increments("id");
			$table->string("name");
			$table->timestamps();
			$table->softDeletes();
		});

		Schema::create('recipes', function($table)
		{
			$table->engine = "InnoDB";
			$table->increments("id");
			$table->string("name");
			$table->timestamps();
			$table->softDeletes();
		});

		Schema::create('barbot_io_devices', function($table)
		{
			$table->engine = "InnoDB";
			$table->increments("id");
			$table->integer("barbot_id");
			$table->integer("gpio_port");
			$table->timestamps();
			$table->softDeletes();
		});

		Schema::create('barbot_containers', function($table)
		{
			$table->engine = "InnoDB";
			$table->increments("id");
			$table->integer("number");
			$table->integer("ingredient_id");
			$table->integer("size");
			$table->timestamps();
			$table->softDeletes();
		});

		Schema::create('barbot_pumps', function($table)
		{
			$table->engine = "InnoDB";
			$table->increments("id");
			$table->integer("barbot_io_device_id");
			$table->integer("barbot_container_id")->nullable();
			$table->timestamps();
			$table->softDeletes();
		});

		Schema::create('barbot_valves', function($table)
		{
			$table->engine = "InnoDB";
			$table->increments("id");
			$table->integer("barbot_io_device_id");
			$table->integer("barbot_container_id")->nullable();
			$table->timestamps();
			$table->softDeletes();
		});

		Schema::create('ingredient_recipe', function($table)
		{
			$table->engine = "InnoDB";
			$table->increments("id");
			$table->integer("recipe_id");
			$table->integer("ingredient_id");
			$table->integer("amount");
			$table->timestamps();
			$table->softDeletes();
		});
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('users');
		Schema::drop('barbots');
		Schema::drop('ingredients');
		Schema::drop('recipes');
		Schema::drop('barbot_io_devices');
		Schema::drop('barbot_containers');
		Schema::drop('barbot_pumps');
		Schema::drop('barbot_valves');
		Schema::drop('recipes_ingredients');
	}

}
