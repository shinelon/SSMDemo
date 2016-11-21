package demo.dao;

import java.util.List;

import demo.model.Ipcn;

public interface IpcnMapper {
    int insert(Ipcn record);

    int insertSelective(Ipcn record);

    List<Ipcn> selectAll();
}