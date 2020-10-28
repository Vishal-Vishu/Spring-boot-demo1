package cruddemo.cruddemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cruddemo.cruddemo.entity.Student;

@Repository
public interface SpringStudentRepository extends JpaRepository<Student,Integer>{
	List<Student> findByName(String name);
	Student findById(Long id);
}
