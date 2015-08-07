package blackboard.plugin.springdemo.spring.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import blackboard.data.course.Course;
import blackboard.data.course.CourseMembership;
import blackboard.data.user.User;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.persist.user.UserDbLoader;
import blackboard.platform.spring.web.annotations.IdParam;


@Controller
public class HelloCourseController
{

  // Annotates a variable so that the Spring container will 
  // attempt to wire the reference for you automatically.
  @Autowired
  private CourseMembershipDbLoader _membershipLoader;

  @Autowired
  private UserDbLoader _userLoader;
  
  @Autowired
  private CourseDbLoader _courseLoader;
  
//  @Autowired 
//  private CourseService courseService;
  
  @RequestMapping("/allCourses")
  public ModelAndView listAllCourseUsers()
		  throws KeyNotFoundException, PersistenceException{
	  ModelAndView mv = new ModelAndView("allCourses");
	  //get all courses and their users
	  //modify in a format for display
	  //TODO: get Database information
	  //TODO: write method for Course Service that returns the Courses, and the Students who arent in Blackboard, but ARE in CAMS
	  //TODO: write method to process this list in order to enroll students. 
	  HashMap<Course, ArrayList<User>> courseEnrollmentMap = new HashMap<Course, ArrayList<User>>();
	  HashMap<String, ArrayList<String>> courseEnrollmentMapIds = new HashMap<String, ArrayList<String>>();
	  courseEnrollmentMap = getCoursesAndUsers();
	  courseEnrollmentMapIds = getCourseEnrollmentIDs(courseEnrollmentMap);
	  
	  mv.addObject("courseEnrollmentMap", courseEnrollmentMapIds);
	  mv.addObject("courses", getAllCourses());
	return mv;
  }

  @RequestMapping( "/course_users" )
  /* @IdParam will take the string in the request parameter listed and use it as an Id object
   to look up the object based on the type.   In this case, it will convert the string to 
   an Id and look up the Course based on the Id.*/
  public ModelAndView listCourseUsers( @IdParam( "cid" ) Course course )
    throws KeyNotFoundException, PersistenceException
  {
    ModelAndView mv = new ModelAndView( "course_users" );
    mv.addObject( "course", course );

    // Load some data and put it in the model
    List<CourseMembership> members = _membershipLoader.loadByCourseId( course.getId() );
    List<User> users = new LinkedList<User>();

   //Load all the courses , later configure it to list each course, and its enrollement
    List<Course> courses = _courseLoader.loadAllCourses();
    
    
    
    for ( CourseMembership member : members )
    {
      users.add( _userLoader.loadById( member.getUserId() ) );
    }
    
   // mv.addObject("currentUsers", courseUsers);
    mv.addObject( "users", users );
    mv.addObject("courses", courses);
    return mv;
  }
  
  /* getCoursesAndUsers: gets all the courses and the users within them
  * @returns: List of List
  */
 public HashMap<Course, ArrayList<User>> getCoursesAndUsers()
 throws KeyNotFoundException, PersistenceException{
 	
 	//create hashmap to store values
 			HashMap<Course, ArrayList<User>> courseEnrollmentMap = new HashMap<Course, ArrayList<User>>();
 	//get list of courses
 			List<Course> courses = getAllCourses(); //TODO:Update to only get courses for a specific semester
 			//use list of courses to get its users 
 			
 			for (Course course : courses){
 				//populate map with the course
 				courseEnrollmentMap.put(course, new ArrayList<User>());
 				List<CourseMembership> members = _membershipLoader.loadByCourseId(course.getId());
 				
 				for(CourseMembership member: members){//add Users To Course
 					courseEnrollmentMap.get(course).add(_userLoader.loadById(member.getUserId())); 
 					//courseEnrollmentMap.put(course, courseEnrollmentMap.get(course));
 				}
 			}
 			return courseEnrollmentMap;
 }
 
 public HashMap<String, ArrayList<String>> getCourseEnrollmentIDs(HashMap<Course, ArrayList<User>> objectEnrollments){
		//convert raw enrollments to strings
		HashMap<String, ArrayList<String>> courseEnrollmentIds = new HashMap<String, ArrayList<String>>();
		for(Course course: objectEnrollments.keySet()){
			courseEnrollmentIds.put(course.getCourseId(), new ArrayList<String>());
				for( User users: objectEnrollments.get(course)){
					courseEnrollmentIds.get(course.getCourseId()).add(users.getEmailAddress());
					//courseEnrollmentIds.put(course.getCourseId(), courseEnrollmentIds.get(course));
				}
				
		}
		
		
		return courseEnrollmentIds;
	}

	/*
	 * GetAllCourses: gets all the available courses in Blackboard
	 * @param:String of the semester to search for, if null, pull all courses  semester
	 * @returns: List of Courses within blackboard
	 */
	public List<Course> getAllCourses()
	throws KeyNotFoundException, PersistenceException{
		List<Course> allCourses = new ArrayList<Course>();
		allCourses = _courseLoader.loadAllCourses();
		return allCourses;
			 
	
		
		
	}
  

}
