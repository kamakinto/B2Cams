package blackboard.plugin.springdemo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import blackboard.plugin.springdemo.dao.FooDao;
import blackboard.plugin.springdemo.model.Foo;

@Component
public class FooService {
	
	@Autowired
	private FooDao fooDao;
	
	public List<Foo> getFoos(){
		return fooDao.getFoos();
	}
	
	public Map<String, Object> getEntityManagerInfo(){
		return fooDao.getConnectionInfo();
	}

}
