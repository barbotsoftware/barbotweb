<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class AddBarbotConainerRelation extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::table('barbot_containers', function($table)
		{
			$table->integer("barbot_id")->after('id');
		});
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::table('barbot_containers', function($table)
		{
			$table->dropColumn("barbot_id");
		});
	}

}
