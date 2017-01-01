<?php namespace Barbot\SocketCommand\Commands;

class GetRecipesForBarbot extends Command
{
    public function execute()
    {
        $barbotId = $this->args['barbot_id'];

        $barbot = \Barbot::where('uid', $barbotId)->first();

        if($this->conn != null) {
            $user = \User::where("uid", $this->conn->getId())->first();
        }

        if($barbot)
        {
            if($this->conn)
            {
                $barbotIngredients = \DB::table('ingredients')
                    ->join('barbot_containers', 'ingredients.id', '=', 'barbot_containers.ingredient_id')
                    ->join('barbots', 'barbots.id', '=', 'barbot_containers.barbot_id')
                    ->where('barbots.id', $barbot->id)
                    ->select('ingredients.id')
                    ->lists('id');

                $allrecipes = \DB::table('recipes')
                    ->leftJoin('recipe_steps', 'recipe_steps.recipe_id', '=', 'recipes.id')
                    ->leftJoin('recipe_step_ingredient', 'recipe_step_ingredient.recipe_step_id', '=', 'recipe_steps.id')
                    ->leftJoin('ingredients', 'recipe_step_ingredient.ingredient_id', '=', 'ingredients.id')
                    ->selectRaw(
                        'recipes.uid as recipe_id,
                        recipes.name,
                        recipes.image_url,
                        GROUP_CONCAT(ingredients.id) as ingredients'
                    )
                    ->orderBy('recipes.id')
                    ->groupBy('recipes.id')
                    ->where("recipes.custom", 0);

                if($user != null) {
                    $allrecipes = $allrecipes->where("recipes.created_by", $user->id)->orWhere("recipes.created_by", 0);
                }

                $allrecipes = $allrecipes->get();

                $recipes = array();
                foreach($allrecipes as $recipe)
                {
                    $ingredients = explode(',', $recipe->ingredients);
                    if(count(array_intersect($ingredients, $barbotIngredients)) === count($ingredients))
                    {
                        $recipes[] = array(
                            'name'      => $recipe->name,
                            'recipe_id' => $recipe->recipe_id,
                            'img'       => $recipe->image_url
                        );
                    }
                }

                return array (
                    'type' => 'response',
                    'command' => 'get_recipes_for_barbot',
                    'data' => array (
                        'recipes' => $recipes
                    )
                );
            }
        }
        else
        {
            return 'error.barbotnotfound';
        }
    }
}