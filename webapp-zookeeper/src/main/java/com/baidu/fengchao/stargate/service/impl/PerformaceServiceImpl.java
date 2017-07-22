package com.baidu.fengchao.stargate.service.impl;

import com.baidu.fengchao.stargate.service.PerformaceService;

import java.util.*;

public class PerformaceServiceImpl implements PerformaceService {

	public long[] convertarray(long[] reqarray){
		long tmp=0;
		
		if(reqarray!=null){
			int arraylength=reqarray.length;
			for(int i=0;i<arraylength/2;i++){
				tmp=reqarray[i];
				reqarray[i]=reqarray[arraylength-i-1];
				reqarray[arraylength-i-1]=tmp;
			}
		}
		return reqarray;
	}
	
	public long serverPressure(long pi_accuracy){
		long starttime=System.currentTimeMillis();
		calculate_PI(pi_accuracy);
		long endtime=System.currentTimeMillis();
		System.out.println(endtime-starttime);
		return endtime-starttime;
		
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> unpackCollection(Map<String,Object> req){
		Map<String,Object> tmp=req;
		List<Map<String,Object>> l;
		while(tmp!=null){
			Iterator<String> i=tmp.keySet().iterator();
			if(i.hasNext()){
				String key=i.next();
				Object o=tmp.get(key);
				if(o instanceof String){
					tmp.put(key, (String)o+" arrive end");
					tmp=null;
				}
				else{
					l=(List<Map<String,Object>>)o;
					tmp=(Map<String,Object>)l.get(0);
				}
			}
			
		}
		return req;
	}
	
	@SuppressWarnings("unchecked")
	private static Map<String,Object> generateDeepCollection(int layer){
		String key="wang";
		String value="xiongjie";
		Map<String,Object> res=new HashMap<String,Object>();
		Map<String,Object> tmp=res;
		for(int i=1;i<layer;i++){
			List<Map> l=new ArrayList<Map>();
			Map m=new HashMap<String,Object>();
			l.add(m);
			tmp.put(key,l);
			tmp=(Map<String,Object>)(((List)tmp.get(key)).get(0));
		}
		tmp.put(key, value);
		return res;
	}
	
	
	
	private double calculate_PI(long limit){
		double pi=0d;
		for(long i=1L;i<limit;i+=2){
			if(i%4==3){
				pi-=1d/i;
			}
			else if(i%4==1){
				pi+=1d/i;
			}
			else;
			if((i&0x0F)==0){
				System.out.println(i);
			}
		}
		return pi*4d;
	}
	
	public static void main(String[] args) {
		Map<String,Object> a=generateDeepCollection(5);
		System.out.println(a);
		System.out.println(new PerformaceServiceImpl().unpackCollection(a));
	}
	
	
}
