package io.github.xcvqqz.tennis_scoreboard.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Matches")
public class Match {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player1", referencedColumnName = "id")
    private Player playerOne;

    @ManyToOne
    @JoinColumn(name = "player2", referencedColumnName = "id")
    private Player playerTwo;

    @ManyToOne
    @JoinColumn(name = "winner", referencedColumnName = "id")
    private Player winner;

}