<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class UpdateDrinkorderIceGarnish extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::table("drink_orders", function($table)
		{
			$table->integer("ice")->default(0);
			$table->integer("garnish")->default(0);
		});
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::table("drink_orders", function($table)
		{
			$table->dropColumn("ice");
			$table->dropColumn("garnish");
		});
	}

}
