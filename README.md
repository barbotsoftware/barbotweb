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
|get_containers_for_barbot|barbot_id|containers|
|get_categories|-|categories
|get_category|category_id|category
|get_ingredients_for_barbot|barbot_id|ingredients|
|get_recipe_details|recipe_id|recipe|
|get_recipes_for_barbot|barbot_id|recipes|
|order_drink|barbot_id, recipe_id, ice, garnish|drink_order_id|
|pour_drink|drink_order_id|success/error|
|set_containers_for_barbot|barbot_id, containers|success/error|
|update_container|barbot_id, container|success/error|

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
                "ingredient_id": "8ec6eb",
                "amount": 2
            }, {
                "ingredient_id": "315ea6",
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
        "recipe_id": "728aeb"
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
        "barbot_id": "1db433"
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
            "ingredient_id": "4f3h6d",
            "current_volume": 0,
            "max_volume": 48
        }, {
            "number": 2,
            "ingredient_id": "4f3h6d",
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
        "barbot_id": "1db433"
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
            "id": "1b4549"
        }, {
            "name": "White Rum",
            "id": "f8b566"
        }, {
            "name": "Tequila",
            "id": "b87666"
        }]
    }
}
```
##### get_categories
Request:
```
{
    "type": "command_request",
    "command": "get_categories",
    "data": {
    }
}
```
Response:
```
{
    "result": "success",
    "data": {
        "categories": [{
            "name": "Cocktail Color",
            "recipes": [],
            "category_id": "97F0F9",
            "sub_categories": [{
                "name": "Blue Cocktails",
                "recipes": [],
                "category_id": "BE95CB",
                "sub_categories": []
            }, {
                "name": "Orange Cocktails",
                "recipes": [],
                "category_id": "2F96E6",
                "sub_categories": []
            }]
        }, {
            "name": "Base Ingredient",
            "recipes": [],
            "category_id": "ED0167",
            "sub_categories": [{
                "name": "Rum",
                "recipes": [],
                "category_id": "35A6E3",
                "sub_categories": []
            }, {
                "name": "Tequila",
                "recipes": [],
                "category_id": "7275C6",
                "sub_categories": []
            }]
        }]
    },
  "type": "command_response",
  "command": "get_categories"
}
```
##### get_category
Request:
```
{
    "type": "command_request",
    "command": "get_category",
    "data": {
      "category_id": "A7F3A7"
    }
}
```
Response:
``` 
{
    "result": "success",
    "data": {
        "category": {
            "name": "Popular",
            "recipes": [],
            "category_id": "A7F3A7",
            "sub_categories": []
        }
    },
    "type": "command_response",
    "command": "get_category"
}
```
##### get_recipe_details
Request:
```
{
    "type": "command_request",
    "command": "get_recipe_details",
    "data": {
        "recipe_id": "ee1236"
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
            "recipe_id": "9aa19a",
            "img": "http:\/\/farm8.staticflickr.com\/7252\/7594170156_46bf574865_o.jpg",
            "ingredients": [{
                "ingredient_id": "d300cc",
                "amount": 3.43
            }, {
                "ingredient_id": "f8fb11",
                "amount": 0.86
            }, {
                "ingredient_id": "8ec6eb",
                "amount": 1.71
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
        "barbot_id": "1db433"
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
            "recipe_id": "8a4d7a",
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
        "barbot_id": "1db433",
        "recipe_id": "ee1236",
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
        "drink_order_id": "5e0f58"
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
        "drink_order_id": "5e0f58"
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
        "barbot_id": "123456",
        "barbot_containers": [{
            "number": 1,
            "ingredient_id": "4f3h6d",
            "current_volume": 0,
            "max_volume": 48
        }, {
            "number": 2,
            "ingredient_id": "4f3h6d",
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
    "data": {
    }
}
```
##### update_container
Request:
```
{
    "type": "command_request",
    "command":"update_container",
    "data": {
        "barbot_id": "123456",
        "container": {
            "number": 1,
            "ingredient_id": "4f3h6d",
            "current_volume": 24,
            "max_volume": 48
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
        "container": {
            "number": 1,
            "ingredient_id": "4f3h6d",
            "current_volume": 24,
            "max_volume": 48
        }
    }
}
```
