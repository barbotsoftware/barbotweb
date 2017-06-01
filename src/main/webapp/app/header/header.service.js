angular.module("barbot")
.factory("headerService", function() {

    function getAnus() {
        return "anus! (from header.service)";
    }

    return {
        getAnus
    }
});