package blackboard.plugin.springdemo.dao;

import java.util.List;
import java.util.Map;

import blackboard.plugin.springdemo.model.Foo;

public interface FooDao {
	
	public List<Foo> getFoos();
	
	public Foo getFooByName(String name);
	
	public Long deleteFooByName(String name);
	public Map<String, Object> getConnectionInfo();

}
