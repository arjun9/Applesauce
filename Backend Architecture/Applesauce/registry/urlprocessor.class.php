<?php

class urlprocessor
{
	private $registry;
	private $urlPath;
	private $urlBits=array();
	
	public function __construct(Registry $registry)
	{
		$this->registry=$registry;
	}
	
	public function setURLPath($path)
	{
		$this->urlPath=$path;
	}
	
	public function getURLData()
	{
		$urldata=(isset($_GET['page']))?$_GET['page']:'';
		$this->urlPath=$urldata;
		if($urldata=='')
		{
			$this->urlBits[]='';
			$this->urlPath='';
		}
		else
		{
			$data=explode('/',$urldata);
			
			$this->urlBits=$this->array_trim($data);
		}
	}
	
	public function getURLBits()
	{
		return $this->urlBits;
	}
	
	public function getURLBit($whichBit)
	{
	return (isset($this->urlBits[$whichBit]))?$this->urlBits[$whichBit]:0;
	}
	
	public function getURLPath()
	{
		return $this->urlPath;
	}
	
	public function array_trim($data)
	{
		// now there can be a case where the top of the stack or the
		//bottom of the stack remain empty then we do the
		// necessary adjustments
		
		while(!empty($data) && strlen(reset($data))===0)
		{
			array_shift($data);
		}
		while(!empty($data) && strlen(end($data))===0)
		{
			array_pop($data);
		}
		
		return $data;
	}
}