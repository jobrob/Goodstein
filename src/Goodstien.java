import java.lang.Math;
import java.util.Arrays;
import java.util.Scanner;

public class Goodstien
{
	public static void main(String args[])
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new InputUI();
			}
		});
//		baseN(3,3);
//		nextTerm(108,3);
	}
	
	public static int[] baseN(int x, int base)
	{
		int[] test = new int[0];
		if(x == base)
		{
			test = new int[1];
			test[0] = 1;
		}
		while (x > base)
		{
			int i = -1;
			Double y = 0.0;
			while (y <= x)
			{
				i++;
				y= Math.pow(base, i);
			}
			i-=1;
			y= Math.pow(base, i);
			int k = Math.toIntExact(Math.floorDiv(x,Math.round(y)));
			if(test.length == 0)
			{
				test = new int [i+1];
			}
			if(k == base)
			{
				test[i] = 1;
			}
			test[i] = k;
			x-=k*y;
		}
		test[0] = x;
		for (int i = test.length-1; i>=0;i--)
		{
			System.out.print(test[i] + " ");
		}
		System.out.println("\n the check is " + reverseBase(test,base));
		return test;
	}
	
	public static int reverseBase(int[] input, int base)
	{
		int result = 0;
		for(int i = input.length-1; i>=0 ; i--)
		{
			result+= input[i]*Math.pow(base,i);
		}
		return result;
	}
	
	public static int nextTerm(int input,int base)
	{
		int result = 0;
		int[] baseForm = baseN(input,base);
		for(int i = baseForm.length-1; i>=0 ; i--)
		{
			if(i>base)
			{
				int tempresult = base +1;
				int remainder = (int)Math.IEEEremainder(baseForm[i],base);
				for (int quotent  = Math.floorDiv(baseForm[i],base); quotent>1;quotent--)
				{
					System.out.println("calculating " + tempresult + " to the power " + (base+1));
					tempresult = (int) Math.pow(tempresult,base+1);
				}
				System.out.println("calculating " + tempresult + " to the power " + (base+1) + " + " + remainder);
				tempresult = (int) Math.pow(tempresult,base+1 + remainder);
				result+= baseForm[i]*tempresult;
			}
			else if(i==0)
			{
				System.out.println("in the zero");
				
				result+=baseForm[i]*1;
			}
			else
			{
				
				if(i == base)
				{
					System.out.println("adding " +baseForm[i] + " * " + (base+1) + " to the power" + (i+1));
					result+= baseForm[i]*Math.pow(base+1,i+1);
				}
				else
				{
					System.out.println("adding " +baseForm[i] + " * " + (base+1) + " to the power" + (i));
					result+= baseForm[i]*Math.pow(base+1,i);
				}
			}
		}
		result--;
		System.out.println(result);
		return result;
	}
}
//[1,2,0,1,2]^
//[1,2,3,4,5,6,7,8,9] or
//[[1(1)],[2(1)],[1(3)],[1+1(3)] ,[2(1) + 1(3)],[0(1) + 2(3)],[1(1) + 2(3)],[2(1) + 2(3)],[0(1) + 0(3) + 1(9)]]
//or
//[[1(1)],[2(1)],[1(3)],[1+1(3)] ,[2(1) + 1(3)],[0(1) + 2(3)],[1(1) + 2(3)],[2(1) + 2(3)],[3^2]
