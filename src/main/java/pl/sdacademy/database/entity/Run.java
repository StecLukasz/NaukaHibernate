package pl.sdacademy.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "RUN")
public class Run {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer membersLimit;

    public Run() {
    }

    public Run(Long id, String name, Integer membersLimit) {
        this.id = id;
        this.name = name;
        this.membersLimit = membersLimit;
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

    public Integer getMembersLimit() {
        return membersLimit;
    }

    public void setMembersLimit(Integer membersLimit) {
        this.membersLimit = membersLimit;
    }
}
