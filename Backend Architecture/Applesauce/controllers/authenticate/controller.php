<?php

class authenticatecontroller
{
	
	private $registry;
	
	public $response=array();
	private $authentication;
	public function __construct(Registry $registry)
	{
	
		$this->registry=$registry;
		$urlBits=$this->registry->getObject('url')->getURLBits();
		if(isset($urlBits[1]))
		{
			switch ($urlBits[1])
			{
				case 'login':
					$this->login();
					break;
				case 'logout':
					$this->logout($urlBits[2]);
					break;	
			}
		}
		else
		{
			$response['message']='Unable to login';
			$response['success']=0;
			
			echo json_encode($response);
			
		}
	}
	
	public function login()
	{
		
		if(isset($_POST['username']) && isset($_POST['password']) && $_POST['username']!='' && $_POST['password']!='')
		{
			
			$username=$this->registry->getObject('db')->sanitizeData($_POST['username']);
			$password=$this->registry->getObject('db')->sanitizeData($_POST['password']);
			$password=md5($password);
			$sql="SELECT * FROM faculty WHERE username='$username' AND password='$password'";
			$result=$this->registry->getObject('db')->executeQuery($sql);
			$result=$this->registry->getObject('db')->getRows();
			$num=$this->registry->getObject('db')->numRows();
			if($num==1)
			{
				$_SESSION['sn_auth_session_id']=$result['uniqueid'];
			
				
				$id=$result['ID'];
				
				$sql="UPDATE faculty SET loggedin=1 WHERE ID=$id";
				$result=$this->registry->getObject('db')->executeQuery($sql);
				$response['message']='You have successfully loggedin';
				$response['success']=1;
				$response['ID']=$id;
			}
			else
			{
				$response['message']='Unable to connect because username or password doesnt match';
				$response['success']=0;
			}
		}
		else
		{
			$response['message']='Unable to connect';
			$response['success']=0;
		}
		
	    echo json_encode($response);
	}
	
	public function logout($iden)
	{
		
		$this->authetication=$this->registry->getObject('authenticate')->checkForAuthentication($iden);
		
		if($this->authetication==false)
		{
			$response['message']='Error logging out !';
			$response['success']=0;
				
			echo json_encode($response);
		}
		else
		{
		$sql="UPDATE faculty SET loggedin=0 WHERE ID=$iden";
		$result=$this->registry->getObject('db')->executeQuery($sql);
		if($result==1)
		{$response['message']='You have successfully logged Out';
		$response['success']=1;
		
		echo json_encode($response);
		}
		else
		{
			$response['message']='Error logging out !';
			$response['success']=0;
			
			echo json_encode($response);
		}
		
		
	}
	}
}