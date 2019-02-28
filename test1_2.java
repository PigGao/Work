import java.io.*;
import java.lang.*;
import java.util.*;
//"time" mean start time,"is_start" mean if this point is start point,"end_time" mean end time.

public class Graph_Coloring2 {
	public static class point
	{
	  int time;
	  int is_start; 
	  int end_time;//if is_start=1，end_time=end_time；if is_start=0，end_time=-1
	  public void set_time(int time)
	  {
		  this.time = time;
	  }
	  public void set_is_start(int time)
	  {
		  this.is_start = time;
	  }
	  public void set_end_time(int time)
	  {
		  this.end_time = time;
	  }
	} 
	
 //static point_t point;

	//sort the all input time.but we just use start_time.

	static void process(point points[],  int n)
	{
	    int[] skyline=new int[10000];
	    for(int i=0;i<10000;i++)
	    	skyline[i]=0;
	    int maxlen;
	    point tmp;
	  //sort the input time;
	  for(int i=0;i<points.length-1;i++)
	  {
		  if(points[i].time>points[i+1].time)
		  {
			  tmp = points[i];
			  points[i]=points[i+1];
			  points[i+1]=tmp;
			  
		  }
	  }
	  maxlen = points[n-1].end_time;
	  System.out.println("maxlen:"+maxlen);
	  //max colors;
	  int[] color = new int[n];
	  int count = 0;
	  color[count++] = points[0].end_time;
	  System.out.println("["+points[0].time+","+points[0].end_time+")"+"color:"+count);
	  for(int k=points[0].time;k<points[0].end_time;k++)
	        {
	            if(skyline[k]<count)
	                skyline[k]=count;
	            
	        }
	  int i;
	  int j;
	  //color the graph 
	  for (i = 1; i < n; i++)
	  {
	    if (points[i].is_start == 1)//if this point isn't start point ,continue;
	    {
	      for (j = 0; j < count; j++)
	      {
	            if (color[j] <= points[i].time)//find existed color for this point; 
	            {
	                color[j] = points[i].end_time;
	                int x = j+1;
	                System.out.println( "["+points[i].time+","+points[i].end_time+")"+ "color:"+x);
	                for(int k=points[i].time;k<points[i].end_time;k++)
	                {
	                    if(skyline[k]<x)
	                        skyline[k]=x;
	                }
	                break;
	            }
	      }
	      if (j == count)//add a new color;
	      {
	            color[count++] = points[i].end_time;
	            int x = j+1;
	            System.out.println( "["+points[i].time+","+points[i].end_time+")"+ "color:"+x);
	            for(int k=points[i].time;k<points[i].end_time;k++)
	                {
	                    if(skyline[k]<count)
	                        skyline[k]=count;
	                }
	      }
	    }
	  }
	  System.out.println( "color number:"+count);
	  int cost=0;
	  for(i=0;i<maxlen;i++)
	  {
	    cost += skyline[i];
	  }
	  System.out.println("cost:"+cost);
	  
	}
    public static void main(String []args) throws IOException {
    	  
    	  int rows=4;
    	  //rows = (int)System.in.read();
    	  //2*rows point
    	  //point p1 = new point();
    	  //point p2 = new point();
    	  point []points = new point[rows];
    	  //point p;
    	  int n = rows;
    	  //point p;
    	  int start_time;
    	  int end_time;
    	  int[][] intervals={{0,4},{2,5},{5,7},{6,9}};
    	  while (rows>0)
    	  {
    		rows--;
    	    int id = n - rows - 1;
    	    try{
    	    	   //read = System.in.read();
    	    start_time =  intervals[n-1-rows][0];
        	end_time = intervals[n-1-rows][1];
    	    //start_time =  (int) System.in.read();
    	    //end_time = (int)System.in.read();	  
    	    //System.out.println();
    	    points[id] = new point();
    	    //points[2*id+1] = new point();
    	    points[id].set_time(start_time);
    	    points[id].set_end_time (end_time);
    	    points[id].set_is_start (1);    
    	   // points[2*id+1].set_time (-1);
    	    //points[2*id+1].set_end_time(end_time);
    	    //points[2*id+1].set_is_start (0);   
    	  }
    	    
    	  catch(Exception e){
	    	   e.printStackTrace();
	    	 }
    	  }
    	  process(points, n);
    	  
    	  return;
    }
}
