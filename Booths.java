import java.util.*;

class Booths
{	
	int n=0,q_1=0,carry=0,carry1=0;
	int a[]=new int[8];
	int b[]=new int[8];
	int q[]=new int[8];
	public static void main(String args[])
	{
		int i,x,y;
		Booths booths = new Booths();
		Scanner scr=new Scanner(System.in);
		System.out.print("Enter the number1: ");
		x=scr.nextInt();
		System.out.print("Enter the number2: ");
		y=scr.nextInt();
		for(i=0;i<booths.a.length;i++)
		{
			booths.a[i]=0;
		}
		booths.toBinary(x,y);
		if((booths.n)<=4)
		{
			System.out.println("A\t Q\tQ-1\t B");
			System.out.println("--------------------------------");
		}
		else
		{
			System.out.println("A\t    Q\t\t   Q-1\t\t   B");
			System.out.println("----------------------------------------------");
		}
		for(i=booths.a.length-booths.n;i<booths.a.length;i++)
		{
			System.out.print(booths.a[i]);
		}
		System.out.print("\t");
		for(i=booths.q.length-booths.n;i<booths.q.length;i++)
		{
			System.out.print(booths.q[i]);
		}
		System.out.print("\t");
		System.out.print(+booths.q_1+"\t");
		for(i=booths.b.length-booths.n;i<booths.b.length;i++)
		{
			System.out.print(booths.b[i]);
		}
		System.out.println();
		if(booths.n<=4)
		{
			System.out.println("--------------------------------");
		}
		else
		{
			System.out.println("--------------------------------------------------");
		}
		booths.multiply();
		booths.toDecimal();			
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
		if(n1<4 && n2<4)
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
		for(i=a.length-n;i<a.length;i++)
		{
			if(a[i]==0)
				a[i]=1;
			else if(a[i]==1)
				a[i]=0;
		}
		add(a,c);
		carry1=carry;
	}

	public void multiply()
	{
		int i,j;
		int copy[]=new int[8];
		for(j=n;j>0;j--)
		{
			if((q[7]==0 && q_1==0) || (q[7]==1 && q_1==1))
				rshift();
			else if((q[7]==0 && q_1==1))
			{
				add(a,b);
				if(carry==1)
					a[7-n]=1;
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
				rshift();
			}

			else if((q[7]==1 && q_1==0))
			{
				for(i=0;i<b.length;i++)
					copy[i]=b[i];
				twos_complement(copy);
				add(a,copy);
				if(carry==1)
					a[8-n]=1;
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
				rshift();
			}	
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
	
	public void rshift()
	{
		int i;
		q_1=q[7];
		for(i=7;i>8-n;i--)
			q[i]=q[i-1];
		q[8-n]=a[7];
		for(i=7;i>8-n;i--)
			a[i]=a[i-1];	
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
		int ans=0,i,count=0;
		boolean flag=false;
		if(a[8-n]==1)
		{	
			twos_complement(q);
			if(carry1==0)
				ones_complement(a);
			else
				twos_complement(a);
			flag=true;
		} 
		for(i=0;i<n;i++)
		{
			ans=ans+(int)(q[7-i]*Math.pow(2,count++));
		}
		for(i=0;i<n;i++)
		{
			ans=ans+(int)(a[7-i]*Math.pow(2,count++));
		}
		if(flag)
			ans=-ans;
		System.out.println("Answer is: "+ans);
	}
}
	
		