<?php

class RecipeSeeder extends Seeder {

    public function run()
    {
    	DB::table("recipes")->truncate();
    	DB::table("recipe_steps")->truncate();
    	DB::table("recipe_step_ingredient")->truncate();
        DB::table("recipe_actions")->truncate();

        RecipeAction::create(array('name' => 'add'));
        RecipeAction::create(array('name' => 'mix'));
        RecipeAction::create(array('name' => 'stir'));
        RecipeAction::create(array('name' => 'add_ice'));
        RecipeAction::create(array('name' => 'pour'));

        // -- The Sour --

        $recipe = Recipe::create(array("name" => "The Sour"));

        $recipeStep1 = RecipeStep::create(array(
            'recipe_id' => $recipe->id,
            'recipe_action_id' => 4,
            'step_number' => 1
        ));

        $recipeStep2 = RecipeStep::create(array(
        	'recipe_id' => $recipe->id,
        	'recipe_action_id' => 1,
        	'step_number' => 2
        ));
        $recipeStep2->ingredients()->attach(5, array("amount" => 3.43));

        $recipeStep3 = RecipeStep::create(array(
        	'recipe_id' => $recipe->id,
        	'recipe_action_id' => 1,
        	'step_number' => 3
        ));
        $recipeStep3->ingredients()->attach(8, array("amount" => .86));

        $recipeStep4 = RecipeStep::create(array(
            'recipe_id' => $recipe->id,
            'recipe_action_id' => 1,
            'step_number' => 4
        ));
        $recipeStep4->ingredients()->attach(11, array("amount" => 1.71));

        // -- Tiger Juice --

        $recipe2 = Recipe::create(array("name" => "Tiger Juice"));

        $recipeStep5 = RecipeStep::create(array(
            'recipe_id' => $recipe2->id,
            'recipe_action_id' => 4,
            'step_number' => 1
        ));

        $recipeStep6 = RecipeStep::create(array(
            'recipe_id' => $recipe2->id,
            'recipe_action_id' => 1,
            'step_number' => 2
        ));
        $recipeStep6->ingredients()->attach(5, array("amount" => 3.00));

        $recipeStep7 = RecipeStep::create(array(
            'recipe_id' => $recipe2->id,
            'recipe_action_id' => 1,
            'step_number' => 3
        ));
        $recipeStep7->ingredients()->attach(12, array("amount" => 2.00));

        $recipeStep8 = RecipeStep::create(array(
            'recipe_id' => $recipe2->id,
            'recipe_action_id' => 1,
            'step_number' => 4
        ));
        $recipeStep8->ingredients()->attach(11, array("amount" => 0.50));


        // -- Tolstoy Tang --

        $recipe3 = Recipe::create(array("name" => "Tolstoy Tang"));

        $recipeStep9 = RecipeStep::create(array(
            'recipe_id' => $recipe3->id,
            'recipe_action_id' => 4,
            'step_number' => 1
        ));

        $recipeStep10 = RecipeStep::create(array(
            'recipe_id' => $recipe3->id,
            'recipe_action_id' => 1,
            'step_number' => 2
        ));
        $recipeStep10->ingredients()->attach(4, array("amount" => 3.79));

        $recipeStep11 = RecipeStep::create(array(
            'recipe_id' => $recipe3->id,
            'recipe_action_id' => 1,
            'step_number' => 3
        ));
        $recipeStep11->ingredients()->attach(11, array("amount" => 1.89));

        $recipeStep12 = RecipeStep::create(array(
            'recipe_id' => $recipe3->id,
            'recipe_action_id' => 1,
            'step_number' => 4
        ));
        $recipeStep12->ingredients()->attach(8, array("amount" => 0.32));

        // -- Rum Fizz --

        $recipe4 = Recipe::create(array("name" => "Rum Fizz"));

        $recipeStep13 = RecipeStep::create(array(
            'recipe_id' => $recipe4->id,
            'recipe_action_id' => 4,
            'step_number' => 1
        ));

        $recipeStep14 = RecipeStep::create(array(
            'recipe_id' => $recipe4->id,
            'recipe_action_id' => 1,
            'step_number' => 2
        ));
        $recipeStep14->ingredients()->attach(2, array("amount" => 1.50));

        $recipeStep15 = RecipeStep::create(array(
            'recipe_id' => $recipe4->id,
            'recipe_action_id' => 1,
            'step_number' => 3
        ));
        $recipeStep15->ingredients()->attach(8, array("amount" => 0.75));

        $recipeStep16 = RecipeStep::create(array(
            'recipe_id' => $recipe4->id,
            'recipe_action_id' => 1,
            'step_number' => 4
        ));
        $recipeStep16->ingredients()->attach(11, array("amount" => 0.75));

        $recipeStep17 = RecipeStep::create(array(
            'recipe_id' => $recipe4->id,
            'recipe_action_id' => 1,
            'step_number' => 5
        ));
        $recipeStep17->ingredients()->attach(7, array("amount" => 2.00));

        // -- Cherry Cipirinha  --

        $recipe5 = Recipe::create(array("name" => "Cherry Caipirinha"));

        $recipeStep18 = RecipeStep::create(array(
            'recipe_id' => $recipe5->id,
            'recipe_action_id' => 4,
            'step_number' => 1
        ));

        $recipeStep19 = RecipeStep::create(array(
            'recipe_id' => $recipe5->id,
            'recipe_action_id' => 1,
            'step_number' => 2
        ));
        $recipeStep19->ingredients()->attach(2, array("amount" => 3.53));

        $recipeStep20 = RecipeStep::create(array(
            'recipe_id' => $recipe5->id,
            'recipe_action_id' => 1,
            'step_number' => 3
        ));
        $recipeStep20->ingredients()->attach(8, array("amount" => 1.41));

        $recipeStep21 = RecipeStep::create(array(
            'recipe_id' => $recipe5->id,
            'recipe_action_id' => 1,
            'step_number' => 4
        ));
        $recipeStep21->ingredients()->attach(11, array("amount" => 1.06));

        // -- Ambassador  --

        $recipe6 = Recipe::create(array("name" => "Ambassador"));

        $recipeStep22 = RecipeStep::create(array(
            'recipe_id' => $recipe6->id,
            'recipe_action_id' => 4,
            'step_number' => 1
        ));

        $recipeStep23 = RecipeStep::create(array(
            'recipe_id' => $recipe6->id,
            'recipe_action_id' => 1,
            'step_number' => 2
        ));
        $recipeStep23->ingredients()->attach(3, array("amount" => 2.00));

        $recipeStep24 = RecipeStep::create(array(
            'recipe_id' => $recipe6->id,
            'recipe_action_id' => 1,
            'step_number' => 3
        ));
        $recipeStep24->ingredients()->attach(8, array("amount" => 1.00));

        $recipeStep25 = RecipeStep::create(array(
            'recipe_id' => $recipe6->id,
            'recipe_action_id' => 1,
            'step_number' => 4
        ));
        $recipeStep25->ingredients()->attach(12, array("amount" => 3.00));

        // -- Screwdriver  --

        $recipe7 = Recipe::create(array("name" => "Screwdriver"));

        $recipeStep26 = RecipeStep::create(array(
            'recipe_id' => $recipe7->id,
            'recipe_action_id' => 4,
            'step_number' => 1
        ));

        $recipeStep27 = RecipeStep::create(array(
            'recipe_id' => $recipe7->id,
            'recipe_action_id' => 1,
            'step_number' => 2
        ));
        $recipeStep27->ingredients()->attach(4, array("amount" => 2.00));

        $recipeStep28 = RecipeStep::create(array(
            'recipe_id' => $recipe7->id,
            'recipe_action_id' => 1,
            'step_number' => 3
        ));
        $recipeStep28->ingredients()->attach(12, array("amount" => 4.00));

        // -- Cubata  --

        $recipe8 = Recipe::create(array("name" => "Cubata"));

        $recipeStep29 = RecipeStep::create(array(
            'recipe_id' => $recipe8->id,
            'recipe_action_id' => 4,
            'step_number' => 1
        ));

        $recipeStep30 = RecipeStep::create(array(
            'recipe_id' => $recipe8->id,
            'recipe_action_id' => 1,
            'step_number' => 2
        ));
        $recipeStep30->ingredients()->attach(1, array("amount" => 3.18));

        $recipeStep31 = RecipeStep::create(array(
            'recipe_id' => $recipe8->id,
            'recipe_action_id' => 1,
            'step_number' => 3
        ));
        $recipeStep31->ingredients()->attach(10, array("amount" => 0.71));

        $recipeStep32 = RecipeStep::create(array(
            'recipe_id' => $recipe8->id,
            'recipe_action_id' => 1,
            'step_number' => 4
        ));
        $recipeStep32->ingredients()->attach(14, array("amount" => 2.12));

        // -- Crantini  --

        $recipe9 = Recipe::create(array("name" => "Crantini"));

        $recipeStep33 = RecipeStep::create(array(
            'recipe_id' => $recipe9->id,
            'recipe_action_id' => 4,
            'step_number' => 1
        ));

        $recipeStep34 = RecipeStep::create(array(
            'recipe_id' => $recipe9->id,
            'recipe_action_id' => 1,
            'step_number' => 2
        ));
        $recipeStep34->ingredients()->attach(4, array("amount" => 3.00));

        $recipeStep35 = RecipeStep::create(array(
            'recipe_id' => $recipe9->id,
            'recipe_action_id' => 1,
            'step_number' => 3
        ));
        $recipeStep35->ingredients()->attach(13, array("amount" => 3.00));

        // -- Orange Blossom  --

        $recipe10 = Recipe::create(array("name" => "Orange Blossom"));

        $recipeStep36 = RecipeStep::create(array(
            'recipe_id' => $recipe10->id,
            'recipe_action_id' => 4,
            'step_number' => 1
        ));

        $recipeStep37 = RecipeStep::create(array(
            'recipe_id' => $recipe10->id,
            'recipe_action_id' => 1,
            'step_number' => 2
        ));
        $recipeStep37->ingredients()->attach(1, array("amount" => 2.67));

        $recipeStep38 = RecipeStep::create(array(
            'recipe_id' => $recipe10->id,
            'recipe_action_id' => 1,
            'step_number' => 3
        ));
        $recipeStep38->ingredients()->attach(8, array("amount" => 0.67));

        $recipeStep39 = RecipeStep::create(array(
            'recipe_id' => $recipe10->id,
            'recipe_action_id' => 1,
            'step_number' => 4
        ));
        $recipeStep39->ingredients()->attach(12, array("amount" => 2.67));

         // -- Tequila Sunrisse  --

        $recipe11 = Recipe::create(array("name" => "Tequila Sunrise"));

        $recipeStep40 = RecipeStep::create(array(
            'recipe_id' => $recipe11->id,
            'recipe_action_id' => 4,
            'step_number' => 1
        ));

        $recipeStep41 = RecipeStep::create(array(
            'recipe_id' => $recipe11->id,
            'recipe_action_id' => 1,
            'step_number' => 2
        ));
        $recipeStep41->ingredients()->attach(1, array("amount" => 2.67));

        $recipeStep42 = RecipeStep::create(array(
            'recipe_id' => $recipe11->id,
            'recipe_action_id' => 1,
            'step_number' => 3
        ));
        $recipeStep42->ingredients()->attach(8, array("amount" => 0.67));

        $recipeStep43 = RecipeStep::create(array(
            'recipe_id' => $recipe11->id,
            'recipe_action_id' => 1,
            'step_number' => 4
        ));
        $recipeStep43->ingredients()->attach(12, array("amount" => 2.67));

        // -- Gin Rickey  --
        // -- Muddy Water  --
        // -- Leprechaun  --
        // -- Cuba Libre  --
        // -- Brass Monkey  --
        // -- Culto a La Vida  --
        // -- Mint Julep  --
        // -- Cape Codder  --
        // -- Red Rooster  --
        // -- Brazilian Julep  --
        // -- Lime Gin Fizz  --
        // -- Border Crossing  --
        // -- Southside  --
        // -- Cranberry Kick  --
        // -- Gin Tonic  --
        // -- Sun Crush  --
        // -- Gin Fizz  --
        // -- Daiquiri  --
        // -- Gin Sour  --
        // -- Viva Villa  --
        // -- Hole in One  --
        // -- Dirty Texas Tea  --
        // -- Cape Cod  --
        // -- Classic Cosmo  --
        // -- Derby Daiquiri  --
        // -- Gin and Sin  --
        // -- Judge Jr  --
        // -- Little Devil  --
        // -- Maiden's Blush  --
        // -- Tom Collins  --
        // -- Cheksea Sidecar  --
        // -- Hula-Hula  --
        // -- Kamikaze  --
        // -- Mararita  --
        // -- Tequila Sunrise  --
        // -- The Gilbert  --
        // -- Gimlet  --
        // -- Gin and Tonic  --
        // -- Pink Polar Bear  --
        // -- Snake Bite  --
        // -- Vodka Tonic  --
        // -- BarBot's Long Island Ice Tea  --
        // -- Alpine Lemonade  --

    }

}