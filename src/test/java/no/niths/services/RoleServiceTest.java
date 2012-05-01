package no.niths.services;

import static org.junit.Assert.assertEquals;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.school.Role;
import no.niths.domain.school.Student;
import no.niths.services.school.interfaces.RoleService;
import no.niths.services.school.interfaces.StudentService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private StudentService studService;
    
    @Test
    public void testCRUD(){
        int size = roleService.getAll(null).size();
        Role r1 = new Role("ROLE_XYZZ");
        roleService.create(r1);
        assertEquals(size + 1, roleService.getAll(null).size());
        
        Role temp = new Role("ROLE_XYZZ");
        assertEquals(1, roleService.getAll(temp).size());
        
        assertEquals(r1, roleService.getById(r1.getId()));
        
        Role fetched = roleService.getById(r1.getId());
        fetched.setRoleName("SSSSS");
        roleService.update(fetched);
        
        assertEquals("SSSSS", roleService.getById(r1.getId()).getRoleName());
        
        roleService.hibernateDelete(r1.getId());
        assertEquals(size, roleService.getAll(null).size());
    }
    
    @Test
    public void testStudentRelation(){
        Student s1 = new Student("abc@abcd.com");
        Student s2 = new Student("abcd@abcd.com");
        studService.create(s1);
        studService.create(s2);
        
        Role r1 = new Role("ABCD");
        Role r2 = new Role("ABCDE");
        roleService.create(r1);
        roleService.create(r2);
        
        //Add roles to student
        //Student t1 = studService.getById(s1.getId());
        Student t1 = studService.getStudentWithRoles(s1.getId());
        int t1Roles = t1.getRoles().size();
        t1.getRoles().add(r1);
        t1.getRoles().add(r2);
        studService.update(t1);
        assertEquals(t1Roles + 2, studService.getStudentWithRoles(s1.getId()).getRoles().size());
        
        //Remove a role from student
        t1 = studService.getStudentWithRoles(s1.getId());
        t1.getRoles().remove(r1);
        studService.update(t1);
        assertEquals(t1Roles + 1, studService.getStudentWithRoles(s1.getId()).getRoles().size());
        roleService.hibernateDelete(r1.getId());
        
        studService.hibernateDelete(t1.getId());
        
        roleService.hibernateDelete(r2.getId());
        studService.hibernateDelete(s2.getId());
    }
    
//    @Test(expected = org.hibernate.exception.ConstraintViolationException.class)
//    public void testConstraint(){
//        Role r1 = new Role("ROLED_XYZ");
//        Role r2 = new Role("ROLED_XYZ");
//        roleService.create(r1);
//        roleService.create(r2);
//    }
    
}
