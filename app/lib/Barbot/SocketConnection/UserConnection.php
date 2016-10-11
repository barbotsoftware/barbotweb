<?php namespace Barbot\SocketConnection;

use Ratchet\ConnectionInterface;

class UserConnection extends SocketConnection
{
	public function __construct($socket, $uid)
	{
		$this->socket = $socket;
		
		$user = \User::where('uid', $uid)->first();

		if($user)
		{
			$this->id = $user->uid;

			\DB::table("metrics")->increment("users");

			\Event::fire("barbot.metrics.general", \DB::table("metrics")->get());
		}
	}

	public function closeConnection()
	{
		\DB::table("metrics")->decrement("users");
		
		\Event::fire("barbot.metrics.general", \DB::table("metrics")->get());
	}
}