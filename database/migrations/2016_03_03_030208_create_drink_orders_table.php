<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateDrinkOrdersTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('drink_orders', function($table)
		{
			$table->engine = "InnoDB";
			$table->increments("id");
			$table->integer("user_id");
			$table->integer("recipe_id");
			$table->integer("barbot_id");
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
		Schema::drop("drink_orders");
	}

}
