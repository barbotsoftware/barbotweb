angular.module('barbot')
    .controller('OrderController', ['$scope', '$http', 'webSocket', OrderController]);

function OrderController($scope, $http, webSocket) {
    vm = this;
    
    $scope.test = "Peniscake";
    $scope.orderDrink = orderDrink;
    
    function orderDrink(){
        webSocket.orderDrink($('#drinkSelector').val());
    }
    
    console.log("PenisCake");

    $http.get('http://localhost/barbot/public/recipe')
        .then(function(response) {
            console.log(response);
            
            // $scope.recipes = response.data;
            $scope.recipes = [
                {
                    uid: '1',
                    name: 'Cuba Libre'
                },
                {
                    uid: '2',
                    name: 'Tequila Sunrise'
                },
                {
                    uid: '3',
                    name: 'Rum & Coke'
                },
                {
                    uid: '4',
                    name: 'Vampiro'
                },
                {
                    uid: '5',
                    name: 'AMF'
                },
                {
                    uid: '6',
                    name: 'Long Island Iced Tea'
                },
                ];
        });
}

// $.get('http://localhost/barbot/public/recipe', function(response) {
//     console.log(response);
//     initDrinkSelector(response);
// });

function initDrinkSelector(data) {



    // $.each(data, function(i, drink) {
    //     $('#drinkSelector').append($('<option>', {
    //         value: drink.uid,
    //         text: drink.name
    //     }));
    // });

    // $('#drinkSelectButton').click(function(){
    //     console.log($('#drinkSelector').val());


    // });
}