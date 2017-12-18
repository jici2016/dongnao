package rizhi2db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;

import org.junit.Test;

/**
 *
 * @Package: rizhi2db
 * @author liuming
 * @date 2017ƒÍ12‘¬15»’
 *
 */
public class Rizhi2Db {
	public static void main1(String[] args) {
		FileInputStream fis = null;
		BufferedReader br = null;
		
		try {
			fis = new FileInputStream("E:/12-15-01.log");
			StringBuffer sb = new StringBuffer();
			br = new BufferedReader(new InputStreamReader(fis, "utf-8"));
			String line = null;
			while((line=br.readLine())!=null) {
				System.out.println(line);
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void execute(){
		String l="°æinsert into g_list_12(state,statemsg,appname,subject,imsi,imei,callbackdata,rectime,fee ,spnumber,mo,spcodeid,spid,cpid,servicetype,rectype,itemid,orderid,linkid)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);?[null, null, ≤∂”„√»√»ﬂ’, ¿Ò∞¸, 460030927772060, 99000660393358, XgUEeTQdNyPN, 2017-12-15 01:58:40, 20, null, null, 17, 0007, 114, 3, 0, 1721, 171215pjBRfJi3PA, null]°ø\r\n";
		System.out.println(l);
		l=l.replace("°æ","").replace("°ø", "");
		System.out.println(l);
		String[]ls=l.split(";\\?");
		String sql=ls[0];
		String params=ls[1].replace("[","").replace("]", "");;
		System.out.println(sql);
		System.out.println(params);
	}
	
	@Test
	public void insert() {
		String sql="insert into g_list_12_bak(state,statemsg,appname,subject,imsi,imei,callbackdata,rectime,fee ,spnumber,mo,spcodeid,spid,cpid,servicetype,rectype,itemid,orderid,linkid)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String params="null, null, ≤∂”„√»√»ﬂ’, ¿Ò∞¸, 460030927772060, 99000660393358, XgUEeTQdNyPN, 2017-12-15 01:58:40, 20, null, null, 17, 0007, 114, 3, 0, 1721, 171215pjBRfJi3PA, null";
		String[]ps=params.split(",");
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String password="TZYK123#@!";
		String user="tzyk";
		String url="jdbc:mysql://****:*/*?useUnicode=true&&characterEncoding=UTF-8";
		try {
			conn=DriverManager.getConnection(url, user, password);
			System.out.println(conn);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < ps.length; i++) {
				if(ps[i]=="null") {
					pstmt.setNull(i+1, Types.VARCHAR);
				}else {
				pstmt.setString(i+1, ps[i]);
				}
			}
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
