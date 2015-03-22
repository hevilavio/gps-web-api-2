package com.api.model;

import java.util.List;

/**
 * Created by hevilavio on 3/17/15.
 */
public interface Calculator {

    /**
     * Dada uma area e uma posicao, retorna true se e somente se
     * a posicao esta dentro da area.
     */
    boolean isInsideArea(Position position, Area area);

    /**
     * Dada uma area e uma posicao, retorna uma lista de pares
     * A,B, que sao segmentos de reta que cortam o eixo X de position.
     */
    List<Pair<AreaPosition, AreaPosition>> findCandidatesToInterssect(Position position, Area area);

    /**
     * Dada uma lista de candidatos, conta quandos deles realmente
     * estao a direita do ponto.
     */
    int countRealInterssect(Position position, Area area);

}
