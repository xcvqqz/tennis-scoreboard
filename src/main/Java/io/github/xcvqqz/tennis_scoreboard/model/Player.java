package io.github.xcvqqz.tennis_scoreboard.model;

import jakarta.persistence.*;

@Entity     //всегда должен быть пустой конструктор и поле id, использует Hibernate как сущность
@Table(name = "Players") //необязательно использовать, так как одно и тоже название, но лучше явно указать
public class Player {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //означает что hibernate не думает о генерации id, посмотреть SEQUENCE;
    private Long id;

    @Column(name = "name",unique = true)
    private String name;


    public Player(){}

    public Player(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static String formatName(String name){
        if(name.length() >= 2){
            return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase().trim();
        }
        return name;
    }
}
