import java.sql.*;

public class Jdbc1Query01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // ilgili driveri yüklemelyiz
        Class.forName("com.mysql.cj.jdbc.Driver");
        // bağlantı oluşturmalıyız
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");
        // sql komutları için bir statement nesnesi oluşturur.
        Statement st = con.createStatement();
        //sql ifadeleri yazabilir ve çalıştırabiliriz
        ResultSet veri = st.executeQuery("SELECT isim, maas FROM personel WHERE id=123456789");
        // sonuçları aldık ve işledik
        while (veri.next()) {
            System.out.println(veri.getString("isim") + veri.getInt("maas"));
        }
        con.close();
        st.close();
        veri.close();

    }
}
