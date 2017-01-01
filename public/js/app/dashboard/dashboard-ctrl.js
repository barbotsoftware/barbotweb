angular.module('barbot')
    .controller('DashboardController', ['$scope', '$http', 'webSocket', 'notifyService', 'dataService', DashboardController]);

function DashboardController($scope, $http, webSocket, notifyService, dataService) {
    vm = this;
    
    var baseUrl = "http://barbotweb";
    // var baseUrl = ""; // for local testing
    
    console.log('Loaded Dashboard Controller!');
    $scope.test = "Peniscake";
    $scope.orderDrink = orderDrink;
    $scope.metrics = {};
    $scope.orders = [];
    $scope.containerLevels = [];

    $scope.barbotStatus = "busy";
    handleBarbotStatus();

    $scope.ingredientLevels = [
        [1, 2, 3, 4],
        [1, 2, 3, 4],
        [1, 2, 3, 4],
        [1, 2, 3, 4]
    ];

    notifyService.subscribe($scope, websocketUpdate);

    initialDataRequests();


    function websocketUpdate() {
        console.log('WEBSOCKET UPDATE!');
        var metrics = dataService.getMetrics();
        if (metrics.users) {
            $scope.metrics = metrics;
        }
        var orders = dataService.getOrders();
        if (orders.length > 0) {
            $scope.orders = orders;
            $('#noOrdersMessage').hide();
        } else {
            $('#noOrdersMessage').show();
        }

        $scope.barbotStatus = dataService.getStatus();
        handleBarbotStatus();
        $scope.$apply();

        processFluidLevels(dataService.getContainers());
        displayFluidLevels();

    }

    function handleBarbotStatus() {
        if ($scope.barbotStatus === 'ready') {
            $('#barbotStatus').css('color', 'green');
        } else {
            $('#barbotStatus').css('color', 'red');
        }
    }

    function initialDataRequests() {

        // Get Metric requests 
        // $http.get('http://192.168.1.41/barbot/public/metrics')
        $http.get(baseUrl + '/barbot/public/metrics')
            .then(function (response) {
                console.log(response);

                var responseData = response.data[0];
                console.log(responseData);

                $scope.metrics = responseData;
            });

        // Get Fluid container levels
        $http.get(baseUrl + '/barbot/public/container?barbot_id=1')
            .then(function (response) {
                if (response.status === 200) {
                    processFluidLevels(response.data);

                    setTimeout(function () {
                        displayFluidLevels();
                    }, 300);
                }
            });
    }

    function processFluidLevels(data) {
        var fluidGrid = [[], [], [], []];
        
        // set up fluid grid
        if (data.length === 16) {
            for (var i = 0; i < data.length; i++) {
                var currentCont = data[i];
                var containerRow = (i) / 4;

                switch (true) {
                    case (containerRow < 1):
                        fluidGrid[0].push(data[i]);
                        break;
                    case (containerRow < 2):
                        fluidGrid[1].push(data[i]);
                        break;
                    case (containerRow < 3):
                        fluidGrid[2].push(data[i]);
                        break;
                    case (containerRow < 4):
                        fluidGrid[3].push(data[i]);
                        break;
                }
            }
            console.log(fluidGrid);

            dataService.setContainers(fluidGrid);

            $scope.ingredientLevels = fluidGrid;
            displayFluidLevels();
        }
    }

    function displayFluidLevels() {
        var fluidGrid = $scope.ingredientLevels;

        for (var i = 0; i < fluidGrid.length; i++) {
            var row = fluidGrid[i]
            for (var j = 0; j < row.length; j++) {
                var currentFluidMax = row[j].size;
                var currentFluidLvl = row[j].fluid_level;

                var fluidPercentage = (currentFluidLvl / currentFluidMax) * 100;

                row[j].fluidPercentage = fluidPercentage;
                // $('#fluidLevel_'+row[j].id).height(fluidPercentage + '%');
                var containerNumber = row[j].id.toString();
                // var container = document.getElementById('fluidLevel_'+ containerNumber);
                // console.log(container);

                var container = $('#fluidLevel_' + containerNumber);
                if (container) {

                    container.height(fluidPercentage + '%');

                    switch (true) {
                        case (fluidPercentage > 90):
                            container.css('background', '#00800A'); // Dark Green
                            break;
                        case (fluidPercentage > 70):
                            container.css('background', '#00B50E'); // Lighter Green
                            break;
                        case (fluidPercentage > 60):
                            container.css('background', '#7AFF00'); // Yellow-green
                            break;
                        case (fluidPercentage > 50):
                            container.css('background', '#FBFF00'); // Yellow
                            break;
                        case (fluidPercentage > 30):
                            container.css('background', '#FFB100'); // Light Orange
                            break;
                        case (fluidPercentage > 20):
                            container.css('background', '#Fd7700'); // Orange
                            break;
                        case (fluidPercentage <= 20):
                            container.css('background', '#FF2300'); // Red

                    }
                }
            }
        }

        // var isContainersUpdating = dataService.getContainersUpdating(); 
        // if (isContainersUpdating) {

        //     // set Glow on changing containers
        //     var changingContainers = dataService.getChangingContainers();
        //     for (var i = 0; i < changingContainers.length; i++) {
        //         var container = $('#fluidLevel_' + changingContainers[i]);

        //         // give that shit some glow
        //         container.addClass('changing');

        //         setTimeout(function () {
        //             for (var i = 0; i < changingContainers.length; i++) {
        //                 var container = $('#fluidLevel_' + changingContainers[i]);
        //                 container.removeClass('changing');
        //             }
        //         }, 5000);
        //     }
        // }
    }

    function orderDrink() {
        webSocket.orderDrink($('#drinkSelector').val());
    }

    // $scope.orderList = [
    //     {
    //         drinkName: "Pina Colada",
    //         timeStamp: "4:20"
    //     },
    //     {
    //         drinkName: "Tequila Sunrise",
    //         timeStamp: "4:19"
    //     },
    //     {
    //         drinkName: "Adios Motha Fucka",
    //         timeStamp: "4:19"
    //     },
    //     {
    //         drinkName: "Coke & Rum",
    //         timeStamp: "4:17"
    //     },
    //     {
    //         drinkName: "Tequiqui on the rocks",
    //         timeStamp: "4:15"
    //     }

    // ]

    // $http.get('http://localhost/barbot/public/recipe')
    //     .then(function(response) {
    //         console.log(response);

    //         // $scope.recipes = response.data;
    //         $scope.recipes = [
    //             {
    //                 uid: '1',
    //                 name: 'Cuba Libre'
    //             },
    //             {
    //                 uid: '2',
    //                 name: 'Tequila Sunrise'
    //             },
    //             {
    //                 uid: '3',
    //                 name: 'Rum & Coke'
    //             },
    //             {
    //                 uid: '4',
    //                 name: 'Vampiro'
    //             },
    //             {
    //                 uid: '5',
    //                 name: 'AMF'
    //             },
    //             {
    //                 uid: '6',
    //                 name: 'Long Island Iced Tea'
    //             },
    //             ];
    //     });
}