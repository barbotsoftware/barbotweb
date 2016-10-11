<?php namespace Barbot\SocketConnection;

/**
 * Class SocketConnection
 * @package Barbot\SocketConnection
 * Generalized wrapper for web socket conenctions
 */
class SocketConnection
{
	/**
	 * @var
	 * Ratchet ConnectionInterface object
     */
	protected $socket;
	/**
	 * @var
	 * The uid associated with the socket connection
     */
	protected $id;

	public function getSocket()
	{
		return $this->socket;
	}

	public function setSocket($socket)
	{
		$this->socket = $socket;
		return $this;
	}

	public function getId()
	{
		return $this->id;
	}

	public function setId($id)
	{
		$this->id = $id;
		return $this;
	}

	/**
	 * @param $message
	 * Sends a message over the socket conenction.
     */
	public function sendMessage($message)
	{
		if(!is_array($message))
		{
			$messageList = include implode(DIRECTORY_SEPARATOR, array(app_path(), 'lib', 'Barbot', 'config', 'messages.php'));;

			if (array_key_exists($message, $messageList))
			{
				$this->socket->send(json_encode($messageList[$message]));
			}
		}
		else
		{
			// Message is not a generic response message, send it as a JSON command instead
			$this->sendCommand($message);
		}
	}

	/**
	 * @param $command
	 * Sends a JSON encoded command
     */
	public function sendCommand($command)
	{
		if($command && is_array($command))
		{
			$this->socket->send(json_encode($command));
		}
	}
}