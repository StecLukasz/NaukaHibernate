package pl.sdacademy.database.dao;

import pl.sdacademy.database.entity.NfcTag;

import java.util.List;

public interface NfcTagDao {

    void save(NfcTag nfcTag);
    NfcTag findById(Long id);
    List<NfcTag> findAll();
    void deleteById(Long id);
}
