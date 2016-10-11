<?php

require_once(implode(DIRECTORY_SEPARATOR, array(dirname(__FILE__), 'routes', 'commands.php')));
require_once(implode(DIRECTORY_SEPARATOR, array(dirname(__FILE__), 'routes', 'public.php')));
require_once(implode(DIRECTORY_SEPARATOR, array(dirname(__FILE__), 'routes', 'resources.php')));
require_once(implode(DIRECTORY_SEPARATOR, array(dirname(__FILE__), 'routes', 'test.php')));
require_once(implode(DIRECTORY_SEPARATOR, array(dirname(__FILE__), 'routes', 'dashboards', 'admin.php')));


Route::get('/order', array(
   function() {
       return View::make('order');
   }
));

Route::get('/dashboard', array(
   function() {
       return View::make('dashboard');
   }
));