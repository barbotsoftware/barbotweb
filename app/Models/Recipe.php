<?php

class Recipe extends Eloquent 
{
	protected $table = 'recipes';

	protected $fillable = array('id', 'name', "image_url", "custom", "created_by");

	public static function boot()
    {
        parent::boot();

        Recipe::creating(function($recipe)
		{
		    $recipe->uid = 'recipe_' . bin2hex(openssl_random_pseudo_bytes(3));
		});
    }

    public function recipeSteps()
    {
    	return $this->hasMany("RecipeStep");
    }

	public function ingredients()
	{
		return $this->hasManyThrough("Ingredient", "RecipeStep");
	}
}