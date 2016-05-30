<?php

class IngredientSeeder extends Seeder {

    public function run()
    {
    	DB::table("ingredients")->truncate();

        Ingredient::create(array('name' => 'Gin'));
        Ingredient::create(array('name' => 'White Rum'));
        Ingredient::create(array('name' => 'Tequila'));
        Ingredient::create(array('name' => 'Vodka'));
        Ingredient::create(array('name' => 'Whiskey'));
        Ingredient::create(array('name' => 'Triple Sec'));
        Ingredient::create(array('name' => 'Club Soda'));
        Ingredient::create(array('name' => 'Simple Syrup'));
        Ingredient::create(array('name' => 'Grenadine'));
        Ingredient::create(array('name' => 'Lime Juice'));
        Ingredient::create(array('name' => 'Lemon Juice'));
        Ingredient::create(array('name' => 'Orange Juice'));
        Ingredient::create(array('name' => 'Cranberry Juice'));
        Ingredient::create(array('name' => 'Coca-Cola'));
        Ingredient::create(array('name' => 'Tonic Water'));
        Ingredient::create(array('name' => 'Red Bull'));
    }

}