package com.rs2.net;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.security.SecureRandom;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;
import sun.misc.CharacterEncoder;

import com.rs2.Constants;
import com.rs2.Server;
import com.rs2.model.players.Player;
import com.rs2.model.players.Player.LoginStages;
import com.rs2.util.NameUtil;
import com.rs2.util.PlayerSave;
import com.rs2.util.PunishmentManager;

public class Login {
	
	public static String filteredWords[] = {"Mod","mod","admin","owner","0wner","adm1n",
	"m0d","m 0d","mo d","m o d","m 0 d","o w n e r","a d m i n","syi","s y i",
	"pat","staff","admin","god","owner","developer"
	
	};
	public static boolean isFiltered(String user) {
		  for(int i = 0; i < filteredWords.length; i++) {
			String name = user.toLowerCase();
		   if(name.contains(filteredWords[i]))
			return true;
		  }
		 return false;
	}
	public static String encrypt(String plaintext) {
	    MessageDigest md = null;
	    try
	    {
	      md = MessageDigest.getInstance("SHA"); //step 2
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	    }
	    try
	    {
	      md.update(plaintext.getBytes("UTF-8")); //step 3
	    }
	    catch(UnsupportedEncodingException e)
	    {
	    }
	    byte raw[] = md.digest(); //step 4
	    String hash = (new BASE64Encoder()).encode(raw); //step 5
	    return hash; //step 6
	  }	
	public void handleLogin(Player player, ByteBuffer inData) throws Exception {
		switch (player.getLoginStage()) {
		case CONNECTED:
			if (inData.remaining() < 2) {
				inData.compact();
				return;
			}

			// Validate the request.
			int request = inData.get() & 0xff;
			inData.get(); // Name hash.
			if (request != 14) {
				System.err.println("Invalid login request: " + request);
				player.disconnect();
				return;
			}

			// Write the response.
			StreamBuffer.OutBuffer out = StreamBuffer.newOutBuffer(17);
			out.writeLong(0); // First 8 bytes are ignored by the client.
			out.writeByte(0); // The response opcode, 0 for logging in.
			out.writeLong(new SecureRandom().nextLong()); // SSK.
			player.send(out.getBuffer());

			player.setLoginStage(LoginStages.LOGGING_IN);
			break;
		case LOGGING_IN:
			if (inData.remaining() < 2) {
				inData.compact();
				return;
			}

			// Validate the login type.
			int loginType = inData.get();
			if (loginType != 16 && loginType != 18) {
				System.err.println("Invalid login type: " + loginType);
				player.disconnect();
				return;
			}

			// Ensure that we can read all of the login block.
			int blockLength = inData.get() & 0xff;
			int loginEncryptSize = blockLength - (36 + 1 + 1 + 2);
			if (inData.remaining() < blockLength) {
				inData.compact();
				return;
			}

			// Read the login block.
			StreamBuffer.InBuffer in = StreamBuffer.newInBuffer(inData);
			
			// Set the magic id
			player.setMagicId(in.readByte());

			// Set the client version.
			int clientVersion = in.readInt();
			//if (clientVersion != Constants.CLIENT_VERSION + 689263) {
			//	player.setReturnCode(Constants.LOGIN_RESPONSE_UPDATED);
			//}			
			player.setClientVersion(clientVersion);

			in.readByte(); // Skip the high/low memory version.

			// Skip the CRC keys.
			for (int i = 0; i < 9; i++) {
				in.readInt();
			}
			if (Constants.RSA_CHECK) {
				loginEncryptSize--;
				int reportedSize = inData.get() & 0xFF;
				if (reportedSize != loginEncryptSize) {
					System.err.println("Encrypted packet size zero or negative : " + loginEncryptSize);
					player.disconnect();
					return;
				}
				byte[] encryptionBytes = new byte[loginEncryptSize];
				inData.get(encryptionBytes);
				ByteBuffer rsaBuffer = ByteBuffer.wrap(new BigInteger(encryptionBytes).modPow(Constants.RSA_EXPONENT, Constants.RSA_MODULUS).toByteArray());
				int rsaOpcode = rsaBuffer.get() & 0xFF;
				// Validate that the RSA block was decoded properly.
				if (rsaOpcode != 10) {
					System.err.println("Unable to decode RSA block properly!");
					player.disconnect();
					//saveIp(player.getHost());
					return;
				}
				long clientHalf = rsaBuffer.getLong();
				long serverHalf = rsaBuffer.getLong();
				int[] isaacSeed = { (int) (clientHalf >> 32), (int) clientHalf,
						(int) (serverHalf >> 32), (int) serverHalf };
				player.setDecryptor(new ISAACCipher(isaacSeed));
				for (int i = 0; i < isaacSeed.length; i++) {
					isaacSeed[i] += 50;
				}
				player.setEncryptor(new ISAACCipher(isaacSeed));
				rsaBuffer.getInt();
				int playerMacAddress = in.readInt();
				in.readInt(); // Skip the user ID.
				int currentClientId = in.readInt();
                String username = in.readString().trim();
                player.tempPassword = in.readString().toLowerCase().trim();
                player.tempPassword = encrypt(player.tempPassword);
                //player.setPassword(password);
                player.setUsername(NameUtil.uppercaseFirstLetter(username));
                player.setMacAddress(playerMacAddress);
                player.setClientVersion(currentClientId);
                player.setClientVersion2(currentClientId);
               
				/*String username = NameUtil.getRS2String(rsaBuffer).trim();
				String password = NameUtil.getRS2String(rsaBuffer).trim();
				player.setPassword(password);
				player.setUsername(NameUtil.uppercaseFirstLetter(username));*/
			} else {
				in.readByte(); // Skip RSA block length.
				// Validate that the RSA block was decoded properly.
				int rsaOpcode = in.readByte();
				if (rsaOpcode != 10) {
					System.err.println("Unable to decode RSA block properly!");
					player.disconnect();
					return;
				}
				// Set up the ISAAC ciphers.
				long clientHalf = in.readLong();
				long serverHalf = in.readLong();
				int[] isaacSeed = { (int) (clientHalf >> 32), (int) clientHalf, (int) (serverHalf >> 32), (int) serverHalf };
				player.setDecryptor(new ISAACCipher(isaacSeed));
				for (int i = 0; i < isaacSeed.length; i++) {
					isaacSeed[i] += 50;
				}
				player.setEncryptor(new ISAACCipher(isaacSeed));

				// Read the user authentication.
				int playerMacAddress = in.readInt();
				in.readInt(); // Skip the user ID.
				int currentClientId = in.readInt();
                String username = in.readString().trim();
                //String password = in.readString().toLowerCase().trim();
                
                player.tempPassword = in.readString().trim();
                //player.setPassword(password);
                player.tempPassword = encrypt(player.tempPassword);
                player.setUsername(NameUtil.uppercaseFirstLetter(username));
                player.setMacAddress(playerMacAddress);
                player.setClientVersion(currentClientId);
                player.setClientVersion2(currentClientId);
            }

            player.setUsernameAsLong(NameUtil.nameToLong(player.getUsername().toLowerCase()));
            player.setLoginStage(LoginStages.AWAITING_LOGIN_COMPLETE);
            
            if (player.beginLogin() && player.getLoginStage() == LoginStages.AWAITING_LOGIN_COMPLETE) {
            // Switch the player to the cycled reactor.
                synchronized (DedicatedReactor.getInstance()) {
                    DedicatedReactor.getInstance().getSelector().wakeup();
                    player.getKey().interestOps(player.getKey().interestOps() & ~SelectionKey.OP_READ);
                    player.getSocketChannel().register(Server.getSingleton().getSelector(), SelectionKey.OP_READ, player);
                }
            }
        }
    }

	@SuppressWarnings("unused")
	private void saveIp(String host) {
		String filePath = "./data/ip.txt";
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filePath, true));
			try {
				out.write(host);
				out.newLine();
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean checkName(String username) {
		/*
		 * String[] names = {"Mod Caleb", "Mod Vault", "Mod Russian",
		 * "Mod Vayken", "Mod James", "Mod Blake", "Mod Josh", "Mod Nick",
		 * "Mod Calvin", "Mod Ian", "Mod Patrick", "Darrel", "Divine", "Ftw",
		 * "Liberty", "Melee", "Ness", "Rex", "Jwc"}; for (String name : names)
		 * { if (username.equalsIgnoreCase(name)) { return true; } } return
		 * false;
		 */
		return true;
	}

}
