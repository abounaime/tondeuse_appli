package com.bounaime.tondeuseappli.service;

import com.bounaime.tondeuseappli.domain.*;
import com.bounaime.tondeuseappli.dto.TondeuseRequest;
import com.bounaime.tondeuseappli.dto.TondeuseResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TondeuseService {
    private final static Logger log = LoggerFactory.getLogger(TondeuseService.class);

    public List<TondeuseResponse> start(TondeuseRequest tondeuseRequest) {
        Field field = tondeuseRequest.field();
        List <Tondeuse> tondeuses = Arrays.stream(tondeuseRequest.mowers()).toList();
        List <TondeuseResponse> tondeuseResponses = new ArrayList<>();

        for(Tondeuse tondeuse : tondeuses){
            log.info("Tondeuse: {}, Start Position: {}, Orientation: {}"
                    , tondeuse.getId(), tondeuse.getStartPosition(), tondeuse.getOrientation());
            tondeuse.setCurrentPosition(new Position(tondeuse.getStartPosition().getX(),
                    tondeuse.getStartPosition().getY()));

            Instruction[] instructions = tondeuse.getInstructions();
            for (Instruction instruction : instructions){
                executeInstruction(instruction, tondeuse, field);
            }
            log.info("Tondeuse: {}, achève sa série d'instruction, son Orientation: {}, sa finale Position: {} "
                    , tondeuse.getId(), tondeuse.getOrientation(), tondeuse.getCurrentPosition().toString());
            tondeuseResponses.add(new TondeuseResponse(tondeuse.getId(), tondeuse.getOrientation(),
                    tondeuse.getCurrentPosition()));
        }
        return tondeuseResponses;
    }

    private void executeInstruction(Instruction instruction, Tondeuse tondeuse, Field field) {
        if(instruction == Instruction.A){
            tondeuse.avancer(field);
            log.info("Tondeuse: {}, avance à l'Orientation: {}, Current Position: {} "
                    , tondeuse.getId(), tondeuse.getOrientation(), tondeuse.getCurrentPosition().toString());
        }
        if(instruction == Instruction.D || instruction == Instruction.G){
            Orientation curentOrientation = tondeuse.getOrientation();
            tondeuse.setOrientation(curentOrientation.tourner(instruction));
            log.info("Tondeuse: {}, a pris l'Orientation: {}, suite à l'instruction {}, Current Position: {} ",
                    tondeuse.getId(),
                    tondeuse.getOrientation(),
                    instruction,
                    tondeuse.getCurrentPosition().toString()
                    );
        }
    }
}
