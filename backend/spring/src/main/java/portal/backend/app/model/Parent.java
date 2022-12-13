package portal.backend.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
public class Parent extends User implements Serializable {

    @OneToMany
    private Set<Student> students; // 1-n
}
