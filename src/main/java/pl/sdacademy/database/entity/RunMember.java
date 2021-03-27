package pl.sdacademy.database.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "RUNMEMBER")
public class RunMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 100)
    private String name;
    private Integer start_number;

    @ManyToOne
    @JoinColumn(name = "id_run")
    private Run run;
    
    @ManyToMany(mappedBy = "members")

    private Set<NfcTag> tags = new HashSet<>();

    public Set<NfcTag> getTags() {
        return tags;
    }

    public void setTags(Set<NfcTag> tags) {
        this.tags = tags;
    }

    public Run getRun() {
        return run;
    }

    public void setRun(Run run) {
        this.run = run;
    }

    public RunMember() {
    }

    public RunMember(Long id, String name, Integer startNumber) {
        this.id = id;
        this.name = name;
        this.start_number = start_number;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStart_number() {
        return start_number;
    }

    public void setStart_number(Integer start_number) {
        this.start_number = start_number;
    }
}
