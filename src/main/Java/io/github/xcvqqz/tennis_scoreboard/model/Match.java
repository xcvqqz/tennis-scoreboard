package io.github.xcvqqz.tennis_scoreboard.model;

import jakarta.persistence.*;

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

    public Match(Player playerOne, Player playerTwo, Player winner) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.winner = winner;
    }

    public Match() {}



    public Player getPlayerOne() {return playerOne;}
    public void setPlayerOne(Player playerOne) {this.playerOne = playerOne;}
    public Player getPlayerTwo() {return playerTwo;}
    public void setPlayerTwo(Player playerTwo) {this.playerTwo = playerTwo;}
    public Player getWinner() {return winner;}
    public void setWinner(Player winner) {this.winner = winner;}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", playerOne=" + playerOne +
                ", playerTwo=" + playerTwo +
                ", winner=" + winner +
                '}';
    }
}
