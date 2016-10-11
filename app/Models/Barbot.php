<?php

class Barbot extends Eloquent 
{
	protected $table = 'barbots';

	protected $fillable = array('id', 'name', 'status');

	public static function boot()
    {
        parent::boot();

        Barbot::creating(function($barbot)
		{
		    $barbot->uid = 'barbot_' . bin2hex(openssl_random_pseudo_bytes(3));
		});
    }

	public function ioDevices()
	{
		return $this->hasMany('BarbotIODevice');
	}
}