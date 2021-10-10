package riddles;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class Riddle {
	
	public static void main(String[] args){
		Set<A> set1 = new HashSet<A>();
		set1.add(new A(5,7));
		set1.add(new A(7,7));
		set1.add(new A(5,100));
		int num1 = set1.size(); //3
		if(num1 == 2)
			System.out.println("success!");
		else
			System.out.println("Failed test 1");
		
		
		Set<A> set2 = new HashSet<A>();
		set2.add(new B(5,7));
		set2.add(new B(5,7));
		set2.add(new A(5,100));
		set2.add(new A(7,7));
		int num2 = set2.size();
		
		if(num2 == 3)
			System.out.println("success!");
		else
			System.out.println("Failed test 2");
	
		
		
		Set<A> set3 = new TreeSet<A>();
		set3.add(new B(5,7));
		set3.add(new B(5,7));
		set3.add(new A(5,100));
		
		if(set3.size() == 2)
			System.out.println("success!");
		else
			System.out.println("Failed test 3");

		set3.add(new A(7,7));
		
		if(set3.size() == 2)
			System.out.println("success!");
		else
			System.out.println("Failed test 4");
		
		set3.add(new C(7,5));
		set3.add(new C(7,7));
		int num3 = set3.size();
		
		if(num3 == 4)
			System.out.println("success!");
		else
			System.out.println("Failed test 5");
		
		
		A a = set3.iterator().next();
		
		if(a.i == 7)
			System.out.println("success!");
		else
			System.out.println("Failed test 6");
		
		if(a.j == 7)
			System.out.println("success!");
		else
			System.out.println("Failed test 7");
	
		
	}
}
