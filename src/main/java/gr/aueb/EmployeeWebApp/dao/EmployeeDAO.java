package gr.aueb.EmployeeWebApp.dao;


import gr.aueb.EmployeeWebApp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {

    //Custom query
    @Query(value = "select * from employees s where s.first_name like %:keyword% or s.last_name like %:keyword%", nativeQuery = true)
    List<Employee> findByKeyword(@Param("keyword") String keyword);

}