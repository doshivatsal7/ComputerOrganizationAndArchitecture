
import java.util.*;

class Division
{	
	int n=0,q_1=0,carry=0;
	int a[]=new int[8];
	int b[]=new int[8];
	int q[]=new int[8];
	public static void main(String args[])
	{
		int i,x,y;
		Division d= new Division();
		Scanner scr=new Scanner(System.in);
		System.out.print("Enter the number1: ");
		x=scr.nextInt();
		System.out.print("Enter the number2: ");
		y=scr.nextInt();
		for(i=0;i<d.a.length;i++)
		{
			d.a[i]=0;
		}
		d.toBinary(x,y);
		if((d.n)<=4)
		{
			System.out.println("A\t Q\tQ-1\t M");
			System.out.println("--------------------------------");
		}
		else
		{
			System.out.println("A\t    Q\t\t   Q-1\t\t   M");
			System.out.println("----------------------------------------------");
		}
		for(i=d.a.length-d.n;i<d.a.length;i++)
		{
			System.out.print(d.a[i]);
		}
		System.out.print("\t");
		for(i=d.q.length-d.n;i<d.q.length;i++)
		{
			System.out.print(d.q[i]);
		}
		System.out.print("\t");
		System.out.print(+d.q_1+"\t");
		for(i=d.b.length-d.n;i<d.b.length;i++)
		{
			System.out.print(d.b[i]);
		}
		System.out.println();
		if((d.n)<=4)
		{
			System.out.println("--------------------------------");
		}
		else
		{
			System.out.println("----------------------------------------------");
		}
		d.divide();
		d.toDecimal();			
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
		{
			twos_complement(b);
		}
		if(y1<0)
		{
			twos_complement(q);
		}		
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
			System.out.print(+q_1+"\t");
			for(i=b.length-n;i<b.length;i++)
			{
				System.out.print(b[i]);
			}
			System.out.println();
			if(a[8-n]==1)
			{
				q[7]=0;
				add(a,b);
			}
			else
			{
				q[7]=1;
			}	
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
			System.out.print(+q_1+"\t");
			for(i=b.length-n;i<b.length;i++)
			{
				System.out.print(b[i]);
			}
			System.out.println();	
		}
	}

	public void add(int a[],int b[])
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
	
	public void lshift()
	{
		int i;
		a[7]=q[8-n];
		for(i=8-n;i<7;i++)
		{
			q[i]=q[i+1];
		}
		for(i=8-n+1;i<7;i++)
		{
			a[i]=a[i+1];
		}
		a[8-n]=0;	
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
		System.out.print(+q_1+"\t");
		for(i=b.length-n;i<b.length;i++)
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
		int quotient=0,i,count=0,remainder=0;
		boolean flaga=false,flagq=false;
		if(a[8-n]==1)
		{
			twos_complement(a);
			flaga=true;
		} 
		if(q[8-n]==1)
		{
			twos_complement(q);
			flagq=true;
		}
		for(i=0;i<n;i++)
		{
			quotient=quotient+(int)(q[7-i]*Math.pow(2,count++));
		}
		count=0;
		for(i=0;i<n;i++)
		{
			remainder=remainder+(int)(a[7-i]*Math.pow(2,count++));
		}
		if(flaga)
			quotient=-quotient;
		if(flagq)
			remainder=-remainder;
		System.out.println("Quotient is: "+quotient);
		System.out.println("Remainder is: "+remainder);
	}
}
	