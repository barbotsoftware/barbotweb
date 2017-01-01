angular.module('barbot')
    .service('webSocket', ['notifyService', 'dataService', WebSocketService]);
    
function WebSocketService(notifyService, dataService){
    // var webSocket = new WebSocket('ws://localhost:8080?id=user_878410');
    // var webSocket = new WebSocket('ws://localhost:8080?id=user_af3707');
    // var webSocket = new WebSocket('ws://192.168.1.41:8000?id=user_3328ce');
    var webSocket = new WebSocket('ws://barbotweb:8000?id=user_1e1e46');
    
    
    webSocket.onmessage = eventHandler;
    
    function eventHandler(message){
        console.log(message)
        
        var response = JSON.parse(message.data);
        console.log(response);
        
        switch(response.event){
            case 'barbot.metrics.general':
                console.log('metrics event');
                dataService.setMetrics(response.data);
                dataService.setContainersUpdating(false);
                break;
                
            case 'barbot.metrics.containerlevels':
                console.log('container levels event');
                // dataService.setContainers(response.data);
                dataService.setContainer(response.data);
                dataService.clearChangingContainers(false);
                dataService.setContainersUpdating(true);
                break;
                
            case 'barbot.metrics.drinkorder':
                console.log('drink order');
                dataService.clearChangingContainers(true);
                dataService.addOrder(response.data);
                break;
                
            case 'barbot.status':
                console.log('status event');
                dataService.setStatus(response.data);
                break;
        }
        
        notifyService.notify();
    }
    
    function orderDrink(order){
        var jsonObject = {
           'type': 'command',
           'command': 'order_drink',
           'args': {
               'barbot_id': 'barbot_42cfe7',
               'recipe_id': order
           }
        };
        
        webSocket.send(JSON.stringify(jsonObject));
    }
    
    return {
        orderDrink: orderDrink
    }
}