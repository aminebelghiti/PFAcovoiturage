package ma.emsi.covoiturage.security.Registration.token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.emsi.covoiturage.model.Conducteur;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class ConfirmationToken {
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
    @JoinColumn(name = "conducteur_id", nullable = false)
    private Conducteur conducteur;

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiredAt,
                             Conducteur conducteur) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.conducteur= conducteur;
    }
}
