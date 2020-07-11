package am.basic.springTest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 2,message = "Name must be more then 2 characters")
    private String name;

    @Size(min = 2,message = "Description must be more then 2 characters")
    private String description;

    @Column(name = "user_id", nullable = false)
    private int userId;



}
