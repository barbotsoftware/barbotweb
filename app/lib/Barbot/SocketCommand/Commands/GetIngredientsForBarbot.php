<?php namespace Barbot\SocketCommand\Commands;

class GetIngredientsForBarbot extends Command
{
    public function execute()
    {
        $barbotId = $this->args["barbot_id"];

        $barbot = \Barbot::where('uid', $barbotId)->first();

        if($barbot)
        {
            $ingredients = \DB::table("ingredients")
                ->join("barbot_containers", "barbot_containers.ingredient_id", "=", "ingredients.id")
                ->join("barbots", "barbots.id", "=", "barbot_containers.barbot_id")
                ->where("barbots.id", $barbot->id)
                ->select("ingredients.*")
                ->get();

            $arr = array();
            foreach($ingredients as $ingredient)
            {
                $arr[] = array(
                    'name'          => $ingredient->name,
                    'ingredient_id' => $ingredient->uid
                );
            }

            return array(
                'type' => 'response',
                'command' => 'get_ingredients_for_barbot',
                'data' => array(
                    'ingredients' => $arr
                )
            );
        }
        else
        {
            return 'error.barbotnotfound';
        }
    }
}