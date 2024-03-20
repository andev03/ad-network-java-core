/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.TraStatus;

/**
 *
 * @author nguye
 */
public interface I_TraStatusDAO {
    TraStatus getTraStatus(int statusNo);
}
