
package com.dao;

import java.util.List;
import java.util.Map;

/**
 * 通用接口
 */
public interface commonDao{
	List<String> getOption(Map<String, Object> params);
	
	Map<String, Object> getFollowByOption(Map<String, Object> params);
	
	List<String> getFollowByOption2(Map<String, Object> params);
	
	void sh(Map<String, Object> params);
	
	int remindCount(Map<String, Object> params);

	Map<String, Object> selectCal(Map<String, Object> params);
/**
  	tableName 查询表
	condition1 条件1
	condition1Value 条件1值
	average 计算评论量
* */
	Map<String, Object> queryScore(Map<String, Object> params);

	List<Map<String, Object>> selectGroup(Map<String, Object> params);
	
	List<Map<String, Object>> selectValue(Map<String, Object> params);


	List<Map<String, Object>> chartBoth(Map<String, Object> params);

	List<Map<String, Object>> chartOne(Map<String, Object> params);

	/**
	 * 下面为新加的
	 */

	/**
	 * 新的级联字典表的   分组求和方法
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> newSelectGroupSum(Map<String, Object> params);

	/**
	 * 新的级联字典表的   分组条数统计统计方法方法
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> newSelectGroupCount(Map<String, Object> params);


	/**
	 * 当前表的日期分组求和
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> newSelectDateGroupSum(Map<String, Object> params);

	/**
	 * 查询字典表的分组统计总条数
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> newSelectDateGroupCount(Map<String, Object> params);

	/**
	 * 增加字段值
	 * @param params
	 * @return
	 */
	int plusCloumNumber(Map<String, Object> params);

	/**
	 * 减少字段值
	 * @param params
	 * @return
	 */
	int reduceCloumNumber(Map<String, Object> params);

	/**
	 * 修改字段值
	 * @param params
	 * @return
	 */
	int updateCloumValue(Map<String, Object> params);



/**
 * 柱状图
 -- 柱状图  查询当前表
 --             某个【年，月】
 -- 			 当前表 2 级联表 1
 -- 						统计
 --   						【日期，字符串，下拉框】
 -- 						求和
 --   						【日期，字符串，下拉框】
 -- 柱状图  查询级联表
 -- 					某个【年，月】
 -- 						统计
 --   						【日期，字符串，下拉框】
 -- 						求和
 --   						【日期，字符串，下拉框】
 */

	/**
	 * 柱状图求和
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> barSum(Map<String, Object> params);

	/**
	 * 柱状图统计
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> barCount(Map<String, Object> params);



}
