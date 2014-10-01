<?php

session_start();

define("FRAMEWORK_PATH", dirname(__FILE__)."/");


require 'registry/registry.class.php';
$registry=new registry();

$registry->createAndStoreObject('db', 'mysqldb');
$registry->createAndStoreObject('url', 'urlprocessor');
$registry->createAndStoreObject('authenticate', 'authenticate');
$registry->getObject('url')->getURLData();

include (FRAMEWORK_PATH.'config.php');
$registry->getObject('db')->connect($configs['db_host_sn'],$configs['db_user_sn'],$configs['db_pass_sn'], $configs['db_name_sn']);
$controller=$registry->getObject('url')->getURLBit(0);

require_once( FRAMEWORK_PATH . 'controllers/' . $controller . '/controller.php');
$controllerInc = $controller.'controller';
$controller = new $controllerInc( $registry, true );

?>


