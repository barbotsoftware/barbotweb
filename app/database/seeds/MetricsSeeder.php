<?php

class MetricsSeeder extends Seeder {

    public function run()
    {
        DB::table("metrics")->truncate();

        DB::table("metrics")->insert(array(
            "barbot_id" => 1,
            "ounces_poured" => 0,
            "users" => 0,
            "drinks_poured" => 0
        ));
    }

}