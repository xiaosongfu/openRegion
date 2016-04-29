<?php
/**
 * auther  : fuxiaosong
 * version : 1.0.0
 * date    : 20160408
 *
 */
class JsonUtils{
    /**
     * Json生成器
     *
     * $code    : 返回码
     * $message : 结果信息
     * $data    : 结果数据
     */
    public static function generateJson($code,$message='',$data=array()){
    	$jsonData = json_encode($data);
    	
    	$res = array(
    			"code" =>$code,
    			"message" =>$message,
    			"data" =>$jsonData
    	);
    	return json_encode($res);
    }
}