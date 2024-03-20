/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ReStatus;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public interface I_ReStatusDAO {

    ReStatus getReStatus(int statusNo);
    
    ArrayList<ReStatus> getAllReStatus();

    ArrayList<ReStatus> getAllReStatusForTechni();
}
