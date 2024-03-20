/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Users;

/**
 *
 * @author nguye
 */
public interface I_UsersDAO {
    Users getUsers(int userId);
    Users getUsersForViewTechni(int userId);
    Users getUsersForViewContract(int userId);
    int insertUsersRegister(String firstName, String lastName);
}
