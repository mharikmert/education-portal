package portal.backend.app.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Term implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;
   private String name; // "2021-2022"
   private String startDate;
   private String endDate;
   private boolean isActive;

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
         return false;
      Term term = (Term) o;

      return Objects.equals(id, term.id);
   }

   @Override
   public int hashCode() {
      return 489543359;
   }

   @Override
   public String toString() {
      return "Term{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", startDate='" + startDate + '\'' +
            ", endDate='" + endDate + '\'' +
            '}';
   }
}
