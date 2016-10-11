Barbot Web App
=====

### I. Using WebSocket Commands
---

### I. Setup

##### a. Start apache + mysql (xampp)
##### b. Start websocket server (default port is 8000):
`>php artisan barbot:start`
##### c. To specify port:
`>php artisan barbot:start --wsport=12345`

### Sending Commands
##### a. Open connection:
`ws://localhost:8000?id=user_73b7cd`
##### b. Send a command:
All commands must be valid JSON and have the same basic structure:
```
{
    "type": "command",
    "command": "[command]",
    "args": {
        ...
    }
}
```
##### c. Command List:
| Command          | Arguments     | Returns | 
| -------------    |-------------| -----  | 
|order_drink               | barbot_id, recipe_id | drink_oder_id   | 
|get_recipes_for_barbot    | barbot_id     |    recipes |
|get_recipe_details        | recipe_id     |recipe       |
|get_ingredients_for_barbot| barbot_id      |   ingredients    |
|create_custom_drink       | recipe     |   recipe_id    |
|pour_drink                | drink_order_id    |   success/error    |
|update_config| *not implemented*     |      |
|start_serving| *not implemented*      |       |
|stop_serving| *not implemented*      |       |
|set_ingredient| *not implemented*     |       |
|run_clean_cycle| *not implemented*      |       |

#### Example Payloads:
##### order_drink
Request:
```
{
	"type": "command",
	"command": "order_drink",
	"args": {
		"barbot_id": "barbot_1db433",
        "recipe_id": "recipe_ee1236"
	}
}
```
Response:
```
{
    "drink_order_id": "drinkorder_5e0f58"
}
```
##### get_recipes_for_barbot
Request:
```
{
	"type": "command",
	"command": "get_recipes_for_barbot",
	"args": {
		"barbot_id": "barbot_1db433"
	}
}
```
Response:
```
{
	"recipes": [{
		"name": "Cuba Libre",
		"id": "recipe_8a4d7a",
		"img": "http:\/\/192.168.1.41\/barbot\/public\/images\/term.jpg"
	}]
}
```
##### get_recipe_details
Request:
```
{
	"type": "command",
	"command": "get_recipe_details",
	"args": {
		"recipe_id": "recipe_ee1236"
	}
}
```
Response:
```
{
	"recipe": {
		"name": "Cuba Libre",
		"id": "recipe_8a4d7a",
		"img": "http:\/\/192.168.1.41\/barbot\/public\/images\/term.jpg",
		"steps": [{
			"step_number": 1,
			"type": 1,
			"ingredient_id": "ingredient_1b4549",
			"amount": 1.1
		}, {
			"step_number": 2,
			"type": 1,
			"ingredient_id": "ingredient_f8b566",
			"amount": 2.3
		}, {
			"step_number": 3,
			"type": 1,
			"ingredient_id": "ingredient_b87666",
			"amount": 1.5
		}, {
			"step_number": 4,
			"type": 2
		}, {
			"step_number": 5,
			"type": 5
		}]
	}
}
```
##### get_ingredients_for_barbot
Request:
```
{
	"type": "command",
	"command": "get_ingredients_for_barbot",
	"args": {
		"barbot_id": "barbot_1db433"
	}
}
```
Response:
```
{
	"ingredients": [{
		"name": "dark rum",
		"id": "ingredient_772b74"
	}, {
		"name": "cola",
		"id": "ingredient_65ae33"
	}, {
		"name": "vodka",
		"id": "ingredient_d62d25"
	}]
}
```
##### create_custom_drink
Request:
```
{
	"type": "command",
	"command": "create_custom_drink",
	"args": {
		"recipe": {
			"name": "peniscake",
			"steps": [{
				"type": 1,
				"ingredient_id": "ingredient_08459c",
				"amount": 2
			}, {
				"type": 1,
				"ingredient_id": "ingredient_d62d25",
				"amount": 1
			}, {
				"type": 4
			}, {
				"type": 5
			}]
		}
	}
}
```
Response:
```
{
	"recipe_id": "recipe_728aeb"
}
```
##### pour_drink
Request:
```
{
	"type": "command",
	"command": "pour_drink",
	"args": {
		"drink_order_id": "drinkorder_5e0f58"
	}
}
```
Response:
```
{
	"result": "success"
}
```
