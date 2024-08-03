package com.course.Service;
import com.course.Client.StudentClient;
import com.course.DTO.StudentDTO;
import com.course.Entity.Course;
import com.course.Repository.CourseRepository;
import com.course.Response.StudentByCourseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentClient studentClient;

    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }

    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    public void save(Course course) {
        courseRepository.save(course);
    }

    public StudentByCourseResponse findStudentsByCourseId(Long courseId) {

        Course course = courseRepository.findById(courseId).orElseThrow();

        List<StudentDTO> studentDTOList = studentClient.findAllStudentByCourse(course.getId());

        return StudentByCourseResponse.builder()
                .courseName(course.getName())
                .teacher(course.getTeacher())
                .studentDTOList(studentDTOList)
                .build();
    }

}
