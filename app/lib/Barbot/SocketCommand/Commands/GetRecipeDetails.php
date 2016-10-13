<?php namespace Barbot\SocketCommand\Commands;

class GetRecipeDetails extends Command
{
    public function execute()
    {
        $recipeId = $this->args['recipe_id'];

        $recipe = \Recipe::where('uid', $recipeId)->first();

        if($recipe)
        {
            if($this->conn)
            {
                $steps = $recipe->recipeSteps()->with("Ingredients")->orderBy('step_number', 'asc')->get();

                $arr = array();
                foreach($steps as $step)
                {
                    $obj = array(
                        'step_number' => $step->step_number,
                        'type'        => $step->recipe_action_id
                    );

                    if($step->recipe_action_id == 1)
                    {
                        $obj['ingredient_id'] = $step->ingredients[0]->uid;
                        $obj['quantity'] = $step->ingredients[0]->pivot->amount;
                    }

                    $arr[] = $obj;
                }

                return array(
                    'type' => 'response',
                    'command' => 'get_recipe_details',
                    'data' => array(
                        'recipe' => array(
                            'name'  => $recipe->name,
                            'id'    => $recipe->uid,
                            'img'   => $recipe->image_url,
                            'steps' => $arr
                        )
                    )
                );
            }
        }
        else
        {
            return 'error.recipenotfound';
        }
    }
}