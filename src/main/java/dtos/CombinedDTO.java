/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.ArrayList;

/**
 *
 * @author casper
 */
public class CombinedDTO {

    private OpenCageDTO OpenCageDTO;
    private WeatherDTO weatherDTO;
    private ArrayList<CovidDTO> CovidDTO;
    private NASADTO NASADTO;

    public CombinedDTO(OpenCageDTO ocDTO, WeatherDTO wDTO, NASADTO nDTO) {
        this.OpenCageDTO = ocDTO;
        this.weatherDTO = wDTO;
        this.NASADTO = nDTO;

    }

    public CombinedDTO(OpenCageDTO ocDTO, WeatherDTO wDTO, ArrayList covid, NASADTO nDTO) {
        this.OpenCageDTO = ocDTO;
        this.weatherDTO = wDTO;
        this.CovidDTO = covid;
        this.NASADTO = nDTO;
    }

}
