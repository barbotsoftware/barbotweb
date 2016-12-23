<?php namespace Barbot;

use Exception;
use SplObjectStorage;
use \Barbot\SocketConnection;
use \Barbot\SocketCommand;
use Ratchet\ConnectionInterface;
use Ratchet\MessageComponentInterface;

/**
 * Class SocketController
 * @package Barbot
 * Handles all barbot/user websocket connections and messaging.
 */
class SocketController implements MessageComponentInterface
{
	/**
	 * @var SplObjectStorage
	 * Collection of open barbot/user connections
     */
	private static $connections;

	/**
	 * @var array
	 * Stores the users that are subscribed to barbot events
     */
	private static $barbotSubscriptions;

	/**
	 * SocketController constructor.
	 * Initializes the connection and subscription storage, and starts an general event listener for barbot events
     */
	public function __construct()
	{
		self::$connections = new SplObjectStorage();

		self::$barbotSubscriptions = [];

		\Event::listen("barbot.*", function($data)
		{
			$this->dispatchEvent($data, \Event::firing());
		});

		print("\nInitialized SocketController \n");
	}

	/**
	 * @return SplObjectStorage
     */
	public function getConnections()
	{
		return self::$connections;
	}

	/**
	 * @param ConnectionInterface $socket
	 * @return SocketConnection|null
	 * Returns the stored socket connection object (barbot/user connection) associated with the given socket object
     */
	public function getConnectionFromSocket(ConnectionInterface $socket)
	{
		foreach (self::$connections as $conn)
		{
			if($conn->getSocket() === $socket)
			{
				return $conn;
			}
		}

		return null;
	}

	/**
	 * @param $id
	 * @return null|SocketConnection\BarbotConnection
	 * Returns the barbot connection for the given barbot id, if one exists
     */
	public static function getBarbotConnection($id)
	{
		foreach (self::$connections as $conn)
		{
			if(get_class($conn) === "Barbot\SocketConnection\BarbotConnection")
			{
				if($conn->getId() === $id)
				{
					return $conn;
				}
			}
		}

		return null;
	}

	/**
	 * @param ConnectionInterface $socket
	 * Handles opening a socket connection based off a supplied user/barbot uid.
     */
	public function onOpen(ConnectionInterface $socket)
	{
		// Get query parameters
		$query = $socket->WebSocket->request->getQuery()->toArray();

		if(array_key_exists("id", $query)) 
		{
			if($this->openConnection($socket, $query["id"]))
			{
				print("Opened connection with {$query['id']}\n");
			}
		}
	}

	/**
	 * @param ConnectionInterface $socket
	 * @param string $message
	 * Handles an incoming websocket message (JSON) and sends a response.
     */
	public function onMessage(ConnectionInterface $socket, $message)
	{
		$cmd = json_decode($message, true);

		$conn = $this->getConnectionFromSocket($socket);

		if(is_null($cmd) || !is_array($cmd))
		{
			$conn->sendMessage('error.invalidjson');
		}
		else if(array_key_exists('type', $cmd))
		{
			$res = "error.unknowntype";

			switch($cmd["type"])
			{
				case "command":
					$res = $this->handleCommand($cmd, $conn);
					break;
				case "subscribe":
					$res = $this->subscribeToBarbot($conn, $cmd);
					break;
				case "unsubscribe":
					$res = $this->unsubscribeFromBarbot($conn, $cmd);
					break;
			}

			$conn->sendMessage($res);
		}
		else
		{
			$conn->sendMessage("error.unknowntype");
		}
	}

	/**
	 * @param ConnectionInterface $socket
	 * Handles closing a socket connection
     */
	public function onClose(ConnectionInterface $socket)
	{
		$conn = $this->getConnectionFromSocket($socket);

		if($conn) 
		{
			// Remove any subscriptions the connection might have
			foreach(self::$barbotSubscriptions as $sub)
			{
				$index = array_search($sub, $conn);

				if($index !== false)
				{
					array_splice($sub, $index, 1);
				}
			}

			$conn->closeConnection();

			// Detach the subscription from the SPL storage
			self::$connections->detach($conn);

			print("Closed connection.\n");
		}
	}

	/**
	 * @param ConnectionInterface $socket
	 * @param Exception $e
	 * Handles errors
     */
	public function onError(ConnectionInterface $socket, Exception $e)
	{
		print("Error occured.\n" . $e);

		$conn = $this->getConnectionFromSocket($socket);

		if($conn)
		{
			$conn->sendMessage('error.exception');
		}
	}

	/**
	 * @param $message
	 * Handles incoming ZMQ messages.
	 * ZMQ messages are handled the same way as websocket messages, only we don't send a response.
     */
	public function onZMQMessage($message)
	{
		$cmd = json_decode($message, true);

		if(is_null($cmd) || !is_array($cmd))
		{
			print('\nInvalid command');
		}
		else
		{
			print("\n" . $this->handleCommand($cmd));
		}
	}

	/**
	 * @param $cmd
	 * @param $conn
	 * @return string
	 * Handles websocket commands.
	 * Looks up the given command and executes it with the supplied arguments.
     */
	private function handleCommand($cmd, $conn = null)
	{
		// Make sure the command is valid
		if(array_key_exists('command', $cmd) && array_key_exists('data', $cmd))
		{
			try
			{
				// Attempt to instantiate the given command class
				$command = SocketCommand\CommandFactory::create($cmd['command'], $conn, $cmd['data']);

				if($command === false)
				{
					return 'error.invalidcmd';
				}
				else if(!$command->validate())
				{
					// Command did not pass validation, return error
					return 'error.invalidargs';
				}
				else
				{
					// Command was found and instantiated, execute it
					return $command->execute();
				}
			}
			catch (\ErrorException $e)
			{
				print '\n' . $e;

				return 'error.exception';
			}
		}
		else
		{
			return 'error.invalidargs';
		}
	}

	/**
	 * @param $socket
	 * @param $uid
	 * @return bool
	 * Opens a socket connection and stores it
     */
	private function openConnection($socket, $uid)
	{
		$type = explode("_", $uid, -1);

		if($type && count($type) > 0)
		{
			switch ($type[0]) 
			{
				case "barbot":
					$conn = new SocketConnection\BarbotConnection($socket, $uid);
					self::$connections->attach($conn);
					return true;
				case "user":
					$conn = new SocketConnection\UserConnection($socket, $uid);
					self::$connections->attach($conn);
					return true;
				default:
					return false;
			}
		}
	}

	/**
	 * @param $user
	 * @param $cmd
	 * @return string
	 * Subscribes the given user connection to a barbot's events
     */
	private function subscribeToBarbot($user, $cmd)
	{
		if(array_key_exists("barbot_id", $cmd))
		{
			// Only user connections should subscribe to barbots
			if(get_class($user) === "Barbot\SocketConnection\UserConnection")
			{
				// Make sure barbot exists
				$barbot = \Barbot::where("uid", $cmd["barbot_id"])->first();

				if($barbot)
				{
					// If the subscription doesn't already exist, add it.
					if(!array_key_exists($barbot->uid, self::$barbotSubscriptions)
						|| !in_array($user, self::$barbotSubscriptions[$barbot->uid]))
					{
						self::$barbotSubscriptions[$barbot->uid][] = $user;

						return "success";
					}
					else
					{
						return "warning.subexists";
					}
				}
				else
				{
					return "error.barbotnotfound";
				}
			}
		}
		else
		{
			return "error.invalidargs";
		}
	}

	/**
	 * @param $user
	 * @param $cmd
	 * @return string
	 * Unsubscribes the given user from a barbot's events
     */
	private function unsubscribeFromBarbot($user, $cmd)
	{
		if(array_key_exists("barbot_id", $cmd))
		{
			if(array_key_exists($cmd["barbot_id"], self::$barbotSubscriptions))
			{
				// If the subscription exists, delete it
				$index = array_search($user, self::$barbotSubscriptions[$cmd["barbot_id"]]);

				if($index !== false)
				{
					array_splice(self::$barbotSubscriptions[$cmd["barbot_id"]], $index, 1);
				}
			}

			return "success";
		}
		else
		{
			return "error.invalidargs";
		}
	}

	/**
	 * @param $data
	 * @param $event
	 * Handles sending events to users
     */
	private function dispatchEvent($data, $event)
	{
		foreach (self::$connections as $conn)
		{
			if(get_class($conn) === "Barbot\SocketConnection\BarbotConnection")
			{
				$conn->sendCommand(array(
					"event" => $event,
					"data" => $data
				));
			}
		}
	}
}