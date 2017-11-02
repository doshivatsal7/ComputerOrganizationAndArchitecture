import java.util.*;

class RestoringDivision
{	
	int n=0,q_1=0,carry=0;
	int a[]=new int[8];
	int b[]=new int[8];
	int q[]=new int[8];
	public static void main(String args[])
	{
		int i,x,y;
		RestoringDivision d= new RestoringDivision();
		Scanner scr=new Scanner(System.in);
		System.out.print("Enter the dividend: ");
		y=scr.nextInt();
		System.out.print("Enter the divisor: ");
		x=scr.nextInt();
		for(i=0;i<d.a.length;i++)
			d.a[i]=0;
		d.toBinary(x,y);
		if((d.n)<=4)
		{
			System.out.println("    A\t Q\t M\t       COMMENT");
			System.out.println("-----------------------------------------------");
		}
		else
		{
			System.out.println("    A\t\t    Q\t\t   M\t\t     COMMENT");
			System.out.println("-----------------------------------------------------------------------");
		}
		d.printArray("Initialize");
		if((d.n)<=4)
			System.out.println("-----------------------------------------------");
		else
			System.out.println("-----------------------------------------------------------------------");	
		d.divide();
		d.toDecimal();			
	}

	public void printArray(String s)
	{
		int i;
		for(i=a.length-n;i<a.length;i++)
			System.out.print(a[i]);
		System.out.print("\t");
		for(i=q.length-n;i<q.length;i++)
			System.out.print(q[i]);
		System.out.print("\t");
		for(i=b.length-n;i<b.length;i++)
			System.out.print(b[i]);
		System.out.println("\t"+s);

	}

	public void toBinary(int x, int y)
	{
		int rem,index=7,n1=0,n2=0,x1,y1,i;
		x1=x;
		y1=y;
		x=Math.abs(x);
		y=Math.abs(y);
		while(x>0)
		{	
			rem=x%2;
			b[index--]=rem;
			x=x/2;
			n1++;
		}
		index=7;
		while(y>0 || y<0)
		{
			rem=y%2;
			q[index--]=rem;
			y=y/2;
			n2++;
		}
		if(n1<=4 && n2<=4)
			n=4;
		else
			n=8;
		if(x1<0)
			twos_complement(b);
		if(y1<0)
			twos_complement(q);	
	}
	public void ones_complement(int a[])
	{
		int i;
		for(i=a.length-n;i<a.length;i++)
		{
			if(a[i]==0)
				a[i]=1;
			else if(a[i]==1)
				a[i]=0;
		}
	}
	
	public void twos_complement(int a[])
	{
		int i;
		int c[]=new int[8];
		c[7]=1;
		ones_complement(a);
		add(a,c);
	}

	public void divide()
	{
		int i,j;
		int copy[]=new int[8];
		for(j=n;j>0;j--)
		{
			lshift();
			for(i=0;i<b.length;i++)
				copy[i]=b[i];
			twos_complement(copy);
			add(a,copy);			
			printArray("Subtract");
			if(a[8-n]==1)
			{
				q[7]=0;
				add(a,b);
				printArray("Restore and set Q0=0");
			}
			else
			{
				q[7]=1;
				printArray("Q0=1");
			}	
			if((n)<=4)
				System.out.println("-----------------------------------------------");
			else
				System.out.println("-----------------------------------------------------------------------");	
		}
	}

	public void add(int a[],int b[])
	{	
		int i;
		for(i=a.length-1;i>=(8-n);i--)
		{
			carry=0;
			a[i]=a[i]+b[i];
			if(a[i]==2)
			{
				a[i]=0;
				carry=1;
			}
			else if(a[i]==3)
			{
				a[i]=1;
				carry=1;
			}
			if(i>0)
				a[i-1]=a[i-1]+carry;	
		}
	}	
	
	public void lshift()
	{
		int i;
		for(i=8-n;i<7;i++)
			a[i]=a[i+1];
		a[7]=q[8-n];
		for(i=8-n;i<7;i++)
			q[i]=q[i+1];
		q[7]=0;	
		printArray("Left Shift");
	}

	public void toDecimal()
	{
		int quotient=0,i,count=0,remainder=0;
		for(i=0;i<n;i++)
			quotient=quotient+(int)(q[7-i]*Math.pow(2,count++));
		count=0;
		for(i=0;i<n;i++)
			remainder=remainder+(int)(a[7-i]*Math.pow(2,count++));
		System.out.println("Quotient is: "+quotient);
		System.out.println("Remainder is: "+remainder);
	}
}
	