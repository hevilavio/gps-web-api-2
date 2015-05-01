package com.api.controller;

import com.api.dao.AreaDAO;
import com.api.dao.AreaDAOImpl;
import com.api.dao.PositionDAO;
import com.api.dao.PositionDAOImpl;
import com.api.model.Area;
import com.api.model.Calculator;
import com.api.model.CalculatorImpl;
import com.api.model.Position;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Camada de servico nao foi implementada
 * devido a pouca logica entre Controller e DAO.
 *
 * */
@Controller
@RequestMapping("/api")
public class GPSController {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(GPSController.class);
    private PositionDAO positionDAO;
    private AreaDAO areaDAO;
    private Calculator calculator;

    GPSController() {
        logger.info("M=GPSController, Iniciando");
        positionDAO = new PositionDAOImpl();
        areaDAO = new AreaDAOImpl();
        calculator = new CalculatorImpl();
    }

    /**
     * Retorna a atual posicao de um determinado GPS
     */
    @RequestMapping(value = "/position/{gpsId}", method = RequestMethod.GET)
    public @ResponseBody Position getPosition(@PathVariable String gpsId) {

        logger.info("M=getCurrent, gpsId=" + gpsId);

        Position currentPosition = positionDAO.getLast(Integer.parseInt(gpsId));

        return currentPosition.toGoogleMapsPosition();
    }

    /**
     * Insere a posicao de um GPS
     * <p/>
     * !! Para teste, enquanto nao fica pronto o HTTP POST no Shield
     */
    @RequestMapping(value = "/position/save/{gpsId}/{latitude}/{longitude}/{dirLatitude}/{dirLongitude}", method = RequestMethod.GET)
    public @ResponseBody Integer savePositionWithGet(@PathVariable String gpsId,
                                                     @PathVariable String latitude,
                                                     @PathVariable String longitude,
                                                     @PathVariable String dirLatitude,
                                                     @PathVariable String dirLongitude) {

        logger.info("M=savePositionWithGet, gpsId=" + gpsId + ", latitude=" + latitude + ", longitude=" + longitude);

        if (isInvalidData(latitude, longitude)) {
            return 0;
        }

        Position position = new Position(Integer.parseInt(gpsId),
                Double.parseDouble(latitude),
                Double.parseDouble(longitude),
                dirLatitude,
                dirLongitude
                );

        positionDAO.insert(position);
        logAreaInfo(position);

        return position.getId();
    }



    /**
     * Retorna a atual Area ativa ou um JSON vazio, caso ela nao exista.
     *
     * */
    @RequestMapping(value = "/area", method = RequestMethod.GET)
    public @ResponseBody Area getActiveArea(){
        logger.info("M=getActiveArea");

        return areaDAO.getActiveArea();
    }

    /**
     * Salva uma Area, tornando- a ativa, ou seja, desativando a Area atual caso exista.
     *
     * */
    @RequestMapping(value = "/area", method = RequestMethod.POST)
    public @ResponseBody Integer createNewArea(ModelMap model, @RequestBody Area area){
        logger.info("M=createNewArea, area=" + area.toString());

        return areaDAO.createNew(area);
    }


    public boolean isInvalidData(String latitude, String longitude) {
        if ("inf".equalsIgnoreCase(latitude) || "inf".equalsIgnoreCase(longitude)) {
            return true;
        }

        return false;
    }

    // Informa no log se a Position esta dentro da Area atual
    private void logAreaInfo(final Position position) {

        Area area = areaDAO.getActiveArea(); // Nao faz cache
        boolean insideArea = calculator.isInsideArea(position, area);

        logger.info("M=logAreaInfo, areaId=" + area.getId()
                + ", position=" + position
                + ", insideArea=" + insideArea);
    }
}