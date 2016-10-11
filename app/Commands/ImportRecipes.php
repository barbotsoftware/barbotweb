<?php

use Illuminate\Console\Command;
use Symfony\Component\Console\Input\InputOption;
use Symfony\Component\Console\Input\InputArgument;

class ImportRecipes extends Command {

	/**
	 * The console command name.
	 *
	 * @var string
	 */
	protected $name = 'barbot:import';

	/**
	 * The console command description.
	 *
	 * @var string
	 */
	protected $description = 'Imports recipes from csv.';

	/**
	 * Create a new command instance.
	 *
	 * @return void
	 */
	public function __construct()
	{
		parent::__construct();
	}

	/**
	 * Execute the console command.
	 *
	 * @return mixed
	 */
	public function fire()
	{
		$fileName = $this->option("filename");
		$file = fopen($fileName, "r");
		$content = fgets($file);

		print("Clearing out old recipes...\n");

		\DB::table("recipes")->truncate();
		\DB::table("recipe_steps")->truncate();
		\DB::table("recipe_step_ingredient")->truncate();

		print("Adding recipes from csv...\n");

		while($content)
		{
			$contentArr = explode(",", $content);

			$recipe = \Recipe::create(array(
				"name"       => $contentArr[1],
				"image_url"  => 'http://192.168.1.41/barbot/public/img/recipe_images/' . strtolower(str_replace(" ", "_", $contentArr[1])) . '.png'
			));

			\RecipeStep::create(array(
				'recipe_id' => $recipe->id,
				'recipe_action_id' => 4,
				'step_number' => 1
			));

			$steps = 1;
			for($i = 3; $i < sizeof($contentArr); $i+=3)
			{
				if($contentArr[$i] != "")
				{
					$steps++;
					$recipeStep = \RecipeStep::create(array(
						'recipe_id' => $recipe->id,
						'recipe_action_id' => 1,
						'step_number' => $steps
					));

					$ingredient = \Ingredient::where("name", "LIKE", "%" . $contentArr[$i] . "%")->first();

					if($ingredient)
					{
						$recipeStep->ingredients()->attach($ingredient->id, array("amount" => $contentArr[$i + 1]));
					}
				}
			}

			\RecipeStep::create(array(
				'recipe_id' => $recipe->id,
				'recipe_action_id' => 5,
				'step_number' => $steps+1
			));

			print("Added: " . $recipe->name . "\n");

			$content = fgets($file);
		}

		fclose($file);
	}

	/**
	 * Get the console command options.
	 *
	 * @return array
	 */
	protected function getOptions()
	{
		return [
			[
				"filename",
				null,
				InputOption::VALUE_REQUIRED,
				"Relative filename.",
				null
			]
		];
	}

}
