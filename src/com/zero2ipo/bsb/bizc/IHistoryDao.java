package com.zero2ipo.bsb.bizc;

import java.util.List;
import java.util.Map;

import com.zero2ipo.bsb.entity.Car;
import com.zero2ipo.bsb.entity.ServiceProject;

public interface IHistoryDao {
	
	public List<Car> findAllListPage(Map<String, Object> map, int i, int max);
	
	public List<Car> findAllList(Map<String, Object> map);
	
	public boolean update(Car notic);
	
	public int findAllListCount(Map<String, Object> map);

	public Car findById(String id);
	public ServiceProject getServiceProjectById(String id) ;
	public String getServiceProjectNames(String ids);
	
}
