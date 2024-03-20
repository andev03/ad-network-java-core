/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.LogErrorDetail;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public interface I_LogErrorDetailDAO {
    LogErrorDetail getLogErrorDetail(int logErrDeId);
    ArrayList<LogErrorDetail> getAllLogErrorDetai();
}
