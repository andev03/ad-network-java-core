/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CoStatus;

/**
 *
 * @author nguye
 */
public interface I_CoStatusDAO {
    CoStatus getCoStatus(int coStatusNo);
}
