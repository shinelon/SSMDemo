package demo.dao;

import java.util.List;

import demo.model.Stipinfo;

public interface StipinfoMapper {
    int insert(Stipinfo record);

    int insertSelective(Stipinfo record);

    List<Stipinfo> selectAll();

    Stipinfo selectByPrimaryKey(Integer id);

    int updateIp(List<Stipinfo> list);
}