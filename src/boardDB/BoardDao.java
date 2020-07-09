package boardDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {

	private Connection conn;    //DB Ŀ�ؼ�(����) ��ü
    private static final String USERNAME = "root";   //DB ���ӽ� ID
    private static final String PASSWORD = "1234";    //DB ���ӽ� �н�����
    //DB���� ���(��Ű��=�����ͺ��̽���)����
    
    //8���� ���� URL �ڵ�
//  private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    
    //8���� �̻� ����
    private static final String URL = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false"; 
 
    //������
    public BoardDao() {
        // connection��ü�� �����ؼ� DB�� ������.
        try {
            System.out.println("������");
           //���� ��ü�� ������� 
// 8���� ���� ���� ��� ���� ���� �� ���� ���� �κ�
// Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
//          Class.forName("com.mysql.jdbc.Driver");
            
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("����̹� �ε� ����!!");
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("����̹� �ε� ����!!");
        }
    }    
    
    //DB�� �����͸� �����ϴ� �޼���
    public void insertStudent(Board board) {
        //������ �غ�
       String sql = "insert into board values(?,?,?,?,?,?);";
        //DB�� ���� �־��ִ� Ŭ����(���� StatementŬ������ �Ͽ�����,
       //����ϱⰡ �� �������� ��ġ�ʾƼ�, PreparedStatementŬ������
       //���� ����Ѵ�.
       PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, board.getId());   //ù ��° ? ����
            pstmt.setString(2, board.getBorderTitle()); //�� ��° ? ����
            pstmt.setString(3, board.getBorderPassword());//�� ��° ? ����
            pstmt.setString(4, board.getComboPublic());
            pstmt.setString(5, board.getWriterName());
            pstmt.setString(6, board.getTextContent());
            //������ �����϶�.
            pstmt.executeUpdate();
            System.out.println("Student������ ���� ����!");
        } catch (SQLException e) {            
           System.out.println("Student������ ���� ����!");
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {                
                e.printStackTrace();
            }
        }
    }
    
 
    public void updateStudent(Board board) {
        String sql = "update board set name=?, grade=? where id = ?;";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, board.getId());   //ù ��° ? ����
            pstmt.setString(2, board.getBorderTitle()); //�� ��° ? ����
            pstmt.setString(3, board.getBorderPassword());//�� ��° ? ����
            pstmt.setString(4, board.getComboPublic());
            pstmt.setString(5, board.getWriterName());
            pstmt.setString(6, board.getTextContent());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //���ǿ� �´� ���� DB���� �����ϴ� �޼���
    public void deleteStudent(String id) {
        String sql = "delete from board where id = ?;";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //���ǿ� �´� ���� DB���� 1�� �ุ �������� �޼���
    public Board selectOne(String id) {
        String sql = "select * from board where id = ?;";
        PreparedStatement pstmt = null;
        int nid = Integer.parseInt(id);
        Board re = new Board();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, nid);
           // pstmt.setInt(2, nid);  //and ������ ���� ������ �߰��Ѵ�. 
            ResultSet rs = pstmt.executeQuery();
            //select�� ����� ResultSet�� ��� ���ϵȴ�.
            if (rs.next()) {  //������ ���� ������ true, ������ false
                re.setId(rs.getInt("id"));
                re.setBorderTitle(rs.getString("borderTitle"));
                re.setBorderPassword(rs.getString("borderPassword"));
                re.setComboPublic(rs.getString("comboPublic"));
                re.setWriterName(rs.getString("writerName"));
                re.setTextContent(rs.getString("textContent"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return re;
    }
    //student���̺� �����ϴ� ��� ���� �������� �޼�����
    public List<Board> selectAll() {
       
       String sql = "select * from board;";
        PreparedStatement pstmt = null; 
        List<Board> list = new ArrayList<Board>();
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet re = pstmt.executeQuery();
 
            while (re.next()) {   //�����ð� �ִ���?
            	Board s = new Board();
//                s.setCODE(re.getString("CODE"));
//                s.setCNAME(re.getString("CNAME"));
//                s.setGrade(re.getString("grade"));
                list.add(s);   //List<Student>���ٰ� �߰���.
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
