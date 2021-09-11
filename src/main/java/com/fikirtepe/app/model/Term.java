package com.fikirtepe.app.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Term {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name; // "2021-2022"
   private String startDate;
   private String endDate;

   @OneToMany
   private List<Teacher> teachers;
   @OneToMany
   private List<Student> students;
   @OneToMany
   private List<Class> classes;


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
              ", classes=" + classes +
              '}';
   }
}
