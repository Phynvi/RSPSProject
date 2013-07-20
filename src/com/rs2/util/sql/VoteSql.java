package com.rs2.util.sql;

import java.sql.*;
import java.security.MessageDigest;
import java.util.*;
import java.util.Date;
import java.text.*;

import com.rs2.Constants;
import com.rs2.model.World;
import com.rs2.model.players.Player;
import com.rs2.util.Misc;

public class VoteSql {

	public static Connection con = null;
	public static Statement stm;

	public static Connection con2 = null;
	public static Statement stm2;
	public static Connection conOSS = null;
	public static Statement stmOSS;


	public static void createConnectionOSS() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conOSS = DriverManager.getConnection("jdbc:mysql://"+Constants.SQLHost+"/accounts","oss","NUIDSfnu7387ca");
			stmOSS = conOSS.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static boolean isGreenDon(String playerName)
	{
		try {
			String query = "SELECT * FROM level1 WHERE username = '" + playerName.toLowerCase() + "'";
			ResultSet results = stmOSS.executeQuery(query);
			while(results.next()) {
				String username = results.getString("username");
				if(username.equals(playerName.toLowerCase()))
				{
					if (results != null) {
						results.close();
					}
				return true;
				}
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean isBlueDon(String playerName)
	{
		try {
			String query = "SELECT * FROM level2 WHERE username = '" + playerName.toLowerCase() + "'";
			ResultSet results = stmOSS.executeQuery(query);
			while(results.next()) {
				String username = results.getString("username");
				if(username.equals(playerName.toLowerCase()))
				{
					if (results != null) {
						results.close();
					}
				return true;
				}
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean isRedDon(String playerName)
	{
		try {
			String query = "SELECT * FROM level3 WHERE username = '" + playerName.toLowerCase() + "'";
			ResultSet results = stmOSS.executeQuery(query);
			while(results.next()) {
				String username = results.getString("username");
				if(username.equals(playerName.toLowerCase()))
				{
					if (results != null) {
						results.close();
					}
				return true;
				}
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean isQuest(String playerName)
	{
		try {
			String query = "SELECT * FROM quest WHERE username = '" + playerName.toLowerCase() + "'";
			ResultSet results = stmOSS.executeQuery(query);
			while(results.next()) {
				String username = results.getString("username");
				if(username.equals(playerName.toLowerCase()))
				{
				if (results != null) {
						results.close();
					}
				return true;
				}
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}	
	public static boolean isPet(String playerName)
	{
		try {
			String query = "SELECT * FROM pets WHERE username = '" + playerName.toLowerCase() + "'";
			ResultSet results = stm.executeQuery(query);
			while(results.next()) {
				String username = results.getString("username");
				if(username.equals(playerName.toLowerCase()))
				{
				if (results != null) {
						results.close();
					}
				return true;
				}
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}	
	public static boolean hasDrops(String playerName)
	{
		try {
			String query = "SELECT * FROM drops WHERE username = '" + playerName.toLowerCase() + "'";
			ResultSet results = stm.executeQuery(query);
			while(results.next()) {
				String username = results.getString("username");
				if(username.equals(playerName.toLowerCase()))
				{
				if (results != null) {
						results.close();
					}
				return true;
				}
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}	
	public static boolean modDay(String playerName)
	{
		try {
			String query = "SELECT * FROM modDay WHERE username = '" + playerName.toLowerCase() + "'";
			ResultSet results = stm.executeQuery(query);
			while(results.next()) {
				String username = results.getString("username");
				if(username.equals(playerName.toLowerCase()))
				{
				if (results != null) {
						results.close();
					}
				return true;
				}
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}	
	public static void Unban(String playerName)
	{
		try {
			String query = "DELETE FROM banned WHERE username = '" + playerName.toLowerCase() + "' AND bannedBy != 'agentjags' AND bannedBy != 'duel bug abuse'";
		query(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}
		public static void unMAC(String playerName)
	{
		try {
			String query = "DELETE FROM macbans WHERE mac = '" + playerName.toLowerCase() + "' AND bannedBy != 'agentjags' AND bannedBy != 'chachi' AND bannedBy != 'zeroeh'";
		query(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}
	public static void unIPMUTE(String playerName)
	{
		try {
			String query = "DELETE FROM ipmutes WHERE ip = '" + playerName.toLowerCase() + "' AND bannedBy != 'agentjags'";
		query(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}
		public static void RemoveTop(String playerName)
	{
		try {
			String query = "DELETE FROM toppker WHERE name LIKE '" + playerName.toLowerCase() + "' LIMIT 1";
		query(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}

		}
	public static void RemoveDonator(String playerName)
	{
		try {
			String query = "DELETE FROM donators WHERE username LIKE '" + playerName.toLowerCase() + "' LIMIT 1";
		query(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}
	public static void unIP(String playerName)
	{
		try {
			String query = "DELETE FROM ipbans WHERE ip= '" + playerName.toLowerCase() + "' AND bannedBy != 'agentjags' AND bannedBy != 'chachi' AND bannedBy != 'zeroeh'";
		query(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}
	public static boolean isBanned(String playerName)
	{
		try {
			String query = "SELECT * FROM banned WHERE username = '" + playerName.toLowerCase() + "'";
			ResultSet results = stm.executeQuery(query);
			while(results.next()) {
				String username = results.getString("username");
				if(username.equals(playerName.toLowerCase()))
				{
				if (!username.equals("garak")) return true;
				}
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}
	public static boolean checkIP(String playerName)
	{
		try {
			String query = "SELECT * FROM ipbans WHERE ip = '" + playerName.toLowerCase() + "'";
			ResultSet results = stm.executeQuery(query);
			while(results.next()) {
				String username = results.getString("ip");
				if(username.equals(playerName.toLowerCase()))
				{
				if (results != null) {
					results.close();
				}
				return true;
				}
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}
	public static boolean checkIPMUTE(String playerName)
	{
		try {
			String query = "SELECT * FROM ipmutes WHERE ip = '" + playerName.toLowerCase() + "'";
			ResultSet results = stm.executeQuery(query);
			while(results.next()) {
				String username = results.getString("ip");
				if(username.equals(playerName.toLowerCase()))
				{
				if (results != null) {
					results.close();
				}
				return true;
				}
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}
	
	public static boolean checkMAC(String playerName)
	{
		try {
			String query = "SELECT * FROM macbans WHERE mac = '" + playerName.toLowerCase() + "'";
			ResultSet results = stm.executeQuery(query);
			while(results.next()) {
				String username = results.getString("mac");
				if(username.equals(playerName.toLowerCase()))
				{
				if (results != null) {
					results.close();
				}
				return true;
				}
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}
	public static void banUser(String playerName, String banner)
	{
		try {
			String query = "INSERT INTO banned (username, bannedBy,date) VALUES('" + playerName.toLowerCase() + "','"+banner.toLowerCase()+"',now())";
			query(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static boolean insertNewChar(String playerName, String playerPass, String salt)
	{
		try {
			String s = "INSERT INTO accounts (username, password,salt) VALUES('" + playerName.toLowerCase() + "','"+playerPass+"','"+salt+"')";
			stmOSS.executeUpdate(s);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	public static void giveTitle(String playerName,String title){
		try
		{
			String query = "INSERT INTO title (username, title) VALUES ('" + playerName.toLowerCase() + "','"+title+"')";
			query(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void duelBugLog(String playerName, int itemid)
	{
		try {
			String query = "INSERT INTO duelbug(username,itemid,date) VALUES('" + playerName.toLowerCase() + "','"+itemid+"',now())";
			query(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
public static int getPM(String playerName)
	{
		try {
			String query = "SELECT * FROM storage WHERE name = '" + playerName.toLowerCase() + "'";
			ResultSet results = stm.executeQuery(query);
			while(results.next()) {
				int world = results.getInt("pmstatus");
				String username = results.getString("name");
				if(username.equals(playerName.toLowerCase()))
				{
					//System.out.println(username + " - " + world);
					if (results != null) {
						results.close();
					}
					return world;
				}
			}
				
				
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static String customTitle(String playerName)
	{
		try {
			String query = "SELECT * FROM title WHERE username = '" + playerName.toLowerCase() + "'";
			ResultSet results = stm.executeQuery(query);
			while(results.next()) {
				String title = results.getString("title");
				if(title.length() > 0)
				{
					//System.out.println(username + " - " + world);
					if (results != null) {
						results.close();
					}
					return title;
				}
			}
				
				return "";
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
			return "";
		
	}
	public static String getSalt(String playerName)
	{
		try {
			String query = "SELECT salt FROM accounts WHERE username = '" + playerName.toLowerCase() + "'";
			ResultSet results = stmOSS.executeQuery(query);
			while(results.next()) {
				String salt = results.getString("salt");
				if(salt.length() > 0)
				{
					//System.out.println(username + " - " + world);
					if (results != null) {
						results.close();
					}
					return salt;
				}
			}
				
				return "";
			
		} catch(SQLException e) {
			e.printStackTrace();
			return "ERROR";
		}
		
	}
	public static boolean compareInfo(String playerName, String playerPass)
	{
		try {
			String query = "SELECT * FROM accounts WHERE username = '" + playerName.toLowerCase() + "'";
			ResultSet results = stmOSS.executeQuery(query);
			while(results.next()) {
				String password = results.getString("password");
				if(password.length() > 0)
				{
					//if (results != null) {
					//	results.close();
					//}
					if (password.equalsIgnoreCase(playerPass)) {
						return true; 
					}
					return false;
				}
				return false;
			}
				return false;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
			return false;
	}
	public static boolean playerExists(String playerName)
	{
		try {
			String query = "SELECT * FROM accounts WHERE username = '" + playerName.toLowerCase() + "'";
			ResultSet results = stmOSS.executeQuery(query);
			while(results.next()) {
				String username = results.getString("username");
				if(username.equals(playerName.toLowerCase()))
				{
				if (results != null) {
						results.close();
					}
				return true;
				}
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}	
	public static int getSWorld(String playerName)
	{
		try {
			String query = "SELECT * FROM storage WHERE name = '" + playerName.toLowerCase() + "'";
			ResultSet results = stm.executeQuery(query);
			while(results.next()) {
				int world = results.getInt("world");
				String username = results.getString("name");
				if(username.equals(playerName.toLowerCase()))
				{
					//System.out.println(username + " - " + world);
					if (results != null) {
						results.close();
					}
					return world;
				}
			}
				
				
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static boolean staffOnline(String playerName)
	{
		try {
			String query = "SELECT * FROM storage WHERE name = '" + playerName.toLowerCase() + "'";
			ResultSet results = stm.executeQuery(query);
			while(results.next()) {
				String username = results.getString("name");
				if(username.equals(playerName.toLowerCase()))
				{
				if (results != null) {
					results.close();
				}
				return true;
				}
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}
	public static boolean checkTop(String playerName)
	{
		try {
			String query = "SELECT * FROM toppker WHERE name = '" + playerName.toLowerCase() + "'";
			ResultSet results = stm.executeQuery(query);
			while(results.next()) {
				String username = results.getString("name");
				if(username.equals(playerName.toLowerCase()))
				{
				if (results != null) {
					results.close();
				}
				return true;
				}
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}
	public static void insertTop(String playerName){
	try {
			String query = "INSERT INTO toppker(name,received) VALUES('" + playerName.toLowerCase() + "','1')";
			query(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void tradeBugLog(String playerName, String playerName2)
	{
		try {
			String query = "INSERT INTO tradebug(username,with,date) VALUES('" + playerName.toLowerCase() + "','"+playerName2.toLowerCase()+"',now())";
			query(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void duelDupeLog(String playerName)
	{
		try {
			String query = "INSERT INTO dbans(name,reason,date) VALUES('" + playerName.toLowerCase() + "','DUEL DUPE',now())";
			query(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static void banIP(String playerName, String banner)
	{
		try {
			String query = "INSERT INTO ipbans (ip, bannedBy,date) VALUES('" + playerName.toLowerCase() + "','"+banner.toLowerCase()+"',now())";
			query(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void banIPMUTE(String playerName, String banner)
	{
		try {
			String query = "INSERT INTO ipmutes (ip, bannedBy,date) VALUES('" + playerName.toLowerCase() + "','"+banner.toLowerCase()+"',now())";
			query(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void banMAC(String playerName, String banner)
	{
		try {
			String query = "INSERT INTO macbans (mac, bannedBy,date) VALUES('" + playerName.toLowerCase() + "','"+banner.toLowerCase()+"',now())";
			query(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static ResultSet query(String s) throws SQLException {
		try {
			if (s.toLowerCase().startsWith("select")) {
				ResultSet rs = stm.executeQuery(s);
				if (rs != null) {
					rs.close();
				}
				return rs;
			} else {
				stm.executeUpdate(s);
			}
			return null;
		} catch (Exception e) {
			System.out.println("MySQL Error:"+s);
			e.printStackTrace();
		}
		return null;
	}

	public static boolean query2(String s) throws SQLException {
		try {
				stm.executeUpdate(s);
				return false;
			} catch (Exception e) {
		   return true;
	
	}
}
	public static void destroyCon() {
		try {
			stm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// world 2
	public static boolean getWorld(String playerName){
		try {
			Statement statement = con.createStatement();
			String query = "SELECT world FROM storage WHERE name = '" + playerName.toLowerCase() + "'";
			ResultSet results = statement.executeQuery(query);
			while(results.next()) {
				int world = results.getInt("world");
				if (statement != null) {
					statement.close();
				}
				if (results != null) {
					results.close();
				}
				return true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return true;
		}
		return false; // default, hasn't logged in.
	}
	public static void deletePlayer(String playerName){
		try
		{
		
			query("DELETE FROM storage WHERE name = '" +  playerName.toLowerCase() + "' LIMIT 1");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static boolean updateStuff(String playerName,int world,int status1){
	try {
		if (!query2("INSERT INTO storage (name,world,pmstatus) VALUES('" + playerName.toLowerCase() + "', '" + world + "', '" + status1 +"')")) {
		return false;
		}
		return true;		
		}
		catch(SQLException z)
		{
			Player player = World.getPlayerByName(playerName);
	        if (player == null)
	            return false;
	        player.disconnect();
			return true;
		}
		/*catch(SQLException e) {
		System.out.println("hello world");
			e.printStackTrace();
			Server.getPlayerManager().kick(playerName);
			return true;
		}*/
	}
	
	public static boolean checkVotes(String playerName)
	{
		try {
			Statement statement = con.createStatement();
			String query = "SELECT * FROM Votes WHERE username = '" + playerName + "'";
			ResultSet results = statement.executeQuery(query);
			while(results.next()) {
				int received = results.getInt("received");
				if(received == 0)
				{
					if (statement != null) {
					statement.close();
				}
				if (results != null) {
					results.close();
				}
				return true;
				}
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/* some crazy shit i hope works :P */
		public static boolean checkItem(String playerName) //check if item has been delivered
	{
		try {
			Statement statement = con.createStatement();
			String query = "SELECT * FROM items WHERE username = '" + playerName + "'";
			ResultSet results = statement.executeQuery(query);
			while(results.next()) {
				int received = results.getInt("received");
				if(received == 0)
				{
				if (statement != null) {
					statement.close();
				}
				if (results != null) {
					results.close();
				}
				return true;
				}
				
			}
				if (statement != null) {
					statement.close();
				}
				if (results != null) {
					results.close();
				}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
		public static String[] getItemIDFromServ(String playerName) //get the id of the item to give 
	{
		try {
			Statement statement = con.createStatement();
			String query = "SELECT * FROM items WHERE username = '" + playerName + "'";
			ResultSet results = statement.executeQuery(query);
			while(results.next()) {
				String itemid = results.getString("itemid");
				String[] itemid2 = itemid.split(",");
					if (statement != null) {
					statement.close();
				}
				if (results != null) {
					results.close();
				}
			return itemid2;
			}
				if (statement != null) {
					statement.close();
				}
				if (results != null) {
					results.close();
				}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String[] getItemAmtFromServ(String playerName) //get the amt of the item to give 
	{
		try {
			Statement statement = con.createStatement();
			String query = "SELECT * FROM items WHERE username = '" + playerName + "'";
			ResultSet results = statement.executeQuery(query);
			while(results.next()) {
				String itemamt = results.getString("itemamt");
				String[] itemamt2 = itemamt.split(",");
					if (statement != null) {
					statement.close();
				}
				if (results != null) {
					results.close();
				}
			return itemamt2;
			}
				if (statement != null) {
					statement.close();
				}
				if (results != null) {
					results.close();
				}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static int getTeamCaptain(String pname){
	try {
				Statement statement = con.createStatement();
				String query = "SELECT * FROM blteams where tcapt='"+pname+"'";
				ResultSet results = statement.executeQuery(query);
				while(results.next()) {
						return results.getInt("tid");
				}
		} catch (Exception e) {
		
			return -1;
		}	
		return -1;
	}
	public static int getTeamCapt(String pname){
	try {
				Statement statement = con.createStatement();
				String query = "SELECT * FROM blteams where tname='"+pname+"'";
				ResultSet results = statement.executeQuery(query);
				while(results.next()) {
						return results.getInt("tid");
				}
		} catch (Exception e) {
		
			return -1;
		}	
		return -1;
	}
public static boolean blDeletePlayer(String name){
	try {
			String query = "Delete from blplayer where pname ='"+name+"'";
			query(query);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static int getTeamAmount(int id){
		int counter = 0;
		try {
				Statement statement = con.createStatement();
				String query = "SELECT * FROM blplayers where tid='"+id+"'";
				ResultSet results = statement.executeQuery(query);
			while(results.next()) {
						counter++;
			}
		} catch (Exception e) {
		
			return 0;
		}	
		return counter;
	}
	public static int getTeam(String name){
		try {
				Statement statement = con.createStatement();
				String query = "SELECT * FROM blteams where tname='"+name+"'";
				ResultSet results = statement.executeQuery(query);
			while(results.next()) {
						return results.getInt("tid");
					}
				return -1;
		} catch (Exception e) {
		
			return -1;
		}	
	}
	
	public static int getTeamID(String name){
		try {
				Statement statement = con.createStatement();
				String query = "SELECT * FROM blplayer";
				ResultSet results = statement.executeQuery(query);
			while(results.next()) {
					if(results.getString("pname").equalsIgnoreCase(name)){
						if (statement != null) {
							statement.close();
						}
						if (results != null) {
							results.close();
						}
						return results.getInt("tid");
					}
				}
				if (statement != null) {
					statement.close();
				}
				if (results != null) {
					results.close();
				}
				return -1;
		} catch (Exception e) {
		
			return -1;
		}	
	}
	
	public static int getPlayerID(String name){
		try {
				Statement statement = con.createStatement();
				String query = "SELECT * FROM blplayer where pname='"+name+"'";
				ResultSet results = statement.executeQuery(query);
			while(results.next()) {
					if(results.getString("pname").equalsIgnoreCase(name)){
						return results.getInt("tid");
					}
				}
				return -1;
		} catch (Exception e) {
			return -1;
		}	
	}
	
	public static void updateTeam(int tid,int rating,int kills,int deaths){
		try{
			query("update blteams set trating='"+rating+"',tkills=tkills+'"+kills+",tdeaths=tdeaths + '"+deaths+"' where tid='"+tid+"'");
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean updatePassword(String playerName, String playerPass){
		try{
			String s = "update accounts set password='"+playerPass+"' where username='"+playerName+"'";
			stmOSS.executeUpdate(s);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void updatePlayer(int tid,int rating,int kills,int deaths){
		try{
			query("update blplayer set prating='"+rating+"',pkills=tkills+'"+kills+",pdeaths=pdeaths + '"+deaths+"' where tid='"+tid+"'");
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** patrick start OMM queue **/
	
		public static boolean inMMQueue(String name){
			try {
				Statement statement = con.createStatement();
				String query = "SELECT * FROM mmqueue";
				ResultSet results = statement.executeQuery(query);
			while(results.next()) {
					if(results.getString("user1").equalsIgnoreCase(name) || results.getString("user2").equalsIgnoreCase(name)){
						if (statement != null) {
					statement.close();
				}
				if (results != null) {
					results.close();
				}
						return true;
					}
				}
				if (statement != null) {
					statement.close();
				}
				if (results != null) {
					results.close();
				}
				return false;
		} catch (Exception e) {

			return false;
		}
		}
	/*public static List<MMStruct> getMMQueue() {
		List<MMStruct> temp = new ArrayList<MMStruct>();
		try {
			Statement statement = con.createStatement();
			String query = "SELECT * FROM mmqueue";
			ResultSet results = statement.executeQuery(query);
			while(results.next()) {
				temp.add(new MMStruct(results.getInt("id"),results.getString("user1"),results.getString("user2"),results.getInt("world"),results.getString("mmtype"),results.getString("datesub")));
			}
			if (statement != null) {
					statement.close();
				}
				if (results != null) {
					results.close();
				}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return temp;
	}*/
	
	public static void insertBLTeam(String blName, String pName, String pIP,String pPass){
		try{
			query("INSERT INTO blteams(tid , tname ,tcapt ,tcaptip ,tkills ,tdeaths ,trating ,tpass , tratio) VALUES (NULL , '"+blName+"', '"+pName+"', '"+pIP+"', '0', '0', '1600', '"+pPass+"', '0');");
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void insertBLPlayer(String pname, int tid){
		try{
			query("INSERT INTO blplayer (pid, pname, pkills, pdeaths, pratio, tid) VALUES (NULL,'"+ pname+"', 0,0,0,"+tid+");");
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int isATeamPlayer(String name) {
		try {
			Statement statement = con.createStatement();
			String query = "SELECT * FROM blplayer where pname ='"+name+"'";
			ResultSet results = statement.executeQuery(query);			
			while (results.next()) {
					return results.getInt("tid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static String getPass(String name){
		try {
				
				Statement statement = con.createStatement();
				String query = "SELECT * FROM blteams where tname ='"+name+"'";
				ResultSet results = statement.executeQuery(query);
				while(results.next()) {
						return results.getString("tpass");
				}
				return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static boolean getIP(String name){
		try {
				
				Statement statement = con.createStatement();
				String query = "SELECT * FROM blteams where tcaptip ='"+name+"'";
				ResultSet results = statement.executeQuery(query);
				while(results.next()) {
						return true;
				}
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void insertMM(String user1, String user2, int world,String type){
		try{
			Date dateNow = new Date();
			SimpleDateFormat dateformat = new SimpleDateFormat(
					"hh:mm:ss");
			StringBuilder date = new StringBuilder(
					dateformat.format(dateNow));
			query("INSERT INTO mmqueue (user1, user2,world,mmtype,datesub) VALUES('" + user1+ "', '" + user2 + "','" + world+ "','" + type + "','" + date.toString()+ "')");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void delMM(int id){
		try{
			 query("DELETE FROM mmqueue WHERE id="+id+";");
		} catch(Exception e){
		
		}
	}
		public static void delMMQueue(){
			try {
		query("TRUNCATE TABLE mmqueue");
		}catch(Exception e){
		}
	}
	public static int isATeam(String name) {
		try {
		Statement statement = con.createStatement();
			String query = "SELECT * FROM blteams where tname ='"+name+"'";
			ResultSet results =  statement.executeQuery(query);
			while (results.next()) {
					return results.getInt("tid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
/*		public static List<MMStaffStruct> getMMStaff() {
		List<MMStaffStruct> temp = new ArrayList<MMStaffStruct>();
		try {
			Statement statement = con.createStatement();
			String query = "SELECT * FROM mmstaff";
			ResultSet results = statement.executeQuery(query);
			while(results.next()) {
				temp.add(new MMStaffStruct(results.getString("playername"),results.getInt("status"),results.getInt("type"),results.getInt("world")));
			}
			if (statement != null) {
					statement.close();
				}
				if (results != null) {
					results.close();
				}
		} catch (Exception e) {
			//e.printStackTrace();

		}
		return temp;
	}
	public static boolean isOMMnline(String name){
				try {
			String query = "SELECT * FROM mmstaff WHERE playername = '" + name + "'";
			ResultSet results = stm.executeQuery(query);
			while(results.next()) {
				String username = results.getString("playername");
				if(username.equals(name))
				{
				if (results != null) {
					results.close();
				}
				return true;
				}
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}
	public static void insertMM(String user1, int status, int type,int world){
		try{
			query("INSERT INTO mmstaff (playername, status,type,world) VALUES('" + user1+ "', '" + status+ "','" + type +"','" + world + "')");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateStaffMM(String user1, int status){
		try{
			query("update mmstaff set status='"+status+"' where playername='"+user1+"'");
			} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public static void delStaff(String id){
		try{
			 query("DELETE FROM mmstaff WHERE playername='"+id+"';");
		} catch(Exception e){
		
		}
	}
		public static boolean itemGiven(String playerName)
	{
		try
		{
			query("UPDATE items SET received = 1 WHERE username = '" + playerName + "'");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean voteGiven(String playerName)
	{
		try
		{
			query("UPDATE Votes SET received = 1 WHERE username = '" + playerName + "'");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
		public static boolean lotteryTicket(String playerName)
	{
		try
		{
			int ticketid = Misc.random(3000000);
			query("INSERT INTO lottery (ticketid, username) VALUES('" + ticketid + "', '" + playerName + "' )");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void deleteVoteTable(){
	try {
		query("TRUNCATE TABLE votecounter");
		}catch(Exception e){
		}
	}
	public static ArrayList<String> getVoteNames() {
		ArrayList<String> temp = new ArrayList<String>();
		try {
			Statement statement = con.createStatement();
			String query = "SELECT * FROM votecounter WHERE id = '0'";
			ResultSet results = statement.executeQuery(query);
			while(results.next()) {
				String message = results.getString("playername");
					temp.add(message);
				}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return temp;
	}
	public static String GetMessage() {
	//public string servermessage;
		try {
			Statement statement = con.createStatement();
			String query = "SELECT * FROM servermessage WHERE expired = '0'";
			ResultSet results = statement.executeQuery(query);
			while(results.next()) {
				int expired = results.getInt("expired");
				String message = results.getString("message");
				int msgid = results.getInt("msgid");
				int repeat = results.getInt("repeat");
				int newRepeat = repeat;
				if(expired == 0) {
					if (repeat > 0) {
						newRepeat = newRepeat - 1;
						query("UPDATE servermessage SET repeat = " + newRepeat +" WHERE msgid = " + msgid + "");
							if (statement != null) {
					statement.close();
				}
				if (results != null) {
					results.close();
				}
						return message;
					}
					if (repeat <= 0) {
						if (statement != null) {
					statement.close();
				}
				if (results != null) {
					results.close();
				}
						query("UPDATE servermessage SET expired= '1' WHERE msgid = '" + msgid + "'");
					}
				}
				}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return null;
	}
	
	public static void writeVote(String name){
		try{
		String query = "INSERT INTO votecounter(id, playername) values(0,'"+name+"')";
		stm.executeUpdate(query);
		}catch(Exception e ){
		
		}
	}
	public static void writeDropLog(String pname, int itemid, int amount){ 
	if (Constants.WORLD_NUMBER == 5) { return;}
	try {
		//Connection con = DriverManager.getConnection("jdbc:mysql://localhost/donationsystem","root","NUIDSfnu7387ca");
		String query = "INSERT INTO droplog(itemid,playername,amount,time) values(" + itemid + ",'" + pname + "'," + amount + ",now())";
	//	Statement statement = con.createStatement();
		stm.executeUpdate(query);
		//statement.close();
		//con.close();
	}
		catch (Exception e) {
			System.out.println("writeDropLog failed: " + e.getMessage());
		}
	}
public static void writeDice(String name, long time, String type, int amount, int roll) {
	try {
	
		String query = "INSERT INTO dicing (username,time,date,type,amount,roll) values('" + name + "','" + time + "',now(),'"+type+"','"+amount+"','"+roll+"')";
		stm.executeUpdate(query);
	}
		catch (Exception e) {
			System.out.println("writeConnLog failed: " + e.getMessage());
		}
	
	
	}
	public static void writeConnLog(String pname, String ip, int mac, int mac2){ 
	//return;
	try {
	//	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/donationsystem","root","NUIDSfnu7387ca");
		String query = "INSERT INTO connections (name,ip,mac,time,mac2) values('" + pname + "','" + ip + "'," + mac + ",now(),"+ mac2+")";
	//	Statement statement = con.createStatement();
		stm.executeUpdate(query);
		//statement.close();
	//	con.close();
	}
		catch (Exception e) {
			System.out.println("writeConnLog failed: " + e.getMessage());
		}
	}
	public static void writeDuel(String winner, String loser,int type, int itemid, int amount){ 
	//return;
	if (Constants.WORLD_NUMBER == 5) { return;}
	try {
		long epoch2 = System.currentTimeMillis()/1000;
		String itemname = getName(itemid);
	//	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/donationsystem","root","NUIDSfnu7387ca");
		String query = "INSERT INTO duelLogs (winner, loser,type,itemname, itemid, amount, time, date) values('" + winner + "','" + loser + "',"+type+",'" +itemname+"'," + itemid + ","+amount+","+epoch2+",now())";
	//	Statement statement = con.createStatement();
		stm.executeUpdate(query);
		//statement.close();
	//	con.close();
	}
		catch (Exception e) {
			System.out.println("writeConnLog failed: " + e.getMessage());
		}
	}
	public static void writeFromDuel(String name, int type,int itemid, int amount){ 
	//return;
	if (Constants.WORLD_NUMBER == 5) { return;}
	try {
		long epoch2 = System.currentTimeMillis()/1000;
		String itemname = getName(itemid);
	//	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/donationsystem","root","NUIDSfnu7387ca");
		String query = "INSERT INTO fromduel (username, type, itemname, itemid, amount, time, date) values('" + name + "'," + type +",'"+itemname+ "'," + itemid + ","+amount+","+epoch2+",now())";
	//	Statement statement = con.createStatement();
		stm.executeUpdate(query);
		//statement.close();
	//	con.close();
	}
		catch (Exception e) {
			System.out.println("writeConnLog failed: " + e.getMessage());
		}
	}	
	public static String getName(int id) {
							String WeaponName = "NULL";
							//WeaponName = Server.getItemManager().getItemDefinition(id).getName().toLowerCase();
							WeaponName = WeaponName.replaceAll("_", " ");
							WeaponName = WeaponName.replaceAll("'", " ");
							WeaponName = WeaponName.trim();	
			return WeaponName;				
	}
	
	public static void writeFromTrade(String name, String with, int type,int itemid, int amount){ 
	//return;
	if (Constants.WORLD_NUMBER == 5) { return;}
	try {
		long epoch2 = System.currentTimeMillis()/1000;
	//	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/donationsystem","root","NUIDSfnu7387ca");
	String itemname = getName(itemid);
		String query = "INSERT INTO fromtrade (username, tradewith,itemname,type, itemid, amount, time, date) values('" + name + "','"+ with +"','" +itemname+"'," + type + "," + itemid + ","+amount+","+epoch2+",now())";
	//	Statement statement = con.createStatement();
		stm.executeUpdate(query);
		//statement.close();
	//	con.close();
	}
		catch (Exception e) {
			System.out.println("writeConnLog failed: " + e.getMessage());
		}
	}
	public static void writeTradeLogs(String name, String with, int itemidreceive, int amountreceive, int type){ 
	if (Constants.WORLD_NUMBER == 5) { return;}
	//return;
	try {
		long epoch2 = System.currentTimeMillis()/1000;
		String itemname = getName(itemidreceive);
	//	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/donationsystem","root","NUIDSfnu7387ca");
		String query = "INSERT INTO tradeLogs (username, tradewith,itemname, itemidreceive, amountreceive, time, date,type) values('" + name + "','"+ with +"','" +itemname+"',"+ itemidreceive + ","+amountreceive+","+epoch2+",now(),"+type+")";
	//	Statement statement = con.createStatement();
		stm.executeUpdate(query);
		//statement.close();
	//	con.close();
	}
		catch (Exception e) {
			System.out.println("writeConnLog failed: " + e.getMessage());
		}
	}
	public static boolean voteTickets(String pname, String ip, int amount){ 
	//return;
	try {
	//	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/donationsystem","root","NUIDSfnu7387ca");
		long epoch = System.currentTimeMillis()/1000;
		String query = "INSERT INTO votetickets (username,ip,amount,time) values('" + pname + "','" + ip + "'," + amount + ","+epoch+")";
	//	Statement statement = con.createStatement();
		stm.executeUpdate(query);
		query = "INSERT INTO voteticketsLogs (username,ip,amount,time) values('" + pname + "','" + ip + "'," + amount + ","+epoch+")";
		stm.executeUpdate(query);
		return true;
		//statement.close();
	//	con.close();
	}
		catch (Exception e) {
			System.out.println("writeConnLog failed: " + e.getMessage());
			return false;
		}
	}
	public static void writeDeathLog(String pname, String items, String amounts){ 
	return;
	/*try {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/donationsystem","root","NUIDSfnu7387ca");
		String query = "INSERT INTO deaths (name,items,amounts,time) values('" + pname + "','" + items + "','" + amounts + "',now())";
		Statement statement = con.createStatement();
		statement.executeUpdate(query);
		statement.close();
		con.close();
	}
		catch (Exception e) {
			System.out.println("writeConnLog failed: " + e.getMessage());
		}*/
	}
}
