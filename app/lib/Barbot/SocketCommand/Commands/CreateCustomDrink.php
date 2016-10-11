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

        $count = 1;
        for($i = 0; $i < count($recipe["steps"]); $i++)
        {
            $recipeStep = \RecipeStep::create(array(
                'recipe_id'        => $newRecipe->id,
                'step_number'      => $i + 1,
                'recipe_action_id' => $recipe['steps'][$i]['type']
            ));

            if($recipe['steps'][$i]['type'] == '1')
            {
                \DB::table("recipe_step_ingredient")->insert(array(
                    'recipe_step_id' => $recipeStep->id,
                    'ingredient_id'  => \Ingredient::where("uid", $recipe['steps'][$i]['ingredient_id'])->first()->id,
                    'amount'         => $recipe['steps'][$i]['amount']
                ));
            }
            $count++;
        }

        \RecipeStep::create(array(
            'recipe_id' => $newRecipe->id,
            'step_number' => $count + 1,
            'recipe_action_id' => 5
        ));

        return array(
            'recipe_id' => $newRecipe->uid
        );
    }
}