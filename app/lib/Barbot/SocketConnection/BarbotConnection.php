<?php namespace Barbot\SocketConnection;

use Ratchet\ConnectionInterface;

class BarbotConnection extends SocketConnection
{
	public function __construct($socket, $uid)
	{
		$this->socket = $socket;
		
		$barbot = \Barbot::where('uid', $uid)->first();

		if($barbot)
		{
			$this->id = $barbot->uid;

			$barbot->update(array(
				'status' => 'connected'
			));
		}
	}

	public function closeConnection()
	{
		\Barbot::where('uid', $this->id)->first()->update(array(
			'status' => 'disconnected'
		));
	}
}