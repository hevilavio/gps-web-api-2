package com.api.model;

import java.util.List;

/**
 * Created by hevilavio on 3/17/15.
 */
public interface Calculator {

    /**
     * Dada uma area e uma posi��o, retorna true se e somente se
     * a posi��o est� dentro da �rea.
     */
    boolean isInsideArea(Position position, Area area);

    /**
     * Dada uma area e uma posi��o, retorna uma lista de pares
     * A,B, que s�o segmentos de reta que cortam o eixo X de position.
     */
    List<Pair<Position, Position>> findCandidatesToInterssect(Position position, Area area);

    /**
     * Dada uma lista de candidatos, conta quandos deles realmente
     * est�o � direita do ponto.
     */
    int countRealInterssect(Position position, Area area);

}
