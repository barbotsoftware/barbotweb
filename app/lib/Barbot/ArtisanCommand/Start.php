<?php
namespace Barbot\ArtisanCommand;
use Illuminate\Console\Command;
use Ratchet\ConnectionInterface;
use Ratchet\Http\HttpServer;
use Ratchet\Server\IoServer;
use Ratchet\WebSocket\WsServer;
use Symfony\Component\Console\Input\InputOption;
use Symfony\Component\Console\Input\InputArgument;


/**
 * Class Start
 * @package Barbot\ArtisanCommand
 * Command to be used through the Artisan CLI, starts the main websocket server and IO loop on a specified port
 * and starts a local zmq socket to interface with the main laravel app.
 */
class Start extends Command
{
    protected $name        = "barbot:start";
    protected $description = "Starts barbot connections.";
    protected $barbotController;

    public function __construct()
    {
        parent::__construct();
        $this->barbotController = new \Barbot\SocketController();
    }

    public function fire()
    {
        // Get port information from the command line
        $wsport = (integer) $this->option("wsport");
        if (!$wsport)
        {
            $wsport = 8000;
        }

        $loop = \React\EventLoop\Factory::create();

        $webSock = new \React\Socket\Server($loop);
        $webSock->listen($wsport, '0.0.0.0');
        $webServer = new \Ratchet\Server\IoServer(
            new \Ratchet\Http\HttpServer(
                new \Ratchet\Websocket\WsServer(
                    $this->barbotController
                )
            ),
            $webSock
        );

        print("Starting server on port " . $wsport . "\n");
        // Start everything
        $loop->run();
    }
    protected function getOptions()
    {
        return [
            [
                "wsport",
                null,
                InputOption::VALUE_REQUIRED,
                "Web socket port to listen on.",
                null
            ]
        ];
    }
}