<?php

//获取文档路径，在整个应用中使用绝对路径
define("DOC_PATH_ROOT",$_SERVER['DOCUMENT_ROOT']);

//引入json类库
require_once(DOC_PATH_ROOT."/libs/json/json.func.php");
//引入数据库操作类库
require_once(DOC_PATH_ROOT."/libs/database/database.func.php");

//查询省的SQL语句
$sql = "select id,name from provinces";

//执行查询操作
$provinces = DBUtils::executeQuery($sql , array());

//查询结果判断
if($provinces != null){
	echo JsonUtils::generateJson('1001','fetch success',$provinces);
}else{
	echo JsonUtils::generateJson('1002','fetch fail',array());
}