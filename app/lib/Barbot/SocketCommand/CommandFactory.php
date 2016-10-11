<?php namespace Barbot\SocketCommand;

/**
 * Class CommandFactory
 * @package Barbot\SocketCommand
 * Creates instances of command classes given a command name and arguments
 */
class CommandFactory
{
	public static function create($name, $conn, $args)
	{
		$commands = include implode(DIRECTORY_SEPARATOR, array(app_path(), 'lib', 'Barbot', 'config', 'commands.php'));

		if(array_key_exists($name, $commands))
		{
			$class = new \ReflectionClass($commands[$name]['command']);

			return $class->newInstanceArgs(array($commands[$name]['args'], $conn, $args));
		}
		else
		{
			return false;
		}
	}
}