package ma.emsi.covoiturage.security.Registration.tokenPassager;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.emsi.covoiturage.model.Passager;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class ConfirmationTokenPassager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)

    private LocalDateTime createdAt;
    @Column(nullable = false)

    private LocalDateTime expiredAt;

    private LocalDateTime confirmedAt;
    @ManyToOne
    @JoinColumn(name = "passager_id", nullable = false)
    private Passager passager;



    public ConfirmationTokenPassager(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiredAt,
                             Passager passager) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.passager= passager;
    }

}
