/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class Pagination {

    public int getIndex(int number) {
        int index = 0;

        index = (int) Math.ceil(number / 5.0);

        return index;
    }
}
