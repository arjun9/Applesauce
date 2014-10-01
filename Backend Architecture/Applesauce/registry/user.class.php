<?php

class user
{
	private $isLoggedIn;
	private $name;
	private $teacher_id;
	private $designation;
	private $uniqueid;
	private $registry;
	 public function __construct(Registry $registry,$id=0,$username='',$password='')
	 {
	 	$this->registry=$registry;
	 	
	 		$sql="SELECT * FROM faculty WHERE ID=$id";
	 		$result=$this->registry->getObject('db')->executeQuery($sql);
	 		$result=$this->registry->getObject('db')->getRows();
	 		$this->name=$result['name'];
	 		$this->teacher_id=$result['ID'];
	 		$this->designation=$result['designation'];
	 		$this->uniqueid=$result['uniqueid'];
	 		$this->isLoggedIn=$result['loggedin'];
	 		$this->teacher_id=$id;
	 	
	 }
	 
	 public function isLoggedIn()
	 {
	 	return $this->isLoggedIn;
	 }
	 
	 public function getName()
	 {
	 	return $this->name;
	 }
	 
	 public function getUniqueID()
	 {
	 	return $this->uniqueid;
	 }
	 
	 public function getDesignation()
	 {
	 	return $this->designation;
	 }
	 
	 public function getTeacherID()
	 {
	 	return $this->teacher_id;
	 }
}