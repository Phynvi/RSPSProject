package com.rs2.util;

import java.io.*;
import java.util.*;

import com.rs2.util.sql.VoteSql;

public class RightsProcessor {

	private static List<String> developer = new ArrayList<String>();
	private static List<String> staff = new ArrayList<String>();
	private static List<String> fagRank = new ArrayList<String>();
	private static List<String> greenDon = new ArrayList<String>();
	private static List<String> blueDon = new ArrayList<String>();
	private static List<String> redDon = new ArrayList<String>();

	
	private RightsProcessor() {
	
	}
	
	public static void loadRights() {
		try {
			BufferedReader developerRead = new BufferedReader(new FileReader("./data/rights/developer.conf"));
			String s;
			developer.clear(); 
			while((s = developerRead.readLine()) != null) {
				developer.add(s);
			}
			developerRead.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			BufferedReader staffRead = new BufferedReader(new FileReader("./data/rights/staff.conf"));
			String s;
			staff.clear();
			while((s = staffRead.readLine()) != null) {
				staff.add(s);
			}
			staffRead.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		try {
			BufferedReader fagRankRead = new BufferedReader(new FileReader("./data/rights/fagRank.conf"));
			String s;
			fagRank.clear();
			while((s = fagRankRead.readLine()) != null) {
				fagRank.add(s);
			}
			fagRankRead.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		try {
			BufferedReader greenDonRead = new BufferedReader(new FileReader("./data/rights/greenDon.conf"));
			String s;
			greenDon.clear();
			while((s = greenDonRead.readLine()) != null) {
				greenDon.add(s);
			}
			greenDonRead.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			BufferedReader blueDonRead = new BufferedReader(new FileReader("./data/rights/blueDon.conf"));
			String s;
			blueDon.clear();
			while((s = blueDonRead.readLine()) != null) {
				blueDon.add(s);
			}
			blueDonRead.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			BufferedReader redDonRead = new BufferedReader(new FileReader("./data/rights/redDon.conf"));
			String s;
			redDon.clear();
			while((s = redDonRead.readLine()) != null) {
				redDon.add(s);
			}
			redDonRead.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}	
	
	public static void undon(String name) {
		//name = name.toLowerCase();
		int idx = fagRank.indexOf(name);
		if(idx == -1) 
			return;
		fagRank.remove(idx);
		save("fagRank");
	}
	
	public static void save(String filename) {
		try {	
			BufferedWriter b = new BufferedWriter(new FileWriter("./data/rights/"+filename+".conf"));
			if(filename.equalsIgnoreCase("developer")) {
				for(String s : developer) {
					b.write(s);
					b.newLine();
				}
			} else if(filename.equalsIgnoreCase("staff"))  {	
				for(String s2 : staff) {
					b.write(s2);	
					b.newLine();
				}
			} else if(filename.equalsIgnoreCase("fagRank")) {	
				for(String s3 : fagRank) {
					b.write(s3);	
					b.newLine();
				}
			} else if(filename.equalsIgnoreCase("greenDon")) {	
				for(String s5 : greenDon) {
					b.write(s5);	
					b.newLine();
				}
			} else if(filename.equalsIgnoreCase("blueDon")) {	
				for(String s6 : blueDon) {
					b.write(s6);	
					b.newLine();
				}
			} else if(filename.equalsIgnoreCase("redDon")) {	
				for(String s7 : redDon) {
					b.write(s7);	
					b.newLine();
				}
			}
			b.flush();
			b.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
	}	
	public static int checkRights(String name) {
		try {
			BufferedReader in = null;
			//BufferedReader in2 = null;
			//BufferedReader in3 = null;
			//BufferedReader in4 = null;
			BufferedReader in5 = null;
			BufferedReader in6 = null;
			try {
				in = new BufferedReader(new FileReader("./data/rights/fagRank.conf"));
				String i = "";
				while ((i = in.readLine()) != null) {
					if (i.equalsIgnoreCase(name))
						return 1;
				}
				if (VoteSql.isGreenDon(name)) {
					return 2;
				}
				if (VoteSql.isBlueDon(name)) {
					return 3;
				}
				if (VoteSql.isRedDon(name)) {
					return 4;
				}
				
				/*in2 = new BufferedReader(new FileReader(
						"./data/rights/greenDon.conf"));
				String i2 = "";
				while ((i2 = in2.readLine()) != null) {
					if (i2.equalsIgnoreCase(name))
						return 2;
				}
				in3 = new BufferedReader(new FileReader(
						"./data/rights/blueDon.conf"));
				String i3 = "";
				while ((i3 = in3.readLine()) != null) {
					if (i3.equalsIgnoreCase(name))
						return 3;
				}
				in4 = new BufferedReader(new FileReader(
						"./data/rights/redDon.conf"));
				String i4 = "";
				while ((i4 = in4.readLine()) != null) {
					if (i4.equalsIgnoreCase(name))
						return 4;
				}*/
				in5 = new BufferedReader(new FileReader(
						"./data/rights/staff.conf"));
				String i5 = "";
				while ((i5 = in5.readLine()) != null) {
					if (i5.equalsIgnoreCase(name))
						return 5;
				}	
				in6 = new BufferedReader(new FileReader(
						"./data/rights/developer.conf"));
				String i6 = "";
				while ((i6 = in6.readLine()) != null) {
					if (i6.equalsIgnoreCase(name))
						return 6;
				}
			} finally {
				if (in != null)
					in.close();
				/*if (in2 != null)
					in2.close();
				if (in3 != null)
					in3.close();
				if (in4 != null)
					in4.close();*/
				if (in5 != null)
					in5.close();
				if (in6 != null)
					in6.close();
					
			}
		} catch (Exception e) {
			System.out.println("Error reading rights: " + name);
			e.printStackTrace();
			return 0;
		}
		return 0;
	}	
}