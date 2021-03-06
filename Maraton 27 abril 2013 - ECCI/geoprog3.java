import java.io.*;
import java.util.*;
public class geoprog3{
	static int atoi(String n){return Integer.parseInt(n);}
	static HashMap<Integer, Integer> facto(int n){
		HashMap<Integer, Integer> facs = new HashMap<Integer, Integer>();
		int fin = (int)Math.sqrt(n);
		int n2 = 0;
		if(n==1 || n==0)return facs;
		while((n&1)==0){
			n2++;
			n>>=1;
		}
		if(n2!=0)
			facs.put(2, n2);
		int div = 3;
		int ccc = 0;
		while(n!=1 && div<=fin){
			if(n%div==0){
				//System.out.println(n+" div "+n);
				ccc++;
				n/=div;
				}
			else{
				//System.out.println(div+" no div "+n);
				if(ccc!=0){
					//System.out.println("pongo "+div+" "+ccc+" veces");
					facs.put(div, ccc);
					}
				ccc=0;
				div+=2;
			}
		}
		if(n==1 && ccc!=0)
			facs.put(div, ccc);
		if(n!=1)
			facs.put(n, 1);
		return facs;
	}
	static HashMap<Integer, Integer> merge(HashMap<Integer, Integer> h1, HashMap<Integer, Integer> h2){
		HashMap<Integer, Integer> ans = new HashMap<Integer, Integer>(h1);
		for(Integer c: h2.keySet()){
			if(ans.containsKey(c))
				ans.put(c, ans.get(c)+h2.get(c));
			else
				ans.put(c, h2.get(c));
		}
		return ans;
	}
	static boolean raro(int a, int b){
		return (a==0 || b<=1);//genera una constante para ese conjunto.
	}
	static void agregar(HashMap<Integer, Integer> pb1, HashMap<Integer, Integer>pq1, HashSet<HashMap<Integer, Integer>> conj, int n1){
		//System.out.println("pb "+pb1);
		//System.out.println("pq "+pq1);
		for(int i=0;i<n1;i++){
					HashMap<Integer, Integer> ccc = (HashMap<Integer,Integer>)pb1.clone();
					conj.add(ccc);
					//System.out.println("anado" +pb1);
					//exponenciamos
					for(Integer c: pq1.keySet()){
						if(!pb1.containsKey(c))
							pb1.put(c, 0);
						pb1.put(c, pb1.get(c)+pq1.get(c));
					}
				}
		//System.out.println("conjunto al final \n"+conj);
	}
	public static void main(String jkfdfd[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		//System.out.println(facto(48));
		int cont = 0;
		while((input = br.readLine())!=null && !input.equals("")){
			System.out.println("Caso "+ ++cont);
			int b1, q1, n1, b2, q2, n2;
			String div[] = input.split(" ");
			b1 = atoi(div[0]);
			q1 = atoi(div[1]);
			n1 = atoi(div[2]);
			b2 = atoi(div[3]);
			q2 = atoi(div[4]);
			n2 = atoi(div[5]);
			
			//bonito el if :) me lo quedo
			if(b2==0 || q2<=1){
				//magic swap
				b2^=b1;b1^=b2;b2^=b1;
				q2^=q1;q1^=q2;q2^=q1;
				n2^=n1;n1^=n2;n2^=n1;
			}
			if(raro(b1, q1)){
				boolean ya = false;
				HashSet<Long> conj = new HashSet<Long>();
				conj.add((long)b1);
				if(n1>1)conj.add((long)b1*(long)q1);
				long act = b2;
				for(int i=0;i<n2 && !ya;i++){
					conj.add(act);
					act*=q2;
					if(act>500000000){//la constante generada no es mas grande que 500 000 000
						System.out.println(n2-i-1+conj.size());//los que faltan mas lo que llevaba
						ya = true;
						}
				}
				if(!ya){
					ya = true;
					System.out.println(conj.size());
				}
			}else{
				//con HashMap para que funcione el hashset con arreglos no se puede :( o mejor, es mas tedioso
				HashSet<HashMap<Integer, Integer>> conj = new HashSet<HashMap<Integer,Integer>>();
				HashMap<Integer, Integer> pb1 = facto(b1);
				HashMap<Integer, Integer> pq1 = facto(q1);
				HashMap<Integer, Integer> pb2 = facto(b2);
				HashMap<Integer, Integer> pq2 = facto(q2);
				//System.out.println("111");
				agregar(pb1, pq1, conj, n1);
				//System.out.println("222");
				agregar(pb2, pq2, conj, n2);
				//StringBuilder sb = new StringBuilder("");
				//for(HashMap<Integer, Integer> c: conj)
					//sb.append(c).append("\n");
				//System.out.println(sb);
				System.out.println(conj.size());
			}
		}
	}
}
