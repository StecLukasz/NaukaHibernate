package pl.sdacademy.database.dao;

import pl.sdacademy.database.entity.Run;
import pl.sdacademy.database.entity.RunMember;

import java.util.List;

public interface RunMemberDao {

    void save(RunMember runMember);
    RunMember findById(Long id);
    List<RunMember> findAll();
    void deleteById(Long id);

    List<RunMember> findByNameFragment(String fragment);

}
