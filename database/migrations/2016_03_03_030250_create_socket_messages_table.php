<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateSocketMessagesTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('socket_messages', function($table)
		{
			$table->engine = "InnoDB";
			$table->increments("id");
			$table->integer("barbot_id");
			$table->binary("message");
			$table->boolean("received");
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
		Schema::drop("socket_messages");
	}

}
