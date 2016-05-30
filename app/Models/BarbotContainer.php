<?php

class BarbotContainer extends Eloquent 
{
	protected $table = 'barbot_containers';

	protected $fillable = array('id', 'number', 'ingredient_id', 'size');

	public function ingredient()
	{
		return $this->belongsTo("Ingredient");
	}
}