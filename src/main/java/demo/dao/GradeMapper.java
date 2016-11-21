package demo.dao;

import java.util.List;

import demo.model.Grade;

public interface GradeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Grade record);

    int insertSelective(Grade record);

    Grade selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Grade record);

    int updateByPrimaryKey(Grade record);
    
    
    Grade selectGradeAndStudents(Integer id);
    
    List<Grade> selectAllGradeAndStudents();
    
    List<Grade> selectAllGradeAndStudentsN1();
}