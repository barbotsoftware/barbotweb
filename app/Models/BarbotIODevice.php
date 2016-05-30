<?php

class BarbotIODevice extends Eloquent 
{
	protected $table = 'barbot_io_devices';

	protected $fillable = array('id', 'barbot_id', 'gpio_port', 'barbot_io_device_type_id');

	public function barbot()
	{
		return $this->belongsTo("Barbot");
	}

	public function pump()
	{
		return $this->hasOne("BarbotPump");
	}

	public function valve()
	{
		return $this->hasOne("BarbotValve");
	}
}