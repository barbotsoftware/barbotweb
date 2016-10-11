<?php 

/* Defines common response messages */

return array(
	'success'              => array('result' => 'success'),
	'error.invalidjson'    => array('result' => 'error', 'message' => 'Invalid JSON string'),
	'error.unknowntype'    => array('result' => 'error', 'message' => 'Unknown message type'),
	'error.invalidargs'    => array('result' => 'error', 'message' => 'Invalid arguments'),
	'error.exception'      => array('result' => 'error', 'message' => 'Exception in execution of command'),
	'error.invalidcmd'     => array('result' => 'error', 'message' => 'Unknown command'),
	'error.disconnected'   => array('result' => 'error', 'message' => 'Barbot disconnected'),
	'error.barbotnotfound' => array('result' => 'error', 'message' => 'The barbot id given was not found.'),
	'error.recipenotfound' => array('result' => 'error', 'message' => 'The recipe id was not found.'),
	'error.ordernotfound'  => array('result' => 'error', 'message' => 'The order id was not found.'),
	'warning.subexists'    => array('result' => 'warning', 'message' => 'This connection is already subscribed to that barbot.')
);