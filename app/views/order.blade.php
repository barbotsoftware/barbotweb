@extends('templates.mainTemplate')

@section('meta')
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="google-site-verification" content="UU4MI4Y6ZM1MLlJsPQfUXvNQqepMqmszebYXBeqjjLA" />
    <title>Barbot | Order</title>
    <meta name='keywords' content='Barbot, turnup'>
    <meta name='distribution' content='Global'>
    <meta name='copyright' content='Barbot'>
@stop

@section('page_styles')
<link rel="stylesheet" href="{{ asset('css/bootstrap.min.css') }}">
<link rel="stylesheet" href="{{ asset('css/order.css') }}">
@stop

@section('scripts')
@stop

@section('content')
<divclass="container" ng-app="barbot" ng-controller="OrderController as orderCtrl">
    <!--<div class="row">-->
    <div id="pageWrapper" class="center-block">
        <div class="pageTitle text-center">
            Orders
        </div>
        <div id="pageContent" class="">
            Select a drink:
            <!--<div id="drinkSelectorCntnr">
                <select name="" id="drinkSelector">
                    <option ng-repeat="drink in drinkOptions"value="<%drink.uid%>"><%drink.name%></option>
                </select>
            </div>-->
            
            <div id="recipeList">
                <div class="recipeEntry" ng-repeat="recipe in recipes" id="(%recipe.uid%)"> (%recipe.name%) </div>
            </div>
            
            <div>
                <button ng-click="orderDrink()" id="drinkSelectButton">Go</button>
            </div>
        </div>
    </div>
    <!--</div>-->
</div>

@stop

@stop