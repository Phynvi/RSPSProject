package com.rs2.util;


import java.io.*;
import java.util.*;
/**
 * Bans processing.
 * 
 * @author Blake
 * @author Graham
 * 
 */
public class BanProcessor {
	private static List<String> bans = new ArrayList<String>();
	private static List<String> mutes = new ArrayList<String>();
	private static List<String> permbans = new ArrayList<String>();
	//added
	private static List<String> ipbans = new ArrayList<String>();
	private static List<String> macbans = new ArrayList<String>();
	private static List<String> ipmutes = new ArrayList<String>();
	//end
	
	private BanProcessor() {
	}
	public static void loadPunishments() {
		try {
			BufferedReader permbanRead = new BufferedReader(new FileReader("./data/bans/permban.txt"));
			String s;
			permbans.clear(); //
			while((s = permbanRead.readLine()) != null) {
				permbans.add(s);
			}
			permbanRead.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
		try {
			BufferedReader banRead = new BufferedReader(new FileReader("./data/bans/accounts.txt"));
			String s;
			bans.clear(); //
			while((s = banRead.readLine()) != null) {
				bans.add(s);
			}
			banRead.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			BufferedReader muteRead = new BufferedReader(new FileReader("./data/bans/mutes.txt"));
			String s;
			mutes.clear(); //
			while((s = muteRead.readLine()) != null) {
				mutes.add(s);
			}
			muteRead.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			BufferedReader ipmuteRead = new BufferedReader(new FileReader("./data/bans/mutes2.txt"));
			String s;
			ipmutes.clear(); //
			while((s = ipmuteRead.readLine()) != null) {
				ipmutes.add(s);
			}
			ipmuteRead.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
//added
		try {
			BufferedReader ipbanRead = new BufferedReader(new FileReader("./data/bans/ips.txt"));
			String s;
			ipbans.clear();
			while((s = ipbanRead.readLine()) != null) {
				ipbans.add(s);
			}
			ipbanRead.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
		try {
			BufferedReader macbanRead = new BufferedReader(new FileReader("./data/bans/macs.txt"));
			String s;
			macbans.clear();
			while((s = macbanRead.readLine()) != null) {
				macbans.add(s);
			}
			macbanRead.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
		try {
			BufferedReader ipmuteRead = new BufferedReader(new FileReader("./data/bans/mutes2.txt"));
			String s;
			ipmutes.clear();
			while((s = ipmuteRead.readLine()) != null) {
				ipmutes.add(s);
			}
			ipmuteRead.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
//end
		System.out.println("Loaded "+ bans.size() +" bans and "+mutes.size() +" mutes.");
	}

	

	
	public static void save(String filename) {
		try {	
			BufferedWriter b = new BufferedWriter(new FileWriter("./data/bans/"+filename+".txt"));
			if(filename.equalsIgnoreCase("accounts")) {
				for(String s : bans) {
					b.write(s);
					b.newLine();
				}
			} 
//changed
			if(filename.equalsIgnoreCase("ips")) {
				for(String s : ipbans) {
					b.write(s);
					b.newLine();
				}
			}
			if(filename.equalsIgnoreCase("macs")) {
				for(String s : macbans) {
					b.write(s);
					b.newLine();
				}
			}
			if(filename.equalsIgnoreCase("mutes2")) {
				for(String s : ipmutes) {
					b.write(s);
					b.newLine();
				}
			}
//end
			else {	
				for(String s2 : mutes) {
					b.write(s2);	
					b.newLine();
				}
			}
			b.flush();
			b.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
	}
		
	public static boolean isBanned(String name) {
		name = name.toLowerCase();
		//name = name.replaceAll(" ", "_");
		return bans.contains(name);
	}

	public static boolean isMuted(String  name) {
		name = name.toLowerCase();
		//name = name.replaceAll(" ", "_");
		return mutes.contains(name);
	}

	public static void addBan(String name) {
		name = name.toLowerCase();
		//name = name.replaceAll(" ", "_");
		bans.add(name);
		save("bans");
	}

	public static void addMute(String name) {
		name = name.toLowerCase();
		//name = name.replaceAll(" ", "_");
		mutes.add(name);
		save("mutes");
	}
	
	
	
	public static void muteUser(String name,String bannedBy) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("./data/bans/mutes.txt", true));
		bw.write(name);
		bw.newLine();
		bw.flush();
		bw.close();
		//VoteSql.banIPMUTE(name.toLowerCase(),bannedBy);
	}

	public static void yellMuteUser(String name) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(
				"./data/bans/yellmutes.txt", true));
		bw.write(name);
		bw.newLine();
		bw.flush();
		bw.close();
	}
	public static void pbanUser(String name) throws IOException {
		name = name.toLowerCase();
		//name = name.replaceAll(" ", "_");
		permbans.add(name);
		BufferedWriter bw = new BufferedWriter(new FileWriter(
				"./data/bans/permban.txt", true));
		bw.write(name);
		bw.newLine();
		bw.flush();
		bw.close();
	}
	public static void banUser(String name, String banner) throws IOException {
	//VoteSql.banUser(name.toLowerCase(), banner.toLowerCase());/*
		name = name.toLowerCase();
		//name = name.replaceAll(" ", "_");
		bans.add(name);
		BufferedWriter bw = new BufferedWriter(new FileWriter(
				"./data/bans/accounts.txt", true));
		bw.write(name);
		bw.newLine();
		bw.flush();
		bw.close();
	}

	public static void banIP(String ip,String bannedBy) throws IOException {
		ip = ip.toLowerCase(); //added
		ipbans.add(ip); //added
		BufferedWriter bw = new BufferedWriter(new FileWriter(
				"./data/bans/ips.txt", true));
		bw.write(ip);
		bw.newLine();
		bw.flush();
		bw.close();
		//VoteSql.banIP(ip,bannedBy);
	}

	public static void banUID(int uid) throws IOException {
		/*BufferedWriter bw = new BufferedWriter(new FileWriter(
				Config.HD1+":\\Shard\\config/bans/uids.txt", true));
		bw.write(String.valueOf(uid));
		bw.newLine();
		bw.flush();
		bw.close();*/
	}
	public static void banMAC(int uid, String name,String bannedBy) throws IOException {
		name = name.toLowerCase(); //added
		macbans.add(name); //added
		BufferedWriter bw = new BufferedWriter(new FileWriter(
				"./data/bans/macs.txt", true));
		bw.write(name);	
		bw.newLine();		
		bw.write(String.valueOf(uid));
		bw.newLine();
		bw.flush();
		bw.close();
		String uid2 = String.valueOf(uid);
		//VoteSql.banMAC(uid2,bannedBy);
	}
	public static boolean checkUser(String name) {
		//name = name.toLowerCase();
		//name = name.replaceAll(" ", "_");
		try {
			BufferedReader in = null;
			try {
				in = new BufferedReader(new FileReader(
						"./data/bans/accounts.txt"));
				String i;
				while ((i = in.readLine()) != null) {
					if (i.equals(name))
						return true;
				}
			} finally {
				if (in != null)
					in.close();
			}
		} catch (Exception e) {
			//System.out.println("Error reading banned user: " + name);
			return false;
		}
		return false;
	}

	public static boolean Banned(String name) {
		try {
			BufferedReader in = new BufferedReader(new FileReader("./data/bans/accounts.txt"));
			String Data = null;

			while ((Data = in.readLine()) != null) {
				if (name.equalsIgnoreCase(Data)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean checkPerm(String name) {
		try {
			BufferedReader in = null;
			try {
				in = new BufferedReader(new FileReader(
						"./data/bans/permban.txt"));
				String i;
				while ((i = in.readLine()) != null) {
					if (i.equals(name))
						return true;
				}
			} finally {
				if (in != null)
					in.close();
			}
		} catch (Exception e) {
			System.out.println("Error reading banned user: " + name);
			return false;
		}
		return false;
	}	
	
	public static boolean checkMuted(String name) {
		try {
			BufferedReader in = null;
			try {
				in = new BufferedReader(new FileReader(
						"./data/bans/mutes.txt"));
				String i;
				while ((i = in.readLine()) != null) {
					if (i.equals(name))
						return true;
				}
			} finally {
				if (in != null)
					in.close();
			}
		} catch (Exception e) {
			System.out.println("Error reading banned user: " + name);
			return false;
		}
		return false;
	}

	public static boolean checkIP(String ip) {
		try {
			BufferedReader in = null;
			try {
				in = new BufferedReader(new FileReader("./data/bans/ips.txt"));
				String i;
				while ((i = in.readLine()) != null) {
					if (i.equals(ip))
						return true;
				}
			} finally {
				if (in != null)
					in.close();
			}
		} catch (Exception e) {
			System.out.println("Error reading banned IP: " + ip);
			return false;
		}
		return false;
	}

	public static boolean checkUID(int uid) {
		try {
			BufferedReader in = null;
			try {
				in = new BufferedReader(
						new FileReader("./data/bans/uids.txt"));
				String i;
				while ((i = in.readLine()) != null) {
					if (i.equals(String.valueOf(uid)))
						return true;
				}
			} finally {
				if (in != null)
					in.close();
			}
		} catch (Exception e) {
			System.out.println("Error reading banned UID: " + uid);
			return false;
		}
		return false;
	}

	public static boolean checkMAC(int mac) {
		try {
			BufferedReader in = null;
			try {
				in = new BufferedReader(
						new FileReader("./data/bans/macs.txt"));
				String i;
				while ((i = in.readLine()) != null) {
					if (i.equals(String.valueOf(mac)))
						return true;
				}
			} finally {
				if (in != null)
					in.close();
			}
		} catch (Exception e) {
			System.out.println("Error reading banned MAC: " + mac);
			return false;
		}
		return false;
	}
}
