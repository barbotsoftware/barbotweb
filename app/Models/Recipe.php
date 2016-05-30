<?php

class Recipe extends Eloquent 
{
	protected $table = 'recipes';

	protected $fillable = array('id', 'name');

    public function recipeSteps()
    {
    	return $this->hasMany("RecipeStep");
    }

	public function ingredients()
	{
		return $this->hasManyThrough("Ingredient", "RecipeStep");
	}
}