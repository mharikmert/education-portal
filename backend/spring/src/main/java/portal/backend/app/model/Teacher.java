package portal.backend.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Teacher extends User implements Serializable {
    @OneToOne
    private Lecture lecture;
}
