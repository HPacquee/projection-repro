package be.hapa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by hpacquee on 05/07/2017.
 */
@Entity
public class Department {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private UUID uuid;

    @ManyToOne
    private Department parent;

    @OneToMany(mappedBy = "parent", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    List<Department> children = new ArrayList<>();

    Department() {
        //empty JPA/JSON constructor
    }

    public Department(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Department getParent() {
        return parent;
    }

    public List<Department> getChildren() {
        return children;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void setParent(Department parent) {
        this.parent = parent;
    }

    public void addChild(Department department) {
        department.setParent(this);
        this.children.add(department);
    }
}
