package blackboard.plugin.springdemo.spring.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import blackboard.plugin.springdemo.model.Foo;
import blackboard.plugin.springdemo.service.FooService;

@Controller
public class CamsController {
	
	//@Autowired
	//CourseService courseService
	
	//Autowired
	//CamsService camsService
	
	  @RequestMapping( "/camsController" )
	  public ModelAndView cams() throws Exception
	  {
		  //camsCourseEnrollments = getcamsCourseEnrollments();
		  //bbCourseEnrollments = getbbCourseEnrollments();
		  //diffCourseEnrollments = generateDiffCourseEnrollments(camsCourseEnrollments, bbCourseEnrollments);
		  //enrollUsers(diffCourseEnrollments);
		  ModelAndView mv = new ModelAndView("camsAdmin");

		//  mv.addObject("fooList", fooService.getFoos());
		
		  return mv;
	  }

}
