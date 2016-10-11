<?php

class DatabaseSeeder extends Seeder {

	/**
	 * Run the database seeds.
	 *
	 * @return void
	 */
	public function run()
	{
		Eloquent::unguard();

		$this->call('UserTableSeeder');
		$this->call('IODeviceTypeSeeder');
		$this->call('IngredientSeeder');
		$this->call('RecipeSeeder');
		$this->call('BarbotSeeder');
		$this->call("MetricsSeeder");
	}

}
