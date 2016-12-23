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

				\Event::fire("barbot.metrics.drinkorder", array(
					array(
					"drink_order" => $drinkOrder,
					"recipe"      => $recipe,
					"user"        => $user
				)));

				print("Created drink order for recipe " . $recipe->name . " and barbot " . $barbot->uid . "\n");

				\Event::fire('barbot.drinkordered', array(
						'drink_order' => array (
							'barbot_id'   => $barbot->uid,
							'user_id'     => $user->uid,
							'user_name'   => $user->name,
							'recipe_id'   => $recipe->uid,
							'recipe_name' => $recipe->name,
							'ice'         => $this->args['ice'],
							'garnish'     => $this->args['garnish'],
							'timestamp'   => $drinkOrder->created_at
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