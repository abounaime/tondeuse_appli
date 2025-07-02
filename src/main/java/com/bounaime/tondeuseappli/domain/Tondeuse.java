package com.bounaime.tondeuseappli.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



@Builder
@Getter @Setter
@AllArgsConstructor
public class Tondeuse {
    private final String id;
    @JsonProperty("start_position")
    private final Position startPosition;
    private Orientation orientation;
    private final Instruction[] instructions;
    private Position currentPosition;

    public void avancer(Field field){

        Integer x = currentPosition.getX();
        Integer y = currentPosition.getY();
        Integer maxY = field.max_y();
        Integer maxX= field.max_x();

        switch (orientation) {
            case N -> {
                if (y < maxY) currentPosition = new Position(x, y + 1);
            }
            case S -> {
                if (y > 0)    currentPosition = new Position(x, y - 1);
            }
            case E -> {
                if (x < maxX) currentPosition = new Position(x + 1, y);
            }
            case O -> {
                if (x > 0)    currentPosition = new Position(x - 1, y);
            }
        }
    }
}
