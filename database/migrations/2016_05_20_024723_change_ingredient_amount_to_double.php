<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class ChangeIngredientAmountToDouble extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::table("recipe_step_ingredient", function($table)
		{
			$table->dropColumn("amount");
		});

		Schema::table("recipe_step_ingredient", function($table)
		{
			$table->double("amount", 4, 2);
		});
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::table("recipe_step_ingredient", function($table)
		{
			$table->dropColumn("amount");
		});

		Schema::table("recipe_step_ingredient", function($table)
		{
			$table->double("amount", 4, 2);
		});
	}

}
