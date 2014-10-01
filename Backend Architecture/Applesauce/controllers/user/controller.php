<?php

class usercontroller
{
	private $registry;
	public $response=array();
	
	
	
	public function __construct(Registry $registry)
	{
	 $this->registry=$registry;
	$urlBits=$this->registry->getObject('url')->getURLBits();
		if(isset($urlBits[1]))
		{
			switch($urlBits[1])
			{
				case '1':
					$this->popSchool(1,$urlBits[2]);
					break;
				case '2':
					$this->popFaculty(2,$urlBits[2]);
					break;
				case '3':
					$this->popPfaculty(3,$urlBits[2]);
					break;
				case '4':
					$this->notAllowed();
					break;
			}
		}
		else
		{
		$response['message']='Unable to get results';
		$response['success']=0;
		
		return json_encode($response);
		}
		
	}
	
	public function popSchool($db,$id)
	{
		$result=$this->registry->getObject('db')->getData(0,$db,'ID',$id);
		$details=array();
		$response['data']=array();
		
		while($result=$this->registry->getObject('db')->getRows())
		{
		$details['ID']=$result['ID'];
		$details['school']=$result['school'];
		$details['manager']=$result['manager'];

		array_push($response['data'],$details);
		}
		
		$response['message']="success";
		$response['success']=1;
		
		 echo json_encode($response);
	}
	
	public function popFaculty($db,$id)
	{
		$result=$this->registry->getObject('db')->getData(0,$db,'schoolid',$id);
		$details=array();
		$response['data']=array();
		while($result=$this->registry->getObject('db')->getRows())
		{
			$details['ID']=$result['ID'];
			$details['name']=$result['name'];
			$details['designation']=$result['designation'];
	
			array_push($response['data'],$details);
		}
	
		$response['message']="success";
		$response['success']=1;
	
		echo json_encode($response);
	}
	
	public function popPfaculty($db,$id)
	{
		$result=$this->registry->getObject('db')->getData(0,$db,'teacherid',$id);
		$details=array();
		$response['data']=array();
		while($result=$this->registry->getObject('db')->getRows())
		{
			$details['ID']=$result['ID'];
			$details['contact']=$result['contact'];
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
	
	public function notAllowed()
	{
		$response['message']="You are not authorized to use this facility";
		$response['success']=0;
		
		echo json_encode($response);
	}

}