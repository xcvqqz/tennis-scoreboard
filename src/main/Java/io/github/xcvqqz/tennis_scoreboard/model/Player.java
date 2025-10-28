package io.github.xcvqqz.tennis_scoreboard.model;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity     //всегда должен быть пустой конструктор и поле id, использует Hibernate как сущность
@Table(name = "Players") //необязательно использовать, так как одно и тоже название, но лучше явно указать
public class Player {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //означает что hibernate не думает о генерации id, посмотреть SEQUENCE;
    private Long id;

    @Column(name = "name",unique = true)
    private String name;

    @OneToMany(mappedBy = "playerOne", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Match> matchesAsPlayerOne = new ArrayList<>();

    @OneToMany(mappedBy = "playerTwo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Match> matchesAsPlayerTwo = new ArrayList<>();

    @OneToMany(mappedBy = "winner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Match> wonMatches = new ArrayList<>();

    public Player(){}

    public Player(String name) {
        this.name = name;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public List<Match> getMatchesAsPlayerOne() {return matchesAsPlayerOne;}
    public void setMatchesAsPlayerOne(List<Match> matchesAsPlayerOne) {this.matchesAsPlayerOne = matchesAsPlayerOne;}

    public List<Match> getMatchesAsPlayerTwo() {return matchesAsPlayerTwo;}
    public void setMatchesAsPlayerTwo(List<Match> matchesAsPlayerTwo) {this.matchesAsPlayerTwo = matchesAsPlayerTwo;}

    public List<Match> getWonMatches() {return wonMatches;}
    public void setWonMatches(List<Match> wonMatches) {this.wonMatches = wonMatches;}

    public List<Match> getAllMatches() {
        List<Match> allMatches = new ArrayList<>();
        allMatches.addAll(getMatchesAsPlayerOne());
        allMatches.addAll(getMatchesAsPlayerTwo());
        return allMatches;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
