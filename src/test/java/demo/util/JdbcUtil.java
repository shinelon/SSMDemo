package demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.apache.commons.collections.map.LinkedMap;

public class JdbcUtil {
    public static JdbcUtil getInstance(String url, String userName, String password) {
        return new JdbcUtil(url, userName, password);
    }

    private String url;
    private String username;

    private String password;

    private JdbcUtil(String url, String username, String password) {
        super();
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void close(ResultSet rs, Statement stmt, Connection conn) throws SQLException {
        if (rs != null) {
            rs.close();
            rs = null;
        }
        if (stmt != null) {
            stmt.close();
            stmt = null;
        }
        conn.close();
    }

    public int executeQuery(String sql) throws SQLException {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        int r = 0;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                r = rs.getInt(1);
            }
        } finally {
            close(rs, stmt, connection);
        }
        return r;
    }

    public List<Map<String, Object>> executeResult(String sql, Object... args) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            if (args != null) {
                for (int i = 0, length = args.length; i < length; i++) {
                    pstmt.setObject(i + 1, args[i]);
                }
            }
            rs = pstmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int cols = metaData.getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<String, Object>();
                for (int i = 1; i <= cols; i++) {
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                }
                result.add(row);
            }
            return result;
        } finally {
            close(rs, pstmt, conn);
        }
    }

    public int executeSQL(String preparedSql, Object... args) throws SQLException {
        PreparedStatement pstmt = null;
        Connection connection = null;
        int r = 0;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(preparedSql);
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    pstmt.setObject(i + 1, args[i]);
                }
            }
            r = pstmt.executeUpdate();
        } finally {
            close(null, pstmt, connection);
        }
        return r;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}
