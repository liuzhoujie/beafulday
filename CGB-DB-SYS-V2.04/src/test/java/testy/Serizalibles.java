package testy;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serizalibles {
	public static void main(String[] args) {
		point p = new point();
		p.x = 10;
		
	/*	ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("s1.txt"));*/
	}

}
class point{
	int x;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "point+x"+x;
		
		
	}
}