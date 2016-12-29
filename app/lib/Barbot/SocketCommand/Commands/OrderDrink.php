<?php namespace Barbot\SocketCommand\Commands;

class OrderDrink extends Command
{
	public function execute()
	{
		$barbot = \Barbot::where('uid', $this->args['barbot_id'])->first();

		if($barbot)
		{
			// Get recipe and recipe steps
			$recipe = \Recipe::where('uid', $this->args['recipe_id'])->first();

			if($recipe) {

				$user = \User::where("uid", $this->conn->getId())->first();
				
				// Create a drink order record
				$drinkOrder = \DrinkOrder::create(array(
					'barbot_id' => $barbot->id,
					'recipe_id' => $recipe->id,
					'user_id'   => $user->id,
					'ice'       => $this->args['ice'],
					'garnish'   => $this->args['garnish']
				));

				print("Created drink order for recipe " . $recipe->name . " and barbot " . $barbot->uid . "\n");

				// Build ingredient list for recipe
				$steps = $recipe->recipeSteps()->with("Ingredients")->orderBy('step_number', 'asc')->get();

				$arr = array();
				foreach($steps as $step)
				{
					if($step->recipe_action_id == 1)
					{
						$obj = array();

						$obj['name'] = $step->ingredients[0]->name;
						$obj['ingredient_id'] = $step->ingredients[0]->uid;
						$obj['quantity'] = $step->ingredients[0]->pivot->amount;

						$arr[] = $obj;
					}
				}

				\Event::fire('barbot.drink_ordered', array(
						'data' => array (
							'drink_order' => array (
								'id'          => $drinkOrder->uid,
								'barbot_id'   => $barbot->uid,
								'user_id'     => $user->uid,
								'user_name'   => $user->name,
								'recipe' => array(
									'name'        => $recipe->name,
									'recipe_id'   => $recipe->uid,
									'img'         => $recipe->image_url,
									'ingredients' => $arr
								),
								'ice'         => $this->args['ice'],
								'garnish'     => $this->args['garnish'],
								'timestamp'   => $drinkOrder->created_at->toDateTimeString()
							)
						)
					)
				);

				return array (
					'type' => 'response',
					'command' => 'order_drink',
					'data' => array (
						'drink_order_id' => $drinkOrder->uid
					)
				);
			}
			else
			{
				return "error.recipenotfound";
			}
		}
		else
		{
			return "error.barbotnotfound";
		}
	}
}