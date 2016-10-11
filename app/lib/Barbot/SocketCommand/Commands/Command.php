<?php namespace Barbot\SocketCommand\Commands;

/**
 * Class Command
 * @package Barbot\SocketCommand\Commands
 * Base class for socket commands.
 * Contains a validation function to validate command arguments.
 */
class Command
{
	/**
	 * @var
	 * Array of argument requirements
     */
	protected $reqArgs;

	/**
	 * @var array
	 * Array of arguements to be used in the execute method
     */
	protected $args;

	/**
	 * @var
	 * SocketConnection object from which the command request is originating
     */
	protected $conn;

	public function __construct($validateArgs, $conn, $args = [])
	{
		$this->reqArgs = $validateArgs;
		$this->conn = $conn;
		$this->args = $args;
	}

	/**
	 * @return bool
	 * Validates the supplied arguments against the arguement requirements
     */
	public function validate()
	{
		$validator = \Validator::make($this->args, $this->reqArgs);

		return $validator->passes();
	}
}