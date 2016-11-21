package demo.dao;

import java.util.List;

import demo.model.Ip17;

public interface Ip17Mapper {
    int insert(Ip17 record);

    int insertBatch(List<Ip17> list);

}