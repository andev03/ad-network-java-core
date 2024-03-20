/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Location;

/**
 *
 * @author user
 */
public interface I_LocationDAO {
    Location getLocation(int locationId);
    Location getLocationByUserId(int userId);
}
