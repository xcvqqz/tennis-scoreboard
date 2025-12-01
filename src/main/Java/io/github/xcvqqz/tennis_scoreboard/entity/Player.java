package io.github.xcvqqz.tennis_scoreboard.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Players")
public class Player {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",unique = true, nullable = false)
    private String name;

    public static String normalizeName(String name){
        if(name.length() >= 2){
            return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase().trim();
        }
        return name;
    }
}