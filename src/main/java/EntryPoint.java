import javax.sql.DataSource;
import javax.swing.plaf.nimbus.State;

import com.aboelsyeed.jdbc.*;

import java.sql.*;

public class EntryPoint {
    public static void main(String[] args) {
        String create_table = "CREATE TABLE `employee2` (\n" +
                "  `id` INT NOT NULL,\n" +
                "  `F_name` VARCHAR(20) NULL,\n" +
                "  `L_name` VARCHAR(20) NULL,\n" +
                "  `Sex` VARCHAR(1) NULL,\n" +
                "  `Address` VARCHAR(45) NULL,\n" +
                "  `Phone_Number` VARCHAR(11) NULL,\n" +
                "  `Age` INT NULL,\n" +
                "  `Vacation_Balance` INT NULL,\n" +
                "  PRIMARY KEY (`id`));\n";

        String insert_into_table= "INSERT INTO employee2 VALUES (?,?,?,?,?,?,?,?);";
        String drop_table ="DROP TABLE employee2;";
        String check_table = "SELECT COUNT(*)\n" +
                "FROM information_schema.tables \n" +
                "WHERE table_schema = 'newschema' \n" +
                "AND table_name = 'employee2';";

        DataSource ds = MyDataSourceFactory.getInstanace();
        Connection conn= null;
        PreparedStatement pst =null;
        try {
            conn = ds.getConnection();

            pst = conn.prepareStatement(check_table);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                pst = conn.prepareStatement(drop_table);
                pst.executeUpdate();
                System.out.println("table will be dropped, then recreated");
            }
            pst = conn.prepareStatement(create_table);
            pst.executeUpdate();
            pst = conn.prepareStatement(insert_into_table);
            insertData(pst,100,"ahmed","Elsayed","M","Egypt","0100111",30,30);
            insertData(pst,101,"ahmed","Mohamed","M","Egypt","0100111",20,30);
            insertData(pst,102,"Gamal","Ahmed","M","Egypt","0100111",47,45);
            insertData(pst,103,"Nadya","Elsayed","F","Egypt","0100111",49,45);
            insertData(pst,104,"Sally","Salah","F","Egypt","0100111",23,30);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public static void insertData(PreparedStatement pst,Integer Id, String fname, String lname, String sex,String address,  String phone,Integer age, Integer vacationBalance) throws SQLException {
        pst.setInt(1,Id);
        pst.setString(2,fname);
        pst.setString(3,lname);
        pst.setString(4,sex);
        pst.setString(5,address);
        pst.setString(6,phone);
        pst.setInt(7,age);
        pst.setInt(8,vacationBalance);
        pst.executeUpdate();
    }
}
