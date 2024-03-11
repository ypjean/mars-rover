package com.mars.rover.model;

import com.mars.rover.model.Direction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rovers")
public class Rover {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "x_axis", nullable = false)
    private int x;

    @Column(name = "y_axis", nullable = false)
    private int y;

    @Enumerated(EnumType.STRING)
    @Column(name = "direction", nullable = false)
    private Direction direction;

    public String coordinates() {
        StringBuilder sb = new StringBuilder();
        sb.append(x);
        sb.append(", ");
        sb.append(y);
        return sb.toString();
    }
}
