package xu.li.hua;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
/**
 *  指定序列化属性用Externalizable
 * @author TEDU
 *
 */
class Employee implements Externalizable{
	private int id;
	private String name;
	
	
	public Employee(){}//必须指定
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		//序列化
		out.writeInt(id);
	}
    //反序列化调用
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.id = in.readInt();
	}
	
}
public class Testxilihua3 {

}
