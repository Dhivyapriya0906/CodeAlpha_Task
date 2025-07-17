 import java.util.*;
public class task2
{
	public static void main(String[] args) {
	    Scanner scan=new Scanner(System.in);
	    System.out.println("How many students to be entered:");
	    int n=scan.nextInt();
	    studentData(n);
	}
	public static void studentData(int n){
	    ArrayList<String> names=new ArrayList<>();
	    ArrayList<Integer> marks= new ArrayList<>();
	    Scanner scan=new Scanner(System.in);
	    for(int i=0;i<n;i++){
	        scan.nextLine(); 
	        System.out.print("Enter student name:");
	        String name=scan.nextLine();
	        names.add(name);
	        System.out.print("Enter student marks:");
	        int mark=scan.nextInt();
	        marks.add(mark);
	        
	    }
	        updateResult(names,marks);
	    
	}
	 public static void updateResult(ArrayList<String> names,ArrayList<Integer> marks){
	     int avg=0,tot=0;
	     for(int i=0;i<marks.size();i++){
	         tot+=marks.get(i);
	     }
	     //find max, min marks and name 
	     avg=tot/marks.size();
	     int max_mark=marks.get(0);
	     String max_name=names.get(0);
	     int min_mark=marks.get(0);
	     String min_name=names.get(0);
	     for(int i=0;i<names.size();i++){
	        if(marks.get(i)>max_mark) {
	            max_mark=marks.get(i);    //assign max mark
	            max_name= names.get(i);   //assign max name
            }
            if(marks.get(i)<min_mark){
                min_mark=marks.get(i);    //assign min marks
                min_name=names.get(i);    //assign min name
            }
	     }
	     System.out.println("-------------------------------------------");
	     System.out.println("Summary Report.....");
	     System.out.println("Total no.of Students: "+marks.size());
	     System.out.println("Highest mark :"+max_mark+" name : "+max_name);
	     System.out.println("Lowest mark : "+min_mark+" name : " + min_name);
	     System.out.println("Average of the students marks : "+avg);
	     
	 
	}
}
    
