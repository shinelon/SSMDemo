package demo.dao;

import demo.model.Course;

public interface CourseMapper {
    int insert(Course record);

    int insertSelective(Course record);
}