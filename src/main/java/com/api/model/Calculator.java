package com.api.model;

import java.util.List;

/**
 * Created by hevilavio on 3/17/15.
 */
public interface Calculator {

    /**
     * Dada uma area e uma posição, retorna true se e somente se
     * a posição está dentro da área.
     */
    boolean isInsideArea(Position position, Area area);

    /**
     * Dada uma area e uma posição, retorna uma lista de pares
     * A,B, que são segmentos de reta que cortam o eixo X de position.
     */
    List<Pair<Position, Position>> findCandidatesToInterssect(Position position, Area area);

    /**
     * Dada uma lista de candidatos, conta quandos deles realmente
     * estão à direita do ponto.
     */
    int countRealInterssect(Position position, Area area);

}
