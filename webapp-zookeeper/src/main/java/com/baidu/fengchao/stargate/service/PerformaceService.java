package com.baidu.fengchao.stargate.service;

import java.util.Map;

public interface PerformaceService {

	/**
	 * 根据给的long array，反转array中的每个位置的值
	 * @param reqarray
	 * @return
	 */
	public long[] convertarray(long[] reqarray);
	
	/**
	 * 根据给的迭代精度，计算一遍pi的值，然后返回计算时间
	 * @param pi_accuracy
	 * @return
	 */
	public long serverPressure(long pi_accuracy);
	
	/**
	 * 解出集合中的值，然后修改一个值，并返回这个集合
	 * @param req
	 * @return
	 */
	public Map<String,Object> unpackCollection(Map<String, Object> req);
}
