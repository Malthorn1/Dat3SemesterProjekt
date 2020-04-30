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
    private ArrayList<CovidDTO> CovidDTO;
    private NASADTO NASADTO;

    public CombinedDTO(OpenCageDTO ocDTO, ArrayList covid, NASADTO nDTO) {
        this.OpenCageDTO = ocDTO;
        this.CovidDTO = covid;
        this.NASADTO = nDTO;

    }

}
