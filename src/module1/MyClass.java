package module1;

public class MyClass {
	private int a;
	public double b;
	
	public MyClass(int first, double second){
		this.a = first;
		this.b = second;
	}
	public static void incrementBoth(int first){
		first = first + 1;
	}
	public static void main(String[] args){
		MyClass c1 = new MyClass(10, 20.5);
		MyClass c2 = new MyClass(10, 31.5);
		incrementBoth(c2.a);
		System.out.println(c1.a + "," + c2.a);
		
	    
	}
	
}
