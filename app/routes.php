<?php

require_once(implode(DIRECTORY_SEPARATOR, array(dirname(__FILE__), 'routes', 'commands.php')));
require_once(implode(DIRECTORY_SEPARATOR, array(dirname(__FILE__), 'routes', 'public.php')));
require_once(implode(DIRECTORY_SEPARATOR, array(dirname(__FILE__), 'routes', 'resources.php')));
require_once(implode(DIRECTORY_SEPARATOR, array(dirname(__FILE__), 'routes', 'test.php')));
require_once(implode(DIRECTORY_SEPARATOR, array(dirname(__FILE__), 'routes', 'dashboards', 'admin.php')));


Route::get('/order', array(
   function() {
       return View::make('order');
   }
));

Route::get('/dashboard', array(
   function() {
       return View::make('dashboard');
   }
));

Route::get("/userinfo", array(
    function() {
        $drinkOrders = DrinkOrder::where("user_id", 2)->with("Recipe")->get();

        $totalDrinks = 0;
        $totalOunces = 0;
        $alcOunces = 0;
        for($i = 0; $i < count($drinkOrders); $i++) {
            $totalDrinks++;

            $drinkOrder = $drinkOrders[$i];

            $steps = $drinkOrder->recipe->recipeSteps()->with("Ingredients")->orderBy('step_number', 'asc')->get();

            foreach($steps as $step)
            {
                if($step->recipe_action_id == 1)
                {
                    $totalOunces += $step->ingredients[0]->pivot->amount;
                    if($step->ingredients[0]->abv > 0) {
                        $alcOunces += $step->ingredients[0]->pivot->amount / (100 / $step->ingredients[0]->abv);
                    }
                }
            }
        }

        return array(
            'totalDrinks' => $totalDrinks,
            'totalOunces' => $totalOunces,
            'alcOunces'   => $alcOunces
        );
    }
));