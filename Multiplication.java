import java.util.*;

class Multiplication
{	
	int n=0,carry=0;
	int a[]=new int[8];
	int b[]=new int[8];
	int q[]=new int[8];
	public static void main(String args[])
	{
		int i,x,y;
		Multiplication m = new Multiplication();
		Scanner scr=new Scanner(System.in);
		System.out.print("Enter the number1: ");
		x=scr.nextInt();
		System.out.print("Enter the number2: ");
		y=scr.nextInt();
		for(i=0;i<m.a.length;i++)
		{
			m.a[i]=0;
		}
		m.toBinary(x,y);
		if((m.n)<=4)
		{
			System.out.println("C\t A\t Q\t B");
			System.out.println("--------------------------------");
		}
		else
		{
			System.out.println("C\t    A\t\t   Q\t\t   B");
			System.out.println("--------------------------------------------------");
		}
		System.out.print(m.carry+"\t");
		for(i=m.a.length-m.n;i<m.a.length;i++)
		{
			System.out.print(m.a[i]);
		}
		System.out.print("\t");
		for(i=m.q.length-m.n;i<m.q.length;i++)
		{
			System.out.print(m.q[i]);
		}
		System.out.print("\t");
		for(i=m.b.length-m.n;i<m.b.length;i++)
		{
			System.out.print(m.b[i]);
		}
		System.out.println();
		if(m.n<=4)
		{
			System.out.println("--------------------------------");
		}
		else
		{
			System.out.println("--------------------------------------------------");
		}
		m.multiply();
		m.toDecimal();			
	}

	public void toBinary(int x, int y)
	{
		int rem,index=7,n1=0,n2=0;
		while(x>0)
		{
			rem=x%2;
			b[index--]=rem;
			x=x/2;
			n1++;
		}
		index=7;
		while(y>0)
		{
			rem=y%2;
			q[index--]=rem;
			y=y/2;
			n2++;
		}
		if(n1>n2)
			n=n1;
		else
			n=n2;
	}

	public void multiply()
	{
		int i,j;
		for(j=n;j>0;j--)
		{
			if(q[7]==1)
				add();
			System.out.print(carry+"\t");
			for(i=a.length-n;i<a.length;i++)
			{
				System.out.print(a[i]);
			}
			System.out.print("\t");
			for(i=q.length-n;i<q.length;i++)
			{
				System.out.print(q[i]);
			}
			System.out.print("\t");
			for(i=b.length-n;i<b.length;i++)
			{
				System.out.print(b[i]);
			}
			System.out.println();
			rshift();
		}
	}

	public void add()
	{	
		int i;
		for(i=a.length-1;i>7-n;i--)
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
			if(i>n)
				a[i-1]=a[i-1]+carry;	
		}
	}
	
	public void rshift()
	{
		int i;
		for(i=7;i>8-n;i--)
			q[i]=q[i-1];
		q[8-n]=a[7];
		for(i=7;i>0;i--)
			a[i]=a[i-1];	
		a[8-n]=carry;
		carry=0;
		System.out.print(carry+"\t");
		for(i=a.length-n;i<a.length;i++)
		{
			System.out.print(a[i]);
		}
		System.out.print("\t");
		for(i=q.length-n;i<q.length;i++)
		{
			System.out.print(q[i]);
		}
		System.out.print("\t");
		for(i=b.length-n;i<q.length;i++)
		{
			System.out.print(b[i]);
		}
		System.out.println();
		if(n<=4)
		{
			System.out.println("--------------------------------");
		}
		else
		{
			System.out.println("--------------------------------------------------");
		}
					
	}

	public void toDecimal()
	{
		int ans=0,i,count=0;;
		for(i=0;i<n;i++)
		{
			ans=ans+(int)(q[7-i]*Math.pow(2,count++));
		}
		for(i=0;i<n;i++)
		{
			ans=ans+(int)(a[7-i]*Math.pow(2,count++));
		}
		System.out.println("Answer is: "+ans);
	}
}
	
		