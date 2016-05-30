<?php

class BarbotPump extends Eloquent 
{
	protected $table = 'barbot_pumps';

	protected $fillable = array('id', 'barbot_io_device_id', 'barbot_container_id');

	public function ioDevice()
	{
		return $this->belongsTo("BarbotIODevice", 'barbot_io_device_id');
	}

	public function container()
	{
		return $this->belongsTo("BarbotContainer", 'barbot_container_id');
	}
}