<?php namespace Barbot\SocketCommand\Commands;

class UpdateStatus extends Command
{
    public function execute()
    {
        $barbot = \Barbot::where('uid', $this->args['barbot_id'])->first();

        if($barbot && $barbot->uid == $this->conn->getId())
        {
            if($barbot->status != $this->args['status'])
            {
                print("Barbot status: " . $this->args['status'] . "\n");
            }

            \Event::fire('barbot.status', array(
                    'status' => $this->args['status'],
                    'barbot_id' => $barbot->uid
                )
            );

            $barbot->update(array(
                'status' => $this->args['status']
            ));
        }
    }
}