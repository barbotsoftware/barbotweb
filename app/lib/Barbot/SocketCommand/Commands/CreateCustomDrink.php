<?php namespace Barbot\SocketCommand\Commands;

class CreateCustomDrink extends Command
{
    public function execute()
    {
        $recipe = $this->args['recipe'];

        //TODO: make this a sql transaction

        $newRecipe = \Recipe::create(array(
            "name"      => $recipe["name"],
            "custom"    => 1,
            "image_url" => "http://192.168.1.41/barbot/public/img/recipe_images/custom.png"
        ));

        for($i = 0; $i < count($recipe["ingredients"]); $i++)
        {
            $recipeStep = \RecipeStep::create(array(
                'recipe_id'        => $newRecipe->id,
                'step_number'      => $i + 1,
                'recipe_action_id' => 1
            ));

            \DB::table("recipe_step_ingredient")->insert(array(
                'recipe_step_id' => $recipeStep->id,
                'ingredient_id'  => \Ingredient::where("uid", $recipe['ingredients'][$i]['ingredient_id'])->first()->id,
                'amount'         => $recipe['ingredients'][$i]['amount']
            ));
        }

        return array (
            'type' => 'response',
            'command' => 'order_drink',
            'data' => array (
                'recipe_id' => $newRecipe->uid
            )
        );
    }
}