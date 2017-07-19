BarBot Web Server
=====

### Using WebSocket Commands
---

### I. Setup

##### a. Start apache + mysql (xampp)
##### b. Start websocket server (default port is 8080):
`>mvn spring-boot:run`

### II. Sending Commands
##### a. Open connection:
`ws://localhost:8080/ws?username=panda&password=yeet`
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
|create_custom_recipe|recipe|recipe|
|get_containers_for_barbot|barbot_id|barbot_containers|
|get_ingredients_for_barbot|barbot_id|ingredients|
|get_recipe_details|recipe_id|recipe|
|get_recipes_for_barbot|barbot_id|recipes|
|order_drink|barbot_id, recipe_id, ice, garnish|drink_order_id|
|pour_drink|drink_order_id|success/error|
|set_containers_for_barbot|barbot_containers|success/error|

#### Example Payloads:

##### create_custom_recipe
Request:
```
{
    "type": "command_request",
    "command": "create_custom_recipe",
    "data": {
        "recipe": {
            "name": "Vodka Cran",
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
    "type": "command_response",
    "result": "success",
    "data": {
        "recipe_id": "recipe_728aeb"
    }
}
```
##### get_containers_for_barbot
Request:
```
{
	"type": "command_request",
	"command": "get_containers_for_barbot",
	"data": {
		"barbot_id": "barbot_1db433"
	}
}
```
Response:
```
{
	"type": "command_response",
    "result": "success",
    "data": {
        "barbot_containers": [{
            "number": 1,
            "ingredient_id": "ingredient_4f3h6d",
            "current_volume": 0,
            "max_volume": 48
        }, {
            "number": 2,
            "ingredient_id": "ingredient_4f3h6d",
            "current_volume": 0,
            "max_volume": 48
        }]
    }
}
```
##### get_ingredients_for_barbot
Request:
```
{
	"type": "command_request",
	"command": "get_ingredients_for_barbot",
	"data": {
		"barbot_id": "barbot_1db433"
	}
}
```
Response:
```
{
	"type": "command_response",
	"result": "success",
	"data": {
		"ingredients": [{
			"name": "Gin",
			"id": "ingredient_1b4549"
		}, {
			"name": "White Rum",
			"id": "ingredient_f8b566"
		}, {
			"name": "Tequila",
			"id": "ingredient_b87666"
		}]
	}
}
```
##### get_recipe_details
Request:
```
{
	"type": "command_request",
	"command": "get_recipe_details",
	"data": {
		"recipe_id": "recipe_ee1236"
	}
}
```
Response:
```
{
	"type": "command_response",
	"result": "success",
	"data": {
		"recipe": {
			"name": "The Sour",
			"recipe_id": "recipe_9aa19a",
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
##### get_recipes_for_barbot
Request:
```
{
	"type": "command_request",
	"command": "get_recipes_for_barbot",
	"data": {
		"barbot_id": "barbot_1db433"
	}
}
```
Response:
```
{
    "type": "command_response",
    "result": "success",
    "data": {
        "recipes": [{
            "name": "Cuba Libre",
        	"recipe_id": "recipe_8a4d7a",
        	"img": "http:\/\/192.168.1.41\/barbot\/public\/images\/term.jpg"
        }]
    }
}
```
##### order_drink
Request:
```
{
	"type": "command_request",
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
    "type": "command_response",
    "result": "success",
    "data": {
        "drink_order_id": "drinkOrder_5e0f58"
    }
}
```
##### pour_drink
Request:
```
{
	"type": "command_request",
	"command": "pour_drink",
	"data": {
	    "drink_order_id": "drinkOrder_5e0f58"
	}
}
```
Response:
```
{
    "type": "command_response",
    "result": "success"
}
```
##### set_containers_for_barbot
Request:
```
{
    "type": "command_request",
    "command":"set_containers_for_barbot",
    "data": {
        "barbot_id": "barbot_123456",
        "barbot_containers": [{
            "number": 1,
            "ingredient_id": "ingredient_4f3h6d",
            "current_volume": 0,
            "max_volume": 48
        }, {
            "number": 2,
            "ingredient_id": "ingredient_4f3h6d",
            "current_volume": 0,
            "max_volume": 48
        }]
    }
}
```
Response:
```
{
    "type": "command_response",
    "result": "success"
}
```
