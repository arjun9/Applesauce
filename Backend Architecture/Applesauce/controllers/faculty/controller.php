<?php

class facultycontroller
{
	private $registry;
	private $authetication=false;
	public $response=array();
	private $id=0;
	private $pid=0;
	
	public function __construct(Registry $registry)
	{
		$this->registry=$registry;
		$urlBits=$this->registry->getObject('url')->getURLBits();
		if(isset($urlBits[2]))
		{
			switch ($urlBits[1])
			{
				case 'message':
					$this->readMessage($urlBits[2]);
					break;
				case 'edit':
					$this->editProfile($urlBits[2]);
					break;
				case 'setnotice':
					$this->setNotice($urlBits[2]);
					break;
				case 'setavailability':
					$this->setAvailability($urlBits[2]);
					break;	
				case 'profile':
					$this->showProfile($urlBits[2]);
					break;		
			}
		}
		else
		{
		$response['message']="Unable to retrieve data";
		$response['success']=0;	
		
		echo json_encode($response);
		}
	}
	
	public function readMessage($id)
	{
		//this facility has yet to be included 
	}
	
	public function showProfile($id)
	{
	  $this->authetication=$this->registry->getObject('authenticate')->checkForAuthentication($id);
		
		if($this->authetication==false)
		{
			$this->failedAttempt();
		}
		else
		{
			$result =$this->registry->getObject('db')->getData(1,3,'teacherid',$id);
			if($result==1)
			{
				$details=array();
				$response['data']=array();
				while($result=$this->registry->getObject('db')->getRows())
				{
					$this->pid=$result['ID'];
					$details['name']=$result['name'];
					$details['cabin']=$result['cabin'];
					$details['opo']=$result['opo'];
					$details['opt']=$result['opt'];
					$details['notice']=$result['notice'];
					$details['available']=$result['available'];
						
					array_push($response['data'],$details);
				}
				
				$response['message']="success";
				$response['success']=1;
				
				echo json_encode($response);
			}
			else
				$this->failedAttempt();
		}
	}
	
	public function setAvailability($id)
	{
		if(isset($_POST['available']) && $_POST['available']!='')
		{
			$available=$_POST['available'];
			 $this->authetication=$this->registry->getObject('authenticate')->checkForAuthentication($id);
		
		if($this->authetication==false)
				$this->failedAttempt();
			else
			{
				$result =$this->registry->getObject('db')->updateAvailability($available,$id);
				if($result==1)
					$this->successfullAttempt();
				else
					$this->failedAttempt();
			}
		}
		else
			$this->failedAttempt();
		
	}
	
	
	public function setNotice($id)
	{
		if(isset($_POST['notice']) && $_POST['notice']!='')
		{
		$notice=$_POST['notice'];	
		
		 $this->authetication=$this->registry->getObject('authenticate')->checkForAuthentication($id);
		
		if($this->authetication==false)
			$this->failedAttempt();
		else
		{
		
		$result =$this->registry->getObject('db')->updateNotice($notice,$id);
			if($result==1)
				$this->successfullAttempt();
			else
		        $this->failedAttempt();
		}
		}
		else
			$this->failedAttempt();
	}
	
	public function editProfile($id)
	{
		if(isset($_POST['cabin'])   &&
		   isset($_POST['contact']) &&
		   isset($_POST['opo'])     &&
		   isset($_POST['opt'])     &&
		   $_POST['cabin']!=''	    &&
		   $_POST['contact']!=''    &&
		   $_POST['opo']!=''	    &&	
		   $_POST['opt']!='')
		{
			$details=array();
			
			$details['cabin']=$_POST['cabin'];
			$details['contact']=$_POST['contact'];
			$details['opo']=$_POST['opo'];
			$details['opt']=$_POST['opt'];
			
			 $this->authetication=$this->registry->getObject('authenticate')->checkForAuthentication($id);
		
		if($this->authetication==false)
				$this->failedAttempt();
			else
			{
				
			$result =$this->registry->getObject('db')->updateAllDetails($details,$id);
			if($result==1)
			$this->successfullAttempt();
			else
			$this->failedAttempt();
			}
		}
			
			else
				$this->failedAttempt();
		
	}
	
	
	public function successfullAttempt()
	{
		$response['message']="Successfull attempt";
		$response['success']=1;
	
		echo json_encode($response);
	}
	
	
	public function failedAttempt()
	{
		$response['message']="Unable to retrieve data";
		$response['success']=0;
		$response['session']=$_SESSION['sn_auth_session_id'];
		
		echo json_encode($response);
	}
}