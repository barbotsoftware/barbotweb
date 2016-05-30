<?php

class DrinkOrder extends Eloquent
{
	protected $table = "drink_orders";

	protected $fillable = array('id', 'uid', 'barbot_id', 'recipe_id', 'user_id');

	public function recipe()
	{
		return $this->belongsTo("Recipe");
	}

	public function barbot()
	{
		return $this->belongsTo("Barbot");
	}
}