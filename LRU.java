import java.util.*;

class LRU
{
	public static void main()
	{
		int n,n1,n2,n3,ind1,ind2,ind3;
		Vector cache = new Vector(3);
		System.out.print("Enter the number of elements:");
		Scanner scr = new Scanner(System.in);
		n=scr.nextInt();	
		Vector a=new Vector(n);
		System.out.println("Enter the elements: ");
		for(i=0;i<n;i++)
			cache.addElement(Integer.parseInt(scr.nextInt());
		for(i=0;i<n;i++)
		{
			if(3-cache.size()!=0)
				cache.addElement(Integer.newInteger(a.elementAt(i)));
			else
			{
				n1=Integer.parseInt(cache.elementAt(0));
				n2=Integer.parseInt(cache.elementAt(1));
				n3=Integer.parseInt(cache.elementAt(2));
				if(cache.contains(Integer.newInteger(n1)||cache.contains(Integer.newInteger(n2)||cache.contains(Integer.newInteger(n3))
				{
					printVector("Cache Hit");	
					continue;
				}
				ind1=a.lastIndexOf(n1,i);
				ind2=a.lastIndexOf(n2,i);
				ind3=a.lastIndexOf(n3,i);
				min=(ind1<ind3)?((ind1<ind2)?ind1:ind2):((ind2<ind3)?ind2:ind3);
				x=a.elementAt(min);
				v.setElementAt(Integer.newInteger(Integer.newInteger(a.elementAt(i)), indexOf(x))
				printVector("Page Fault");					
			}
		}
	}
}		