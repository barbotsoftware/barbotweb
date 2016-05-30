<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class AddMetrics extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('metrics', function($table)
		{
			$table->engine = "InnoDB";
			$table->integer("barbot_id");
			$table->integer("ounces_poured");
			$table->integer("users");
			$table->integer("drinks_poured");
		});

		Schema::table("barbot_containers", function($table){
			$table->integer("fluid_level")->after("size");
		});
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop("metrics");

		Schema::table("barbot_containers", function($table){
			$table->dropColumn("fluid_level");
		});
	}

}
