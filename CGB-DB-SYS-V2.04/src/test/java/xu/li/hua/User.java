package xu.li.hua;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class User implements Serializable{

	private static final long serialVersionUID = -3617486002315540974L;
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		//模拟加密
		Encoder encoder = Base64.getEncoder();
		byte[] bytes = username.getBytes();
		encoder.encodeToString(bytes);
		//序列化
		out.defaultWriteObject();
	}
	
	private void readObject(ObjectInputStream in) throws Exception {
		//反序列化
		in.defaultReadObject();
		//模拟解密
		Decoder decoder = Base64.getDecoder();
		byte[] decode = decoder.decode(username);
		this.username = new String(decode);
		
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	

}
