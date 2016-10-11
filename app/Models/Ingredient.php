<?php

class Ingredient extends Eloquent 
{
	protected $table = 'ingredients';

	protected $fillable = array('id', 'name', 'ingredient_type_id');

	public static function boot()
    {
        parent::boot();

        Ingredient::creating(function($ingredient)
		{
		    $ingredient->uid = 'ingredient_' . bin2hex(openssl_random_pseudo_bytes(3));
		});
    }
}