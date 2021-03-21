package pl.sdacademy.database.dao;

import pl.sdacademy.database.entity.Run;

import java.util.List;

public interface RunDao {
    void save(Run run);
    Run findById(Long id);
    List<Run> findAll();
    void deleteById(Long id);

List<Run> findMembersByLimitRange(int min, int max);

}
