<?php

class RecipeStep extends Eloquent 
{
	protected $table = 'recipe_steps';

	protected $fillable = array('id', 'recipe_id', 'recipe_action_id', 'step_number');

	public function recipes()
	{
		return $this->belongsTo("Recipe");
	}

	public function recipeActions()
	{
		return $this->belongsTo("RecipeAction");
	}

	public function ingredients()
	{
		return $this->belongsToMany("Ingredient", "recipe_step_ingredient")->withPivot("amount");
	}
}