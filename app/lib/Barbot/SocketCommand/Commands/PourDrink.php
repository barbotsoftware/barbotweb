<?php namespace Barbot\SocketCommand\Commands;

class PourDrink extends Command
{
    public function execute()
    {
        $drinkOrderId = $this->args['drink_order_id'];

        $drinkOrder = \DrinkOrder::where("uid", $drinkOrderId)->first();

        if($drinkOrder)
        {
            // Get barbot connection
            $barbotConnection = \Barbot\SocketController::getBarbotConnection($drinkOrder->barbot->uid);

            if($barbotConnection)
            {
                $recipe = $drinkOrder->recipe;

                $recipeSteps = $recipe->recipeSteps()->with("Ingredients")->orderBy('step_number', 'asc')->get();

                $instructions = array();
                foreach ($recipeSteps as $step) {
                    $stepCommand = array(
                        'recipe_action' => \RecipeAction::find($step->recipe_action_id)->name,
                        'step_number' => $step->step_number
                    );

                    if (count($step->ingredients) > 0) {
                        $stepCommand['ingredient'] = $step->ingredients[0]->uid;
                        $stepCommand['amount'] = $step->ingredients[0]->pivot->amount;

                        $this->updateContainerLevels($drinkOrder->barbot_id, $step->ingredients[0]->id, $stepCommand['amount']);
                    }

                    $instructions[] = $stepCommand;
                }

                $command = array(
                    'command' => 'pour_drink',
                    'args' => array(
                        'steps' => $instructions
                    )
                );

                $barbotConnection->sendCommand($command);
                
                \DB::table("metrics")->where("barbot_id", $drinkOrder->barbot_id)->increment("drinks_poured");

                \Event::fire("barbot.metrics.general", \DB::table("metrics")->get());

                print("Sending pour command to " . $drinkOrder->barbot->uid . " for recipe " . $recipe->name . "\n");

                return 'success';
            }
            else
            {
                return 'error.disconnected';
            }
        }
        else
        {
            return 'error.ordernotfound';
        }
    }

    private function updateContainerLevels($barbot, $ingredient, $amount)
    {
        \DB::table("barbot_containers")->where("barbot_id", $barbot)->where("ingredient_id", $ingredient)->decrement("fluid_level", $amount);

        \DB::table("metrics")->increment("ounces_poured", $amount);

        \Event::fire("barbot.metrics.containerlevels", \BarbotContainer::where("barbot_id", 1)->where("ingredient_id", 1)->with("Ingredient")->first());
    }
}