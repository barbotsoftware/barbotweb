angular.module('barbot')
    .service('dataService', ['notifyService', DataService]);

function DataService(notifyService) {

    var Metrics = {};

    var Containers = [];
    var changingContainers = [];
    var ClearChangingContainers = false;
    var ContainersUpdating = false;

    var Orders = [];

    var BarbotStatus = "busy";

    function setContainers(containers) {
        Containers = containers;
    }

    function getContainers() {
        return Containers;
    }

    // lit coderonis
    function getContainersUpdating() {
        return ContainersUpdating;
    }

    function setContainersUpdating(peniscake) {
        ContainersUpdating = peniscake;
    }

    // end lit coderonis

    function setContainer(data) {
        var containerID = data.id;

        setDrinkGlow(data.id);

        if (ClearChangingContainers) {
            changingContainers = [];
            ClearChangingContainers = false;
        }

        changingContainers.push(containerID);

        var containerRow = (containerID - 1) / 4;

        var rowToChange;
        switch (true) {
            case (containerRow < 1):
                rowToChange = 0;
                break;
            case (containerRow < 2):
                rowToChange = 1;
                break;
            case (containerRow < 3):
                rowToChange = 2;
                break;
            case (containerRow < 4):
                rowToChange = 3;
                break;
        }

        var rowCell = (containerID - 1) % 4;
        Containers[rowToChange][rowCell] = data;
    }

    function setDrinkGlow(containerID) {
        var container = $('#fluidLevel_' + containerID);

        // give that shit some glow
        container.addClass('changing');

        setTimeout(function () {
            container.removeClass('changing');
        }, 5000);
    }

    function clearChangingContainers(clear) {
        ClearChangingContainers = clear;
    }

    function getChangingContainers() {
        return changingContainers;
    }

    function setMetrics(metrics) {
        Metrics = metrics;
    }

    function getMetrics() {
        return Metrics;
    }

    function addOrder(data) {
        console.log(data);
        var userName = data.user.email.split('@')[0];
        var time = data.drink_order.created_at.split(' ')[1];
        var timeSplit = time.split(':');
        var outputTime = timeSplit[0] + ':' + timeSplit[1];

        var order = {
            date: outputTime,
            recipe: data.recipe.name,
            user: userName
        }
        console.log(order);
        Orders.push(order);
        console.log(Orders);
    }

    function getOrders() {
        return Orders;
    }

    function setStatus(data) {
        BarbotStatus = data;
    }

    function getStatus() {
        return BarbotStatus;
    }

    return {
        setMetrics: setMetrics,
        getMetrics: getMetrics,
        setContainers: setContainers,
        setContainer: setContainer,
        getContainers: getContainers,
        getChangingContainers: getChangingContainers,
        clearChangingContainers: clearChangingContainers,
        addOrder: addOrder,
        getOrders: getOrders,
        getStatus: getStatus,
        setStatus: setStatus,
        getContainersUpdating: getContainersUpdating,
        setContainersUpdating: setContainersUpdating
    }
}