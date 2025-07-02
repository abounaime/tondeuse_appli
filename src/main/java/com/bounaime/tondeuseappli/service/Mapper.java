package com.bounaime.tondeuseappli.service;

import com.bounaime.tondeuseappli.domain.Field;
import com.bounaime.tondeuseappli.domain.Tondeuse;
import com.bounaime.tondeuseappli.dto.TondeuseRequest;

public class Mapper {

    public static Tondeuse[] toTondeuses(TondeuseRequest tondeuseRequest){
        return tondeuseRequest.mowers();
    }
    public static Field toField(TondeuseRequest tondeuseRequest){
        return tondeuseRequest.field();
    }
}
