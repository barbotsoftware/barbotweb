<?php

class Ingredient extends Eloquent 
{
	protected $table = 'ingredients';

	protected $fillable = array('id', 'name', 'ingredient_type_id');
}