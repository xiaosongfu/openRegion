<?php

//判断请求参数中是否包含省id
if(!isset($_GET['provinceid'])){
	echo JsonUtils::generateJson('1000','request error',array());
}else{

	//获取文档路径，在整个应用中使用绝对路径
	define("DOC_PATH_ROOT",$_SERVER['DOCUMENT_ROOT']);

	//引入json类库
	require_once(DOC_PATH_ROOT."/libs/json/json.func.php");
	//引入数据库操作类库
	require_once(DOC_PATH_ROOT."/libs/database/database.func.php");

	//省id
	$provinceID = $_GET['provinceid'];
	//查询省的SQL语句
	$sql = "select id,name from cities where provinceID=?";

	//执行查询操作
	$cities = DBUtils::executeQuery($sql , array($provinceID));

	//判断查询结果
	if($cities != null){
		echo JsonUtils::generateJson('1003','fetch success',$cities);
	}else{
		echo JsonUtils::generateJson('1004','fetch fail',array());
	}
}