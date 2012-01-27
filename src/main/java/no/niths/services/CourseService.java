package no.niths.services;

import no.niths.domain.Course;
import no.niths.infrastructure.CourseDAOimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class CourseService implements ICourseService{
   
//   IGenericDAO< Course > dao;
//   
//   @Autowired
//   public final void setDao( final IGenericDAO< Course > daoToSet ){
//      this.dao = daoToSet;
//      this.dao.setClazz( Course.class );
//   }
	
	@Autowired
	CourseDAOimpl dao;
   
   public Course getSome(){
	   Long id = new Long(1);
	   return dao.getCourse(id);
   }
   
   // ...
   
}
