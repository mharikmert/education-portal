package com.fikirtepe.app.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Term implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name; // "2021-2022"
   private String startDate;
   private String endDate;
   private boolean isActive;

   @OneToMany//(mappedBy = "term", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Set<Teacher> teachers;
   @OneToMany//(mappedBy = "term", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Set<Student> students;
   @OneToMany//(mappedBy = "term", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Set<Classroom> classrooms;


   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
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
              ", teachers=" + teachers +
              ", students=" + students +
              '}';
   }
}
