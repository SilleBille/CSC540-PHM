package sql;

/**
 * Created by mkdin on 16-10-2016.
 */
public class SqlQueries {
    public static final String SQL_LOGIN = "SELECT U_ID, NAME from USERS WHERE u_id=? AND password=?";


    public static final String SQL_GET_PATIENT_LIST_FOR_SUPPORTER = "SELECT PID FROM SUPPORT s1, SUPPORTER s2 WHERE s1.sid = s2.sid AND AUTH_DATE <= SYSDATE AND s2.u_id = ?";

    public static final String SQL_INSERT_USER_USERTABLE = "INSERT INTO USERS(U_ID, PASSWORD, NAME, DOB, GENDER, ADDRESS) VALUES (?, ?, ?, ?, ?, ?) ";
    public static final String SQL_INSERT_PATIENT_TABLE = "INSERT INTO PATIENT(U_ID) values (?)";
    public static final String SQL_INSERT_SUPPORTER_TABLE = "INSERT INTO SUPPORTER(U_ID) VALUES (?)";
    public static final String SQL_INSERT_HAVE_TABLE = "INSERT INTO HAVE(PID, DID) VALUES (?, 203)";

    public static final String SQL_FIND_IS_SUPPORTER = "SELECT COUNT(*) FROM USERS u, SUPPORTER s WHERE u.U_ID = s.U_ID AND u.U_ID = ?";
    public static final String SQL_FIND_PID_FOR_PATIENT = "SELECT PID FROM PATIENT WHERE U_ID=?";
    // public static final String SQL_FIND_PID_FOR_SUPPORTER = "SELECT s1.PID FROM SUPPORT s1, SUPPORTER s2 WHERE s2.U_ID=? AND s1.SID = s2.SID";

    public static final String SQL_SELECT_USER_DETAILS_PATIENT = "SELECT u.U_ID, u.DOB, u.NAME, u.ADDRESS, u.GENDER, p.STATUS FROM USERS u, PATIENT p WHERE u.U_ID = ? AND u.U_ID = p.U_ID";
    public static final String SQL_SELECT_USER_DETAILS_SUPPORTER = "SELECT u.U_ID, u.DOB, u.NAME, u.ADDRESS, u.GENDER FROM USERS u WHERE u.U_ID = ?";
    public static final String SQL_SET_USER_DETAILS_PASSWORD = "UPDATE USERS SET PASSWORD=? WHERE U_ID = ?";
    public static final String SQL_SET_USER_DETAILS_NAME = "UPDATE USERS SET NAME=? WHERE U_ID = ?";
    public static final String SQL_SET_USER_DETAILS_DOB = "UPDATE USERS SET DOB=? WHERE U_ID = ?";
    public static final String SQL_SET_USER_DETAILS_ADDRESS = "UPDATE USERS SET ADDRESS=? WHERE U_ID = ?";
    public static final String SQL_SET_USER_DETAILS_GENDER = "UPDATE USERS SET GENDER=? WHERE U_ID = ?";

    public static final String SQL_GET_DIAGNOSIS_FOR_PATIENT = "SELECT d1.did, d1.dname FROM DIAGNOSIS d1, HAVE h WHERE d1.did = h.did AND h.PID = ?";

    public static final String SQL_LIST_ALL_DIAGNOSIS = "SELECT DID, DNAME FROM DIAGNOSIS";
    public static final String SQL_UPDATE_DIAGNOSIS = "UPDATE HAVE SET DID = ? WHERE PID = ?";
    public static final String SQL_ADD_DIAGNOSIS = "INSERT INTO HAVE(DID, PID) VALUES (?, ?)";
    public static final String SQL_IS_WELL_PATIENT_DIAGNOSIS = "SELECT count(*) FROM HAVE WHERE PID = ? AND DID = 203";

    public static final String SQL_DIAGNOSIS_FOR_PATIENT = "SELECT h1.DID, d1.DNAME FROM HAVE h1, DIAGNOSIS d1 WHERE h1.DID = d1.DID AND h1.pid = ? AND h1.DID <> 203";
    public static final String SQL_UPDATE_DIAGNOSIS_TO_WELL_PATIENT = "UPDATE HAVE SET DID=203 WHERE PID = ? AND DID=?";
    public static final String SQL_DELETE_DIAGNOSIS = "DELETE FROM HAVE WHERE PID = ? AND DID=?";


}
