package hiber.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "users", schema = "public", catalog = "postgres")
@Getter
@Setter
@NoArgsConstructor
public class User {

   @Transient
   public boolean toStringCalled = false;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(name = "name")
   private String firstName;
   @Column(name = "last_name")
   private String lastName;
   @Column(name = "email")
   private String email;
   @OneToOne
   @JoinColumn(name = "car_id")
   private Car car;

   public User(String firstName, String lastName, String email) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
   }

   @Override
   public String toString() {
      final StringBuilder sb = new StringBuilder("User{");
      sb.append("id=").append(id);
      sb.append(", firstName='").append(firstName).append('\'');
      sb.append(", lastName='").append(lastName).append('\'');
      sb.append(", email='").append(email).append('\'');
      if (toStringCalled) {
         toStringCalled = false;
      } else {
         if (car != null) car.toStringCalled = true;
         sb.append(", car=").append(car);
      }
      sb.append('}');
      return sb.toString();
   }
}
