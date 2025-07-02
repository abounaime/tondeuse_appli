package com.bounaime.tondeuseappli.dto;

import com.bounaime.tondeuseappli.domain.Orientation;
import com.bounaime.tondeuseappli.domain.Position;

import java.util.List;

public record TondeuseResponse (String id, Orientation orientation, Position currentPosition) {}
