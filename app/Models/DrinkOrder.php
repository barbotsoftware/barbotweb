<?php

class DrinkOrder extends Eloquent
{
	protected $table = "drink_orders";

	protected $fillable = array('id', 'uid', 'barbot_id', 'recipe_id', 'user_id', 'ice', 'garnish');

	public static function boot()
	{
		parent::boot();

		DrinkOrder::creating(function($drinkOrder)
		{
			$drinkOrder->uid = 'drinkorder_' . bin2hex(openssl_random_pseudo_bytes(3));
		});
	}

	public function recipe()
	{
		return $this->belongsTo("Recipe");
	}

	public function barbot()
	{
		return $this->belongsTo("Barbot");
	}

	public function getDates()
	{
		return array();
	}
}