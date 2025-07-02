package com.bounaime.tondeuseappli;

import com.bounaime.tondeuseappli.domain.*;
import com.bounaime.tondeuseappli.dto.TondeuseRequest;
import com.bounaime.tondeuseappli.dto.TondeuseResponse;
import com.bounaime.tondeuseappli.service.TondeuseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TondeuseServiceTest {

    @InjectMocks
    TondeuseService tondeuseService;
    @Test
    void shouldReturnTheCorrectFinalPosition(){

        var field = new Field(5,5);
        var startPositionTondeuse1 = new Position(1, 2);
        Instruction instrcutionsTondeuse1[] = {Instruction.G, Instruction.A, Instruction.G, Instruction.A,
                Instruction.G, Instruction.A, Instruction.G, Instruction.A, Instruction.A};

        var startPositionTondeuse2 = new Position(3, 3);
        Instruction instrcutionsTondeuse2[] = {Instruction.A, Instruction.A, Instruction.D, Instruction.A,
                Instruction.A, Instruction.D, Instruction.A, Instruction.D, Instruction.D, Instruction.A};
        var tondeuse1 = Tondeuse.builder()
                .id("mower1")
                .startPosition(startPositionTondeuse1)
                .orientation(Orientation.N)
                .instructions(instrcutionsTondeuse1)
                .build();
        var tondeuse2 = Tondeuse.builder()
                .id("mower2")
                .startPosition(startPositionTondeuse2)
                .orientation(Orientation.E)
                .instructions(instrcutionsTondeuse2)
                .build();

        Tondeuse[] tondeuses = {tondeuse1, tondeuse2};
        var tondeuseRequest = new TondeuseRequest(field, tondeuses);

        List<TondeuseResponse> tondeuseResponses  = tondeuseService.start(tondeuseRequest);

        assertEquals(Orientation.N, tondeuseResponses.get(0).orientation());
        assertEquals(new Position(1, 3),tondeuseResponses.get(0).currentPosition());

        assertEquals(Orientation.E, tondeuseResponses.get(1).orientation());
        assertEquals(new Position(5, 1),tondeuseResponses.get(1).currentPosition());

    }

    @Test
    void shouldStopAtFieldBorders(){
        var field = new Field(5,5);
        var startPositionTondeuse1 = new Position(5, 5);

        Instruction instrcutionsTondeuse1[] = {Instruction.A, Instruction.A, Instruction.A,};

        var startPositionTondeuse2 = new Position(0, 0);
        Instruction instrcutionsTondeuse2[] = {Instruction.A, Instruction.A, Instruction.A, Instruction.A};
        var tondeuse1 = Tondeuse.builder()
                .id("mower1")
                .startPosition(startPositionTondeuse1)
                .orientation(Orientation.N)
                .instructions(instrcutionsTondeuse1)
                .build();
        var tondeuse2 = Tondeuse.builder()
                .id("mower2")
                .startPosition(startPositionTondeuse2)
                .orientation(Orientation.S)
                .instructions(instrcutionsTondeuse2)
                .build();

        Tondeuse[] tondeuses = {tondeuse1, tondeuse2};
        var tondeuseRequest = new TondeuseRequest(field, tondeuses);

        List<TondeuseResponse> tondeuseResponses  = tondeuseService.start(tondeuseRequest);

        assertEquals(Orientation.N, tondeuseResponses.get(0).orientation());
        assertEquals(new Position(5, 5),tondeuseResponses.get(0).currentPosition());

        assertEquals(Orientation.S, tondeuseResponses.get(1).orientation());
        assertEquals(new Position(0, 0),tondeuseResponses.get(1).currentPosition());

    }
}
