package CetaCiemat.DotProject;

import CetaCiemat.Tempo.WorkLog;
import com.mysql.jdbc.Statement;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author csuarez
 */
public class DotProjectClient {

    private UserIdCache cache;
    private Connection conn;
    private String encoding;

    public DotProjectClient(String url, String user, String password, String encoding) throws SQLException {
        this.cache = new UserIdCache();
        conn = DriverManager.getConnection(url, user, password);
        this.encoding = encoding;

    }

    public Integer getUserId(String username) throws ClassNotFoundException, SQLException, NoUserException {
        if (cache.containsKey(username)) {
            return cache.get(username);
        }

        String query = "SELECT user_id FROM users where user_username = '" + username + "'";
        Statement st = (Statement) conn.createStatement();

        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            cache.put(username, rs.getInt("user_id"));
            return rs.getInt("user_id");
        } else {
            throw new NoUserException("The user doesn't exist in DotProject!");
        }
    }

    public void addLogs(ArrayList<WorkLog> logs) throws ClassNotFoundException, SQLException, Exception {
        String insertString =
                "INSERT INTO task_log "
                + "(task_log_task, task_log_description, task_log_creator, task_log_hours, task_log_date, task_log_name)"
                + "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement insertLog = null;

        try {
            conn.setAutoCommit(false);
            insertLog = conn.prepareStatement(insertString);

            for (WorkLog log : logs) {
                if (log.getDotProjectTaskId() != null) {
                    insertLog.setInt(1, log.getDotProjectTaskId());
                    insertLog.setString(2, new String(log.getWorkDescription().getBytes("UTF-8"), Charset.forName(encoding)));
                    insertLog.setInt(3, this.getUserId(log.getUsername()));
                    insertLog.setFloat(4, log.getHours().floatValue());
                    insertLog.setDate(5, new Date(log.getWorkDate().getTime()));
                    insertLog.setString(6, new String(log.getIssueKey().getBytes("UTF-8"), Charset.forName(encoding)));
                    insertLog.executeUpdate();
                }
            }
            conn.commit();
        } catch (Exception ex) {
            Logger.getLogger(DotProjectClient.class.getName()).log(Level.SEVERE, "Error during the DotProject import!", ex);
            if (conn != null) {
                try {
                    Logger.getLogger(DotProjectClient.class.getName()).log(Level.INFO, "The transction is being rolled back!");
                    conn.rollback();
                } catch (SQLException excep) {
                    Logger.getLogger(DotProjectClient.class.getName()).log(Level.SEVERE, "The transction can't be rolled back!", excep);
                }
            }
            throw ex;
        } finally {
            if (insertLog != null) {
                insertLog.close();
            }
            conn.setAutoCommit(true);
        }

    }
}
