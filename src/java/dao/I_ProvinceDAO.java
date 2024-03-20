/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Province;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public interface I_ProvinceDAO {
    Province getProvince(int provinceId);
    ArrayList<Province> getAllProvince();
}
