package utils;

import sql.SqlQueries;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Tyler on 10/19/2016.
 */
public class HealthInd {
    // the indicator name, low, high values, frequency, option to enter the observation
    // PID

    static Scanner s;
    static PreparedStatement ps;
    static ResultSet rs;

    public static void healthIndMenu(Connection con) {
        int selection = 1;
        s = new Scanner(System.in);
        try {
            if (!Userid.IS_SUPPORTER) {
                while (selection != 3) {
                    System.out.print("Health Indicator Menu (1-3)\n\n");
                    System.out.print("1. View Recommendations\n" +
                            "2. Enter My Observations\n" +
                            "3. Back To Main Menu\n");

                    selection = s.nextInt();

                    switch (selection) {
                        case 1:
                            viewRecommendation(con);
                            break;
                        case 2:
                            enterObs(con);
                            break;
                        case 3:
                            break;
                        default:
                            break;
                    }//end switch
                }
            } else {
                while (selection != 4) {
                    System.out.print("Health Indicator Menu (1-3)\n\n");
                    System.out.print("1. View Recommendations for a patient\n" +
                            "2. Add Observation for a patient\n" +
                            "3. Add specific recommendations for a patient\n" +
                            "4. Back To Main Menu\n");

                    selection = s.nextInt();

                    switch (selection) {
                        case 1:
                            viewRecommendationSupporter(con);
                            break;
                        case 2:
                            enterObs(con);
                            break;
                        case 3:
                            enterSpecificRec(con);
                            break;
                        default:
                            break;
                    }//end switch
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static public Integer getInteger(ResultSet rs, String strColName) throws Exception {
        int nValue = rs.getInt(strColName);
        return rs.wasNull() ? null : nValue;
    }

    private static void viewRecommendation(Connection con) throws Exception {
        ps = con.prepareStatement(SqlQueries.SQL_GET_RECOMMENDATION_FOR_PATIENT);
        ps.setInt(1, Userid.PID_STATIC);
        rs = ps.executeQuery();

        while (rs.next()) {
            Integer temp;
            temp = getInteger(rs, "WEIGHT_LOWER");
            if (temp != null) System.out.println("WEIGHT_LOWER: " + temp);

            temp = getInteger(rs, "WEIGHT_UPPER");
            if (temp != null) System.out.println("WEIGHT_UPPER: " + temp);

            temp = getInteger(rs, "WEIGHT_FREQ");
            if (temp != null) System.out.println("WEIGHT_FREQ: " + temp);

            temp = getInteger(rs, "BPS_LOWER");
            if (temp != null) System.out.println("BPS_LOWER: " + temp);

            temp = getInteger(rs, "BPS_UPPER");
            if (temp != null) System.out.println("BPS_UPPER: " + temp);

            temp = getInteger(rs, "BPD_LOWER");
            if (temp != null) System.out.println("BPD_LOWER: " + temp);

            temp = getInteger(rs, "BPD_UPPER");
            if (temp != null) System.out.println("BPD_UPPER: " + temp);

            temp = getInteger(rs, "BP_FREQ");
            if (temp != null) System.out.println("BP_FREQ: " + temp);

            temp = getInteger(rs, "OXY_LOWER");
            if (temp != null) System.out.println("OXYGEN_LOWER: " + temp);

            temp = getInteger(rs, "OXY_UPPER");
            if (temp != null) System.out.println("OXYGEN_UPPER: " + temp);

            temp = getInteger(rs, "OXY_FREQ");
            if (temp != null) System.out.println("OXYGEN_FREQUENCY: " + temp);

            temp = getInteger(rs, "PAIN");
            if (temp != null) System.out.println("PAIN: " + temp);

            temp = getInteger(rs, "PAIN_FREQ");
            if (temp != null) System.out.println("PAIN_FREQ: " + temp);

            temp = getInteger(rs, "TEMP_LOWER");
            if (temp != null) System.out.println("TEMP_LOWER: " + temp);

            temp = getInteger(rs, "TEMP_UPPER");
            if (temp != null) System.out.println("TEMP_UPPER: " + temp);

            temp = getInteger(rs, "TEMP_FREQ");
            if (temp != null) System.out.println("TEMP_FREQ: " + temp);

            String moodString = rs.getString("MOOD");
            if (moodString != null) System.out.println("MOOD: " + moodString);

            temp = getInteger(rs, "MOOD_FREQ");
            if (temp != null) System.out.println("MOOD_FREQ: " + temp);

        }
    }

    private static void viewRecommendationSupporter(Connection con) throws Exception {
        s = new Scanner(System.in);
        System.out.println("Available Patients for you to view: ");
        ps = con.prepareStatement(SqlQueries.SQL_GET_PATIENT_LIST_FOR_SUPPORTER);
        ps.setInt(1, Userid.USER_ID_STATIC);
        rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println("PID: " + rs.getInt("PID"));
        }


        System.out.println("Enter PID to View their Recommendations: ");
        int pid = s.nextInt();

        ps = con.prepareStatement(SqlQueries.SQL_GET_RECOMMENDATION_FOR_PATIENT);
        ps.setInt(1, pid);
        rs = ps.executeQuery();

        while (rs.next()) {
            Integer temp;
            temp = getInteger(rs, "WEIGHT_LOWER");
            if (temp != null) System.out.println("WEIGHT_LOWER: " + temp);

            temp = getInteger(rs, "WEIGHT_UPPER");
            if (temp != null) System.out.println("WEIGHT_UPPER: " + temp);

            temp = getInteger(rs, "WEIGHT_FREQ");
            if (temp != null) System.out.println("WEIGHT_FREQ: " + temp);

            temp = getInteger(rs, "BPS_LOWER");
            if (temp != null) System.out.println("BPS_LOWER: " + temp);

            temp = getInteger(rs, "BPS_UPPER");
            if (temp != null) System.out.println("BPS_UPPER: " + temp);

            temp = getInteger(rs, "BPD_LOWER");
            if (temp != null) System.out.println("BPD_LOWER: " + temp);

            temp = getInteger(rs, "BPD_UPPER");
            if (temp != null) System.out.println("BPD_UPPER: " + temp);

            temp = getInteger(rs, "BP_FREQ");
            if (temp != null) System.out.println("BP_FREQ: " + temp);

            temp = getInteger(rs, "OXY_LOWER");
            if (temp != null) System.out.println("OXYGEN_LOWER: " + temp);

            temp = getInteger(rs, "OXY_UPPER");
            if (temp != null) System.out.println("OXYGEN_UPPER: " + temp);

            temp = getInteger(rs, "OXY_FREQ");
            if (temp != null) System.out.println("OXYGEN_FREQUENCY: " + temp);

            temp = getInteger(rs, "PAIN");
            if (temp != null) System.out.println("PAIN: " + temp);

            temp = getInteger(rs, "PAIN_FREQ");
            if (temp != null) System.out.println("PAIN_FREQ: " + temp);

            temp = getInteger(rs, "TEMP_LOWER");
            if (temp != null) System.out.println("TEMP_LOWER: " + temp);

            temp = getInteger(rs, "TEMP_UPPER");
            if (temp != null) System.out.println("TEMP_UPPER: " + temp);

            temp = getInteger(rs, "TEMP_FREQ");
            if (temp != null) System.out.println("TEMP_FREQ: " + temp);

            String moodTemp =rs.getString("MOOD");
            if (moodTemp != null) System.out.println("MOOD: " + moodTemp);

            temp = getInteger(rs, "MOOD_FREQ");
            if (temp != null) System.out.println("MOOD_FREQ: " + temp);

        }
    }

    private static Timestamp getTimeStamp(String dateAndTime) throws Exception{
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = dateFormat.parse(dateAndTime);
        long time = date.getTime();
        return new Timestamp(time);
    }
    public static void enterObs(Connection con) throws Exception {
        int selection = 1;
        int uid = 0;
        boolean isSupporter = Userid.IS_SUPPORTER;

        s = new Scanner(System.in);

        int weight=0, bps=0, bpd=0, oxygen=0, pain=0, temperature=0;
        String w_otime, bps_otime, bpd_otime, oxygen_otime, pain_otime, temp_otime, mood_otime;
        Timestamp w_time =null, bps_time=null, bpd_time=null, oxygen_time=null, pain_time=null, temp_time=null, mood_time=null;
        String mood=null;

        int pid;
        if (isSupporter) {
            System.out.println("Available Patients for you to view: ");
            ps = con.prepareStatement(SqlQueries.SQL_GET_PATIENT_LIST_FOR_SUPPORTER);
            ps.setInt(1, Userid.USER_ID_STATIC);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("PID: " + rs.getInt("PID"));
            }


            System.out.println("Enter PID to View their Recommendations: ");
            pid = s.nextInt();
            ps = con.prepareStatement(SqlQueries.SQL_GET_RECOMMENDATION_FOR_PATIENT);
            ps.setInt(1, pid);

        } else {
            ps = con.prepareStatement(SqlQueries.SQL_GET_RECOMMENDATION_FOR_PATIENT);
            pid = Userid.PID_STATIC;
            ps.setInt(1, pid);
        }
        rs = ps.executeQuery();

        while (rs.next()) {
            Integer temp;

            temp = getInteger(rs, "WEIGHT_FREQ");
            if (temp != null) {
                    System.out.println("Enter WEIGHT: ");
                weight = s.nextInt();
                System.out.println("Enter Observed Time in (MM/dd/yyyy HH:mm:ss)");
                s.nextLine();
                w_otime = s.nextLine();
                w_time = getTimeStamp(w_otime);

            }


            temp = getInteger(rs, "BP_FREQ");
            if (temp != null) {
                System.out.println("Enter BPS: ");
                bps = s.nextInt();
                System.out.println("Enter Observed Time in (MM/dd/yyyy HH:mm:ss)");
                s.nextLine();
                bps_otime = s.nextLine();
                bps_time = getTimeStamp(bps_otime);

                System.out.println("Enter BPD: ");
                bpd = s.nextInt();
                System.out.println("Enter Observed Time in (MM/dd/yyyy HH:mm:ss)");
                s.nextLine();
                bpd_otime = s.nextLine();
                bpd_time = getTimeStamp(bpd_otime);
            }


            temp = getInteger(rs, "OXY_FREQ");
            if (temp != null) {
                System.out.println("Enter Oxygen Saturation: ");
                oxygen = s.nextInt();
                System.out.println("Enter Observed Time in (MM/dd/yyyy HH:mm:ss)");
                s.nextLine();
                oxygen_otime = s.nextLine();
                oxygen_time = getTimeStamp(oxygen_otime);
            }

            temp = getInteger(rs, "PAIN_FREQ");
            if (temp != null) {
                System.out.println("Enter pain: ");
                pain = s.nextInt();
                System.out.println("Enter Observed Time in (MM/dd/yyyy HH:mm:ss)");
                s.nextLine();
                pain_otime = s.nextLine();
                pain_time = getTimeStamp(pain_otime);
            }


            temp = getInteger(rs, "TEMP_FREQ");
            if (temp != null) {
                System.out.println("Enter Temperature:  ");
                temperature = s.nextInt();
                System.out.println("Enter Observed Time in (MM/dd/yyyy HH:mm:ss)");
                s.nextLine();
                temp_otime = s.nextLine();
                temp_time = getTimeStamp(temp_otime);
            }

            temp = getInteger(rs, "MOOD_FREQ");
            if (temp != null) {
                System.out.println("Enter Mood {HAPPY, SAD, NEUTRAL}");
                mood = s.next().toUpperCase();
                System.out.println("Enter Observed Time in (MM/dd/yyyy HH:mm:ss)");
                s.nextLine();
                mood_otime = s.nextLine();
                mood_time = getTimeStamp(mood_otime);
            }

            ps = con.prepareStatement(SqlQueries.SQL_INSERT_OBSERVATION);
            // PID,CREATED_BY,WEIGHT,WEIGHT_OTIME,WEIGHT_RTIME,
            // 6. BPS,BPS_OTIME,BPS_RTIME,
            // 9. BPD,BPD_OTIME,BPD_RTIME,
            // 12. OXYGEN,OXYGEN_OTIME,OXYGEN_RTIME,
            // 15. PAIN,PAIN_OTIME,PAIN_RTIME,MOOD,MOOD_OTIME,MOOD_RTIME,
            // 21. TEMP,TEMP_OTIME,TEMP_RTIME
            ps.setInt(1, pid);
            ps.setInt(2, Userid.USER_ID_STATIC);
            setIntOrNull(ps, 3, weight);
            ps.setTimestamp(4, (w_time != null) ? w_time: null);
            ps.setTimestamp(5, (w_time != null) ? new Timestamp(System.currentTimeMillis()) : null);

            setIntOrNull(ps, 6, bps);
            ps.setTimestamp(7, (bps_time != null) ? bps_time: null);
            ps.setTimestamp(8, (bps_time != null) ? new Timestamp(System.currentTimeMillis()) : null);

            setIntOrNull(ps, 9, bpd);
            ps.setTimestamp(10, (bpd_time != null) ? bpd_time: null);
            ps.setTimestamp(11, (bpd_time != null) ? new Timestamp(System.currentTimeMillis()) : null);

            setIntOrNull(ps, 12, oxygen);
            ps.setTimestamp(13, (oxygen_time != null) ? oxygen_time: null);
            ps.setTimestamp(14, (oxygen_time != null) ? new Timestamp(System.currentTimeMillis()) : null);

            setIntOrNull(ps, 15, pain);
            ps.setTimestamp(16, (pain_time != null) ? pain_time: null);
            ps.setTimestamp(17, (pain_time != null) ? new Timestamp(System.currentTimeMillis()) : null);

            ps.setString(18, (mood != null) ? mood : null);
            ps.setTimestamp(19, (mood_time != null) ? mood_time: null);
            ps.setTimestamp(20, (mood_time != null) ? new Timestamp(System.currentTimeMillis()) : null);

            setIntOrNull(ps, 21, temperature);
            ps.setTimestamp(22, (temp_time != null) ? temp_time: null);
            ps.setTimestamp(23, (temp_time != null) ? new Timestamp(System.currentTimeMillis()) : null);

            ps.execute();

        }
    }

    private static boolean enterSpecificRec(Connection con) throws Exception {


        int recid, weight_lower, weight_upper, bps_lower, bps_upper, bpd_lower, bpd_upper, oxygen_lower,oxygen_upper,
                pain, temperature_lower, temperature_upper, temperature_freq, bpd_freq, oxygen_freq, weight_freq, pain_freq, mood_freq , pid;
          String mood;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your desired RECID: ");
        recid = scanner.nextInt();

        System.out.println("Enter WEIGHT (LOWER): ");
        weight_lower = scanner.nextInt();
        System.out.println("Enter WEIGHT (UPPER): ");
        weight_upper = scanner.nextInt();
        System.out.println("Enter WEIGHT (FREQ): ");
        weight_freq = scanner.nextInt();

        System.out.println("Enter BPS (LOWER): ");
        bps_lower = scanner.nextInt();
        System.out.println("Enter BPS (UPPER): ");
        bps_upper = scanner.nextInt();


        System.out.println("Enter BPD (LOWER): ");
        bpd_lower = scanner.nextInt();
        System.out.println("Enter BPS (UPPER): ");
        bpd_upper = scanner.nextInt();
        System.out.println("Enter BPS (FREQ): ");
        bpd_freq= scanner.nextInt();

        System.out.println("Enter Oxygen Saturation (LOWER): ");
        oxygen_lower = scanner.nextInt();
        System.out.println("Enter Oxygen Saturation (UPPER): ");
        oxygen_upper = scanner.nextInt();
        System.out.println("Enter Oxygen Saturation (FREQ): ");
        oxygen_freq = scanner.nextInt();

        System.out.println("Enter pain: ");
       pain = scanner.nextInt();
        System.out.println("Enter pain (FREQ): ");
        pain_freq = scanner.nextInt();

        System.out.println("Enter Temperature (LOWER):  ");
        temperature_lower = scanner.nextInt();
        System.out.println("Enter Temperature (UPPER): ");
        temperature_upper = scanner.nextInt();
        System.out.println("Enter Temperature (FREQ): ");
        temperature_freq = scanner.nextInt();

        System.out.println("Enter Mood {HAPPY, SAD, NEUTRAL}:");
        mood = scanner.next();
        System.out.println("Enter Mood FREQ:");
        mood_freq = scanner.nextInt();

        try {
            ps = con.prepareStatement(SqlQueries.SQL_INSERT_NEW_RECOMMENDATION);
            ps.setInt(1, weight_lower);
            ps.setInt(2, recid);
            ps.setInt(3, weight_upper);
            ps.setInt(4, weight_freq);
            ps.setInt(5, bps_lower);
            ps.setInt(6, bps_upper);
            ps.setInt(7, bpd_lower);
            ps.setInt(8, bpd_upper);
            ps.setInt(9, bpd_freq);
            ps.setInt(10, oxygen_lower);
            ps.setInt(11, oxygen_upper);
            ps.setInt(12, oxygen_freq);
            ps.setInt(13, pain);
            ps.setInt(14, pain_freq);
            ps.setInt(15, temperature_lower);
            ps.setInt(16, temperature_upper);
            ps.setInt(17, temperature_freq);
            ps.setString(18, mood);
            ps.setInt(19, mood_freq);

            ps.execute();
            System.out.println("Enter Patient PID to add special recommendation: ");
            pid = s.nextInt();
            ps = con.prepareStatement(SqlQueries.SQL_SET_PATIENT_SPECIAL_REC);
            ps.setInt(1, recid);
            ps.setInt(2, pid);
            ps.execute();

            System.out.println("Special recommendation added!: ");
            /*    ps = con.prepareStatement(SqlQueries.SQL_INSERT_PATIENT_REC_TABLE);
                ps.setInt(2, recid);

                ps.execute();*/


            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null) try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public static void setIntOrNull(PreparedStatement psmt, int column, int value) throws Exception
    {
        if (value != 0) {
            psmt.setInt(column, value);
        } else {
            psmt.setNull(column, java.sql.Types.INTEGER);
        }
    }


}