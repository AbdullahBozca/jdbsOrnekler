import java.sql.*;

public class Jdbc1Query02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");
        Statement st = con.createStatement();
        /*=======================================================================
		 ORNEK1: Bolumler tablosundaki tum kayitlari listeleyen bir sorgu yaziniz.
		========================================================================*/
        ResultSet rs = st.executeQuery("select * from bolumler");
        System.out.println("ORNEK1: Bolumler tablosundaki tum kayitlari listeleyen bir sorgu yaziniz.");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " : " + rs.getString("bolum_isim") + " " + rs.getString(3));
        }
        System.out.println("=====================================");
        /*=======================================================================
	     ORNEK2: SATIS ve MUHASEBE bolumlerinde calisan personelin isimlerini ve
	    maaslarini, maas ters sirali olarak listeleyiniz
	    ========================================================================*/
        System.out.println("ORNEK2: SATIS ve MUHASEBE bolumlerinde calisan personelin isimlerini ve maaslarini, maas ters sirali olarak listeleyiniz");
        rs = st.executeQuery("select  isim,maas from personel where bolum_id in(10,30) order by maas desc ");
        while (rs.next()) {
            System.out.println(rs.getString("isim") + " : \t" + rs.getInt(2));
        }
        System.out.println("=====================================");

        /*=======================================================================
	    ORNEK3: Tüm bolumlerde calisan personelin isimlerini, bolum isimlerini ve maaslarini, bolum ve maas sirali listeleyiniz. NOT: calisani olmasa bile bolum ismi gosterilmelidir.
	    ========================================================================*/
        System.out.println("ORNEK3: Tüm bolumlerde calisan personelin isimlerini, bolum isimlerini ve maaslarini, bolum ve maas sirali listeleyiniz. NOT: calisani olmasa bile bolum ismi gosterilmelidir.");
        rs = st.executeQuery("select b.bolum_isim,p.isim,p.maas from bolumler b left join personel p on p.bolum_id=b.bolum_id order by b.bolum_isim,p.maas");
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " : " + rs.getInt(3));
        }
        System.out.println("=====================================");
        /*=======================================================================
	     ORNEK4: Maasi en yuksek 10 kisinin bolumunu,adini ve maasini listeyiniz
	    ========================================================================*/
        System.out.println("ORNEK4: Maasi en yuksek 10 kisinin bolumunu,adini ve maasini listeyiniz");
        rs = st.executeQuery("select b.bolum_isim,p.isim,p.maas from personel p left join bolumler b on p.bolum_id=b.bolum_id order by p.maas desc limit 10");
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " : " + rs.getInt(3));
        }
        System.out.println("=====================================");

        con.close();
        st.close();
        rs.close();
    }
}
