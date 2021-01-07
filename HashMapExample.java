package kc.ar.sejong.da.prj3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class HashMapExample {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("C:\\\\Users\\\\김수지\\\\Desktop\\\\data.txt"));

		// HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		HashMap<Integer, HashSet<Integer>> rSet = new HashMap<Integer, HashSet<Integer>>();
		while (true) {
			String line = br.readLine();
			if (line == null) {
				break;
			}
			if (line.startsWith("#")) {
				continue;
			}
			String[] ar = line.split("\\t");
			int left = Integer.parseInt(ar[0]);
			int right = Integer.parseInt(ar[1]);
			if (!rSet.containsKey(right)) {
				HashSet<Integer> s = new HashSet<Integer>();
				s.add(left);
				rSet.put(right, s);
			} else {
				HashSet<Integer> existing = rSet.get(right);
				existing.add(left);
				rSet.put(right, existing);
			}
		}

		HashMap<Integer, HashSet<Integer>> rrSet = new HashMap();
		// rSet를 순회하면서 이메일을 받은사람에게 받은사람을 구한다
		Set<Entry<Integer, HashSet<Integer>>> entrySet = rSet.entrySet();
		Iterator<Entry<Integer, HashSet<Integer>>> entryItr = entrySet.iterator();

		while (entryItr.hasNext()) {
			Entry<Integer, HashSet<Integer>> entry = entryItr.next();
			// val Set는 rSet의 value인 받은 사람들 집합 ex) k1<-[k0,k3,k4]일때 []안의 값을 가짐(k1에게 메일
			// 보낸사람들)
			HashSet<Integer> val = entry.getValue();
			for (Integer i : val) {
				// ㄴㄴ 여긴 데이터 손상을 막기위해 복붙해주는거임
				HashSet<Integer> rSetI = null;
				if (rSet.get(i) != null) {
					// (clone)
					rSetI = (HashSet<Integer>) rSet.get(i).clone();
				} else {
					// 없을땐 null이었으니까 그냥 새로운 set만들어주기
					rSetI = new HashSet<Integer>();
				}
				// 받은 사람들의 집합의 원소 i로 get(i)한게 null이 아니다 == 그사람들에게 메일을 보낸 사람들의 집합이 있다
				
				if (!rrSet.containsKey(entry.getKey())) {
					rrSet.put(entry.getKey(), rSetI);
				} else {
					HashSet<Integer> ex = rrSet.get(i);
					if (ex == null) {
						ex = new HashSet<Integer>();
					}
					if (rSet.containsKey(i)) {
						ex.addAll(rSet.get(i));
						rrSet.put(entry.getKey(), ex);
					}
				}
			}

		}

	}

}
