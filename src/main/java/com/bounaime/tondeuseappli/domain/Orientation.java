package com.bounaime.tondeuseappli.domain;

public enum Orientation {
    N,
    S,
    E,
    O;

    public Orientation tourner(Instruction instruction){
        if(instruction == Instruction.D){
            return switch (this) {
                case N -> E;
                case S -> O;
                case E -> S;
                case O -> N;
            };
        } else if (instruction == Instruction.G) {
            return switch (this){
                case N -> O;
                case S -> E;
                case E -> N;
                case O -> S;
            };
        }
        return this;
    }

}
