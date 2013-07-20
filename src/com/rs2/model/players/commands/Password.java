package com.rs2.model.players.commands;

import com.rs2.model.players.*;
import com.rs2.util.sql.VoteSql;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;
import sun.misc.CharacterEncoder;

public class Password implements Command {

	public String encrypt(String plaintext) {
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

	@Override
	public void execute(Player client, String command) {
		if(command.length() == 11 ||command.length() == 12 ) {
			client.getActionSender().sendMessage("Enter a password!");
			return;
		}		
		if (command.length() > 11 || command.length() < 26){
			if (VoteSql.updatePassword(client.getUsername().toLowerCase(),encrypt(encrypt(command.substring(11))+client.salt))) {

			client.setPassword(encrypt(encrypt(command.substring(11))+client.salt));
			client.getActionSender().sendMessage(
					"Your new pass is \"" + command.substring(11) + "\"");
					client.getActionSender().sendMessage("@red@ If somebody asked you to change it, do not log out, change it to something else");
			} else { 
				client.getActionSender().sendMessage("@red@ Something has gone wrong with your password request"); }	
		} else {
			client.getActionSender().sendMessage(
					"Syntax is ::pass newpassword");
		}
	}
}