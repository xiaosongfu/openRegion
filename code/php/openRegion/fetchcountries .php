<?php

//判断请求参数中是否包含市id
if(!isset($_GET['cityid'])){
	echo JsonUtils::generateJson('1000','request error',array());
}else{

	//获取文档路径，在整个应用中使用绝对路径
	define("DOC_PATH_ROOT",$_SERVER['DOCUMENT_ROOT']);

	//引入json类库
	require_once(DOC_PATH_ROOT."/libs/json/json.func.php");
	//引入数据库操作类库
	require_once(DOC_PATH_ROOT."/libs/database/database.func.php");

	//市id
	$cityID = $_GET['cityid'];
	//查询省的SQL语句
	$sql = "select id,name from countries where cityID=?";

	//执行查询操作
	$countries = DBUtils::executeQuery($sql , array($cityID));

	//判断查询结果
	if($countries != null){
		echo JsonUtils::generateJson('1005','fetch success',$countries);
	}else{
		echo JsonUtils::generateJson('1006','fetch fail',array());
	}
}