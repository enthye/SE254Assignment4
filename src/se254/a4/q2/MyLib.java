package se254.a4.q2;

public class MyLib {	
	static long fact(long n) {
		if(n<=1) return 1;
		else return n*fact(n-1);
	}
	static double max(double n1,double n2, double n3) {
		double m = n1; 
		if(m<n2) m= n2;
		if(m<n3) m= n3;
		return m;
	}
}
