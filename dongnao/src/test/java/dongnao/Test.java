package dongnao;

public class Test {
	
	
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		Class class1 = sb.getClass();
		
		Class class2 = StringBuffer.class;
		Class class3 = null;	
		try {
			class3 = Class.forName("java.lang.StringBuffer");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("1v1="+(class1));
		System.out.println("1v2="+(class2));
		System.out.println("1v3="+(class3));
		System.out.println("1v2="+(class1==class2));
		System.out.println("1v3="+(class1==class3));
		System.out.println("2v3="+(class2==class3));
	}
}
