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
    "data": {
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
	"data": {
		"barbot_id": "barbot_1db433",
        "recipe_id": "recipe_ee1236",
	"ice": 1,
	"garnish": 0
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
	"data": {
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
	"data": {
		"recipe_id": "recipe_ee1236"
	}
}
```
Response:
```
{
	"type": "response",
	"command": "get_recipe_details",
	"data": {
		"recipe": {
			"name": "The Sour",
			"id": "recipe_9aa19a",
			"img": "http:\/\/farm8.staticflickr.com\/7252\/7594170156_46bf574865_o.jpg",
			"ingredients": [{
				"ingredient_id": "ingredient_d300cc",
				"quantity": 3.43
			}, {
				"ingredient_id": "ingredient_f8fb11",
				"quantity": 0.86
			}, {
				"ingredient_id": "ingredient_8ec6eb",
				"quantity": 1.71
			}]
		}
	}
}
```
##### get_ingredients_for_barbot
Request:
```
{
	"type": "command",
	"command": "get_ingredients_for_barbot",
	"data": {
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
    "data": {
        "recipe": {
            "name": "peniscake",
            "ingredients": [{
                "ingredient_id": "ingredient_8ec6eb",
                "amount": 2
            }, {
                "ingredient_id": "ingredient_315ea6",
                "amount": 1
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
	"data": {
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
