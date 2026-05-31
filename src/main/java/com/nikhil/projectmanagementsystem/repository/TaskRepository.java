package com.nikhil.projectmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nikhil.projectmanagementsystem.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

}
