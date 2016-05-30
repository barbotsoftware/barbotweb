<?php

class Barbot extends Eloquent 
{
	protected $table = 'barbots';

	protected $fillable = array('id', 'name', 'status');

	public function ioDevices()
	{
		return $this->hasMany('BarbotIODevice');
	}
}