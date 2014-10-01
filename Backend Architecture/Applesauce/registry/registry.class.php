<?php

Class registry
{
	
	private $objects=array();
	
	
	public function __construct()
	{
	}
	
	public function createAndStoreObject($key,$object)
	{
	   require_once($object.'.class.php');
       $this->objects[$key]=new $object($this);
	}
    
	public function getObject($key)
	{
		return $this->objects[$key];
	}
	
}

?>