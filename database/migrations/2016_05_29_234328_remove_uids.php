<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class RemoveUids extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::table('barbots', function($table){
			$table->dropColumn('uid');
		});

		Schema::table('ingredients', function($table){
			$table->dropColumn('uid');
		});

		Schema::table('recipes', function($table){
			$table->dropColumn('uid');
		});

		Schema::table('users', function($table){
			$table->dropColumn('uid');
		});

		Schema::table("drink_orders", function($table)
		{
			$table->dropColumn("uid");
		});
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::table('barbots', function($table){
			$table->string('uid')->after('name');
		});

		Schema::table('ingredients', function($table){
			$table->string('uid')->after('name');
		});

		Schema::table('recipes', function($table){
			$table->string('uid')->after('name');
		});

		Schema::table('users', function($table){
			$table->string('uid')->after('password');
		});

		Schema::table('drink_orders', function($table)
		{
			$table->string("uid")->after("id");
		});
	}

}
