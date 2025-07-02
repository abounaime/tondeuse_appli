package com.bounaime.tondeuseappli;

import com.bounaime.tondeuseappli.dto.TondeuseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TondeuseControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void should_return_final_positions_of_mowers_when_valid_payload_is_sent() throws JsonProcessingException {
        String requestEntity = """
                {
                	"field": {
                		"max_x": 5,
                		"max_y": 5
                	},
                	"mowers": [
                	{
                		"id": "mower1",
                		"start_position": {
                			"x": 1,
                			"y": 2
                		},
                		"orientation": "N",
                		"instructions": [
                			"G",
                			"A",
                			"G",
                			"A",
                			"G",
                			"A",
                			"G",
                			"A",
                			"A"
                		]
                	},
                	{
                		"id": "mower2",
                		"start_position": {
                			"x": 3,
                			"y": 3
                		},
                		"orientation": "E",
                		"instructions": [
                			"A",
                			"A",
                			"D",
                			"A",
                			"A",
                			"D",
                			"A",
                			"D",
                			"D",
                			"A"
                		]
                	}]
                }
                """;
        ResponseEntity<String> response = restTemplate.postForEntity(
                "/api/tondeuses",
                requestEntity,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        ObjectMapper objectMapper = new ObjectMapper();
        List<TondeuseResponse> body = objectMapper.readValue(
                response.getBody(),
                new TypeReference<List<TondeuseResponse>>() {}
        );
        assertThat(body).isNotNull();
        assertThat(body.size()).isEqualTo(2);

// Then verify each mower response
        TondeuseResponse mower1 = body.get(0);
        assertThat(mower1.id()).isEqualTo("mower1");
        assertThat(mower1.currentPosition().getX()).isEqualTo(1);
        assertThat(mower1.currentPosition().getY()).isEqualTo(3);
        assertThat(mower1.orientation()).isEqualTo("N");

        TondeuseResponse mower2 = body.get(1);
        assertThat(mower2.id()).isEqualTo("mower2");
        assertThat(mower2.currentPosition().getX()).isEqualTo(5);
        assertThat(mower2.currentPosition().getY()).isEqualTo(1);
        assertThat(mower2.orientation()).isEqualTo("E");

    }
}
