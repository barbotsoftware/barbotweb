<?php namespace Barbot\SocketCommand\Commands;

class UpdateContainers extends Command
{
    public function execute()
    {
        $containers = $this->args['containers'];
        $barbotId = $this->args['barbot_id'];

        $barbot = \Barbot::where('uid', $barbotId)->first();
        
        for($i = 0; $i < count($containers); $i++) {
            $container = \BarbotContainer::where("number", $containers[$i]['number'])->where("barbot_id", $barbot->id)->first();
            $ingredient = \Ingredient::where("uid", $containers[$i]["ingredient_id"])->first();
            $fluidLevel = $containers[$i]["fluid_level"] != "-1" ? $containers[$i]["fluid_level"] : $container->fluid_level;

            $container->update(array(
                 "ingredient_id" => $ingredient->id,
                 "fluid_level"   => intval($fluidLevel)
            ));
        }

        return array (
            'type' => 'response',
            'command' => 'update_containers',
            'data' => array (
                'result' => "success"
            )
        );
    }
}