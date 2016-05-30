<?php

class RecipeAction extends Eloquent 
{
	protected $table = 'recipe_actions';

	protected $fillable = array('id', 'name');
}