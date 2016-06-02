<?php

Route::get("/testzmq", function()
{
    $context = new ZmqContext();
    $socket = $context->getSocket(ZMQ::SOCKET_PUSH, 'test_pusher');
    $socket->connect("tcp://localhost:5555");

    $socket->send(json_encode(array("bloop" => "asdf")));
});

Route::get("/peniscake", function(){
    $recipe = Recipe::create(array("name" => "Cuba Libre"));

        $recipeStep1 = RecipeStep::create(array(
        	'recipe_id' => 1,
        	'recipe_action_id' => 1,
        	'step_number' => 1
        ));
        $recipeStep1->ingredients()->attach(1, array("amount" => 5));

        $recipeStep2 = RecipeStep::create(array(
        	'recipe_id' => 1,
        	'recipe_action_id' => 1,
        	'step_number' => 2
        ));
        $recipeStep2->ingredients()->attach(2, array("amount" => 5));

        $recipeStep3 = RecipeStep::create(array(
            'recipe_id' => 1,
            'recipe_action_id' => 1,
            'step_number' => 2
        ));
        $recipeStep3->ingredients()->attach(3, array("amount" => 5));

        $recipeStep4 = RecipeStep::create(array(
            'recipe_id' => 1,
            'recipe_action_id' => 2,
            'step_number' => 3
        ));

        $recipeStep5 = RecipeStep::create(array(
            'recipe_id' => 1,
            'recipe_action_id' => 5,
            'step_number' => 3
        ));
});


Route::get("/random", function()
{
    $recipe = Recipe::create(array("name" => "Coke"));

    $recipeStep1 = RecipeStep::create(array(
        'recipe_id' => $recipe->id,
        'recipe_action_id' => 4,
        'step_number' => 1
    ));

    $recipeStep2 = RecipeStep::create(array(
        'recipe_id' => $recipe->id,
        'recipe_action_id' => 1,
        'step_number' => 2
    ));
    $recipeStep2->ingredients()->attach(14, array("amount" => 5.00));

    RecipeStep::create(array(
        'recipe_id' => $recipe->id,
        'recipe_action_id' => 5,
        'step_number' => 4
    ));
});
Route::get("/create", function()
{
    // recipe_action_ids: 1 : add, 4 : add_ice, 5 : pour

    $recipe = Recipe::create(array("name" => "Cuba Libre"));

    $recipeStep1 = RecipeStep::create(array(
        'recipe_id' => $recipe->id,
        'recipe_action_id' => 1,
        'step_number' => 1
    ));
    $recipeStep1->ingredients()->attach(1, array("amount" => 5));

    $recipeStep2 = RecipeStep::create(array(
        'recipe_id' => $recipe->id,
        'recipe_action_id' => 1,
        'step_number' => 2
    ));
    $recipeStep2->ingredients()->attach(2, array("amount" => 5));

    $recipeStep3 = RecipeStep::create(array(
        'recipe_id' => $recipe->id,
        'recipe_action_id' => 1,
        'step_number' => 3
    ));
    $recipeStep3->ingredients()->attach(3, array("amount" => 5));

    $recipeStep4 = RecipeStep::create(array(
        'recipe_id' => $recipe->id,
        'recipe_action_id' => 2,
        'step_number' => 4
    ));

    $recipeStep5 = RecipeStep::create(array(
        'recipe_id' => $recipe->id,
        'recipe_action_id' => 5,
        'step_number' => 5
    ));
});

Route::get("/test", function()
{
    print("<div>");
    foreach(Recipe::all() as $recipe)
    {
        print("<a href='{$recipe->image_url}'>{$recipe->name}</a><br/>");
    }
    print("</div>");
});