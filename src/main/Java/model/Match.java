package model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class Match {

    @Id
    private Long id;

    @Column(name = "player1", unique = true)
    private Player playerOne;

    @Column(name = "player2", unique = true)
    private Player playerTwo;

    @Column(name = "winner", unique = true)
    private Player winner;
}
