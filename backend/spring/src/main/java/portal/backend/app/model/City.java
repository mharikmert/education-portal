package portal.backend.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class City implements Serializable {
    @Id
    private long plateNo;
    private String name;
}
