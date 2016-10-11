<?php

class SocketMessage extends Eloquent
{
    protected $table = 'socket_messages';

    protected $fillable = array('id', 'barbot_id', 'message', 'received', 'uid');

    public static function boot()
    {
        parent::boot();

        SocketMessage::creating(function($message)
        {
            $message->uid = 'msg_' . bin2hex(openssl_random_pseudo_bytes(3));
        });
    }

    public function Barbot()
    {
        return $this->belongsTo('Barbot');
    }
}