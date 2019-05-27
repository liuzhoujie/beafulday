package zhujie;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Controllors{
	String value() default "";
}
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Autowired{
	
}
class Syslog{
	
}
@Controllors("hhh")
class SysLogController{
	@Autowired
	private Syslog syslog;
}

public class Annotionss {
	
	public static void main(String[] args) {
		try {
			Class<?> cls = Class.forName("zhujie.SysLogController");
			
			boolean annotationPresent = cls.isAnnotationPresent(Controllors.class);
			
			System.out.println(annotationPresent);
			
			Controllors con = cls.getDeclaredAnnotation(Controllors.class);
			
		
			System.out.println(con.value());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Class<?> clss = Class.forName("zhujie.SysLogController");
			
			Field[] declaredFields = clss.getDeclaredFields();
			
			for (Field field : declaredFields) {
				if(field.isAnnotationPresent(Autowired.class)) {
					System.out.println("zsssssssssss");
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取类中的属性c
		
	}
}
