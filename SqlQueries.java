package sql;

/**
 * Created by mkdin on 16-10-2016.
 */
public class SqlQueries {
    public static final String SQL_LOGIN = "SELECT * from USERS WHERE userid=? AND password=?";

    public static final String SQL_INSERT_USER_USERTABLE = "INSERT INTO USERS(PID, USERID, PASSWORD) VALUES (?, ?, ?) ";
    public static final String SQL_INSERT_PATIENT_TABLE = "INSERT INTO PATIENT(PID, SSN, PNAME, DOB, GENDER, ADDR, STATUS) values (?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_INSERT_SUPPORTER_TABLE = "INSERT INTO SUPPORTER(SID, SSN, SNAME) VALUES (?, ?, ?)";
}
