<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateRecipeSteps extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('recipe_steps', function($table)
		{
			$table->engine = "InnoDB";
			$table->increments("id");
			$table->integer("recipe_id");
			$table->integer("recipe_action_id");
			$table->integer("step_number");
			$table->softDeletes();
			$table->timestamps();
		});

		Schema::create("recipe_actions", function($table){
			$table->engine = "InnoDB";
			$table->increments("id");
			$table->string("name");
			$table->softDeletes();
			$table->timestamps();
		});

		Schema::create("recipe_step_ingredient", function($table)
		{
			$table->engine = "InnoDB";
			$table->increments("id");
			$table->integer("recipe_step_id");
			$table->integer("ingredient_id");
			$table->integer("amount");
			$table->softDeletes();
			$table->timestamps();
		});

		Schema::drop("ingredient_recipe");
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop("recipe_steps");
		Schema::drop("recipe_actions");
		Schema::drop("recipe_step_ingredient");

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

}
