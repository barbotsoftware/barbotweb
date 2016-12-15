<?php

/* Defines the web socket command list and argument requirements for validation */

return array(
	'order_drink' => array(
		'command' => '\Barbot\SocketCommand\Commands\OrderDrink',
		'args'    => array(
			'barbot_id' => 'string|required',
			'recipe_id' => 'string|required',
			'ice'       => 'integer|required',
			'garnish'   => 'integer|required'
		)
	),
	'get_recipes_for_barbot' => array(
		'command' => '\Barbot\SocketCommand\Commands\GetRecipesForBarbot',
		'args'    => array(
			'barbot_id' => 'string|required'
		)
	),
	'get_recipe_details' => array(
		'command' => '\Barbot\SocketCommand\Commands\GetRecipeDetails',
		'args'    => array(
			'recipe_id' => 'string|required'
		)
	),
	'get_ingredients_for_barbot' => array(
		'command' => '\Barbot\SocketCommand\Commands\GetIngredientsForBarbot',
		'args'    => array(
			'barbot_id' => 'required|string'
		)
	),
	'create_custom_drink' => array(
		'command' => '\Barbot\SocketCommand\Commands\CreateCustomDrink',
		'args'    => array(
			'recipe' => 'required'
		)
	),
	'pour_drink' => array(
		'command' => '\Barbot\SocketCommand\Commands\PourDrink',
		'args'    => array(
			'drink_order_id' => 'required|string'
		)
	),
	'update_status' => array(
		'command' => '\Barbot\SocketCommand\Commands\UpdateStatus',
		'args'    => array(
			'status' => 'required|string',
			'barbot_id' => 'required|string'
		)
	),
	'update_config' => array(
		'command' => '',
		'args'    => array()
	),
	'start_serving' => array(
		'command' => '',
		'args'    => array()
	),
	'stop_serving' => array(
		'command' => '',
		'args'    => array()
	),
	'run_clean_cycle' => array(
		'command' => '',
		'args'    => array()
	),
	'set_ingredient' => array(
		'command' => '',
		'args'    => array()
	)
);