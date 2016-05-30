<?php

class IODeviceType extends Eloquent 
{
	protected $table = 'barbot_io_device_types';

	protected $fillable = array('id', 'name');
}