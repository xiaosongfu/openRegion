<?php 

//引入数据库配置文件
require_once(DOC_PATH_ROOT."/config/database.config.php");
/**
 * PDO操作数据库
 *
 * auther  : fuxiaosng
 * version : 1.0.0
 * data    : 20160408
 * 
 */
class DBUtils{
	/**
	 * 执行查询操作
	 *
	 */
	public static function executeQuery($sql,$paramArray = array()){
		try{
			$pdo = new PDO(DSN,USER,PASSWORD,array(PDO::ATTR_PERSISTENT =>true));
			$pdo ->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		}catch(PDOException $e){
			throw $e;
		}
		try{
			$stm = $pdo ->prepare($sql);
			if(count($paramArray) != 0){
				for($i=0; $i<count($paramArray); $i++){
					$stm -> bindParam($i+1, $paramArray[$i]);
				}
			}
			$stm ->execute();
			$stm ->setFetchMode(PDO::FETCH_ASSOC);
			$result = $stm ->fetchAll();
			return $result;
		}catch(PDOException $e){
			throw $e;
		}
	}

	/**
	 * 执行增加、删除、更新操作
	 *
	 */
	public static function executeNoQuery($sql,$paramArray = array()){
		try{
			$pdo = new PDO(DSN,USER,PASSWORD,array(PDO::ATTR_PERSISTENT =>true));
			$pdo ->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		}catch(PDOException $e){
			throw $e;
		}
		try{
			$stm = $pdo ->prepare($sql);
			for($i=0;$i<count($paramArray);$i++){
				$stm -> bindParam($i+1, $paramArray[$i]);
			}
			$stm ->execute();
			$rowCount = $stm ->rowCount();
			return $rowCount;
		}catch(PDOException $e){
			throw $e;
		}
	}
}