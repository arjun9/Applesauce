<?php

class mysqldb
{

	private $dbase = array(1=>'departments',2=>'faculty',3=>'pfaculty',4=>'messages');
	private $result;
	private $con;
	public function __construct(Registry $registry)
	{
		$this->registry=$registry;
	}
	
	public function connect($hostname,$user,$password,$database)
	{
		$this->con=new mysqli($hostname, $user, $password, $database);
		if(mysqli_connect_errno())
		{
			echo "Unable to connect";
		}
	}
	
	public function numRows()
	{
		return $this->result->num_rows;
	}
	
	public function getData($user,$key,$field,$id)
	{
		foreach ($this->dbase as $val=>$tbl)
		{
			if(intval($val)==$key && $key!=4)
				$db=$tbl;
			elseif($key==4)
			return -1;
		}
	  if($user==0)
		{
       if($id!=-1)
		$sql="SELECT * FROM $db WHERE `$field` = $id";
		else
		$sql="SELECT * FROM $db";
		
		if($this->con)
		{
			$this->result=$this->con->query($sql);
			return 1;
		}
		else 
		return -1;
		}
		elseif ($user==1)
		{
        if($id!=-1)
				$sql="SELECT * FROM $db WHERE `$field` = $id";
			else
				$sql="SELECT * FROM $db";
			
			if($this->con)
			{
				$this->result=$this->con->query($sql);
				return 1;
			}
			else
				return -1;
			
		}
		else 
			return -1;
		
		
		
	}
	
	public function getRows()
	{
		return $this->result->fetch_array(MYSQLI_ASSOC);
	}
	
	public function executeQuery($queryStr)
	{
		if($this->con)
		{
			$result=$this->con->query($queryStr);
			if($result)
			{
			$this->result=$result;
			return 1;
			}
			else
				return 0;
		}
		else 
			return -1;
	}
   
  public function sanitizeData($data)
	{
		//stripslashes
		if(get_magic_quotes_gpc())
		{
			$value=stripcslashes($data);
		}
		
		if(version_compare(phpversion(),"4.3.0")=="-1")
		{
			$value=$this->con->escape_string($data);
		}
		else
		{
			$value=$this->con->real_escape_string($data);
		}
		
		return $data;
		
	}
	
	/*
	 *what the teacher can do is he/she can
	*1)update the whole database
	*2)just the available part
	*3)just the special notice
	
	*/
	
	// update notice
	
	public function updateNotice($notice,$id)
	{
	  if($this->con)
	  {
	  	$sql="UPDATE pfaculty SET notice='$notice' WHERE teacherid=$id";
	  	if(!$this->con->query($sql))
	  	{
	  		return -1;
	  	}
	  	else
	  		return 1;
	  }
	  else
	  	return -1;
		
	}
	
	//update availability
	public function updateAvailability($val,$id)
	{
		if($this->con)
		{
			$sql="UPDATE pfaculty SET available='$val' WHERE teacherid=$id";
			if(!$this->con->query($sql))
			{
				return -1;
			}
			else
				return 1;
		}
		else
			return -1;
	
	}
	
	//update all the other details
	public function updateAllDetails($arr=array(),$id)
	{
		if($this->con)
		{
		if(empty($arr))
		{
			return -1;
		}
		else
		{
			
			$cabin=$arr['cabin'];
			$contact=$arr['contact'];
			$opo=$arr['opo'];
			$opt=$arr['opt'];
		$sql="UPDATE pfaculty SET  cabin='$cabin' , contact='$contact' , opo='$opo'
		, opt='$opt' WHERE teacherid=$id";	
		
		if(!$this->con->query($sql))
		{
			return -1;
		}
		else
			return 1;
		
		}
		}
		else
			return -1;
	}
	
	
	
	public function __destruct()
	{
		$this->con->close();
	}
	
}