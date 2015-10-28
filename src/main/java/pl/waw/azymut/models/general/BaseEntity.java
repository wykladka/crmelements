package pl.waw.azymut.models.general;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    private long id;

    private boolean isActive;
    private LocalDateTime modificationDate;

    @OneToOne
    private User whoModified; //todo czasem tu zajzec

//    public BaseEntity() {
//    }
}
