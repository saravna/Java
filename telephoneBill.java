import java.util.*;
public class telephoneBill{
	public static int solution(String logs){
		int bill=0;
		String log[]=logs.split("\n");
		ArrayList<entry> ar1=new ArrayList<>();
		int h,m,s;
		for(int i=0;i<log.length;i++){
			char a[]=log[i].toCharArray();
			if(a[2]==':' && a[5]==':' && a[8]==',' && a[12]=='-' && a[16]=='-' && a.length==20
				&& a[10]!=0){
				h=Integer.parseInt(log[i].substring(0,2));
				m=Integer.parseInt(log[i].substring(3,5));
				s=Integer.parseInt(log[i].substring(6,8));
				if(h<100 && h>=0 && m<60 && m>=0 && s<60 && s>=0){
					ar1.add(new entry(log[i]));
				} else {
					return -1;
				}
			} else {
				return -1;
			}
		}
		ArrayList<entry2> ar2=new ArrayList<>();
		for(entry i:ar1){
			boolean flag=true;
			entry2 temp=null;
			for(entry2 j:ar2){
				if(i.num.equals(j.num)){
					flag=false;
					temp=j;
					break;
				}
			}
			if(flag){
				ar2.add(new entry2(i.num,i.sec));
			} else {
				temp.sec+=i.sec;
			}
		}
		entry2 temp=null;
		int max=0;
		for(entry2 i:ar2){
			if(i.sec>max){
				max=i.sec;
				temp=i;
			}
			if(i.sec==max){
				if(temp.num.compareTo(i.num)>0)
					temp=i;
			}
		}
		for(entry i:ar1){
			if(temp.num.equals(i.num))
				i.cost=0;
			bill+=i.cost;
		}
		return bill;
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String logs="";
		String temp="";
		int bill;
		while(true){
			temp=sc.nextLine();
			if(temp.equals(""))
				break;
			logs+=temp+"\n";
		}
		bill=solution(logs);
		if(bill==-1)
			System.out.println("Invalid Input");
		else
			System.out.println("Bill : "+bill);
	}
}
class entry{
	String time;
	String num;
	int h,m,s,sec,cost;
	public entry(String log){
		time=log.split(",")[0];
		num=log.split(",")[1];
		num=num.replaceAll("-","");
		h=Integer.parseInt(time.split(":")[0]);
		m=Integer.parseInt(time.split(":")[1]);
		s=Integer.parseInt(time.split(":")[2]);
		sec=(h*60*60)+(m*60)+s;
		if(sec<300)
			cost=sec*3;
		else{
			if(s>0)
				m++;
			if(m==60){
				h++;
				m=0;
			}
		cost=((h*60)+m)*150;
		}
	}
}
class entry2{
	String num;
	int sec;
	public entry2(String num,int sec){
		this.num=num;
		this.sec=sec;
	}
}