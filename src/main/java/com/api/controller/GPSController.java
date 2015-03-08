package com.api.controller;

import com.api.dao.PositionDAO;
import com.api.dao.PositionDAOImpl;
import com.api.model.Position;
import com.api.model.Shop;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;

@Controller
@RequestMapping("/api")
public class GPSController {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(GPSController.class);
    private PositionDAO positionDAO;


    GPSController(){
        logger.info("M=GPSController, Iniciando...");
        positionDAO = new PositionDAOImpl();
    }

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    public @ResponseBody Shop getShopInJSON(@PathVariable String name) {

        Shop shop = new Shop();
        shop.setName(name);
        shop.setStaffName(new String[] { "mkyong1", "mkyong2", name });

        return shop;

    }

    /**
     * Retorna a atual posicao de um determinado GPS
     * */
    @RequestMapping(value = "/position/{gpsId}", method = RequestMethod.GET)
    public @ResponseBody Position getPosition(@PathVariable String gpsId) {

        logger.info("M=getCurrent, gpsId=" + gpsId);

        Position currentPosition = positionDAO.getLast(Integer.parseInt(gpsId));

        return currentPosition;
    }

    /**
     * Insere a posicao de um GPS
     *
     * */
    @RequestMapping(value = "/position", method = RequestMethod.POST)
    public @ResponseBody Integer savePosition(ModelMap model, @RequestBody Position position) {

        logger.info("M=savePosition, position=" + position.toString());

        positionDAO.insert(position);

        return position.getId();
    }

    /**
     * Insere a posicao de um GPS
     *
     * !! Para teste, enquanto nao fica pronto o HTTP POST no Shield
     *
     * */
    @RequestMapping(value = "/position/save/{gpsId}/{posX}/{posY}", method = RequestMethod.GET)
    public @ResponseBody Integer savePositionWithGet(@PathVariable String gpsId, @PathVariable String posX, @PathVariable String posY) {

        logger.info("M=savePositionWithGet, gpsId=" + gpsId + ", posX=" + posX + ", posY=" + posY);
        Position position = new Position();
        position.setGpsId(Integer.parseInt(gpsId));
        position.setPosX(Double.parseDouble(posX));
        position.setPosY(Double.parseDouble(posY));
        //position.setDate(Calendar.getInstance());

        positionDAO.insert(position);
        return position.getId();
    }

}