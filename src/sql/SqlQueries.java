package sql;

/**
 * Created by mkdin on 16-10-2016.
 */
public class SqlQueries {
    public static final String SQL_LOGIN = "SELECT U_ID, NAME from USERS WHERE u_id=? AND password=?";

    public static final String SQL_INSERT_USER_USERTABLE = "INSERT INTO USERS(U_ID, PASSWORD, NAME, DOB, GENDER, ADDRESS) VALUES (?, ?, ?, ?, ?, ?) ";
    public static final String SQL_INSERT_PATIENT_TABLE = "INSERT INTO PATIENT(U_ID) values (?)";
    public static final String SQL_INSERT_SUPPORTER_TABLE = "INSERT INTO SUPPORTER(U_ID) VALUES (?)";

    public static final String SQL_FIND_IS_SUPPORTER = "SELECT COUNT(*) FROM USERS u, SUPPORTER s WHERE u.U_ID = s.U_ID AND u.U_ID = ?";

    public static final String SQL_SELECT_USER_DETAILS_PATIENT = "SELECT u.U_ID, u.DOB, u.NAME, u.ADDRESS, u.GENDER, p.STATUS FROM USERS u, PATIENT p WHERE u.U_ID = ? AND u.U_ID = p.U_ID";
    public static final String SQL_SELECT_USER_DETAILS_SUPPORTER = "SELECT u.U_ID, u.DOB, u.NAME, u.ADDRESS, u.GENDER FROM USERS u WHERE u.U_ID = ?";
    public static final String SQL_SET_USER_DETAILS_PASSWORD = "UPDATE USERS SET PASSWORD=? WHERE U_ID = ?";
    public static final String SQL_SET_USER_DETAILS_NAME = "UPDATE USERS SET NAME=? WHERE U_ID = ?";
    public static final String SQL_SET_USER_DETAILS_DOB = "UPDATE USERS SET DOB=? WHERE U_ID = ?";
    public static final String SQL_SET_USER_DETAILS_ADDRESS = "UPDATE USERS SET ADDRESS=? WHERE U_ID = ?";
    public static final String SQL_SET_USER_DETAILS_GENDER = "UPDATE USERS SET GENDER=? WHERE U_ID = ?";


}
