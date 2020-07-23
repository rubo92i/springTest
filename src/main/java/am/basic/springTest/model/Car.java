package am.basic.springTest.model;

 import com.fasterxml.jackson.annotation.JsonFormat;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
 import org.hibernate.annotations.CreationTimestamp;

 import javax.json.bind.annotation.JsonbDateFormat;
 import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @NotBlank
    private String model;

    @NotBlank
    private String  vendor;

    @NotBlank
    @Size(min = 5)
    private String vin;

    private int year;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    // yyyy-tari MM-amis dd-or  HH-jam mm-rope ss-varkyan SSS-milivarkyan
    /*TemporalType.TIMESTAMP 1993-08-25 14:55:47*/
    /*TemporalType.DATE 1993-08-25 */
    /*TemporalType.TIME 14:55:47*/


}
