package com.bounaime.tondeuseappli.dto;

import com.bounaime.tondeuseappli.domain.Field;
import com.bounaime.tondeuseappli.domain.Tondeuse;
import lombok.*;

public record TondeuseRequest (Field field, Tondeuse[] mowers) {}
