<?php

class authenticate
{
	private $registry;
	private $user;
	private $loggedIn;
	private $id;
	
	public function __construct(Registry $registry)
	{
		$this->registry=$registry;
	}
	
	public function checkForAuthentication($ide)
	{
		
			require_once(FRAMEWORK_PATH.'registry/user.class.php');
		$this->user= new user($this->registry,$ide,'','');
		$this->loggedIn=$this->user->isLoggedIn();
		
		if($this->loggedIn==1)
			return true;
		else
			return false;
			
	   }
	   
	 
	
}