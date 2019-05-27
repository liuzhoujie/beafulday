package xu.li.hua;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TestXuLihua {
    public static void main(String[] args) throws FileNotFoundException, IOException {
		User u = new User();
		u.setUsername("admin");
		u.setPassword("123456");
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("m2.txt"));
		out.writeObject(u);
		out.close();
		
		
				
	}
}
