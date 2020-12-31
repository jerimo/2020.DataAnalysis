package kr.ac.sejong.da.first_project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {

	private MyBucket<E>[] buckets;
	private Integer size; //

	@SuppressWarnings("unchecked")
	public MyHashSet(int bucketSize) { // 생성자
		buckets = new MyBucket[bucketSize];
		size = 0;
	}

	public int size() {
		int size = 0;
		for (MyBucket<E> b : buckets) {
			if (b != null)
				size += b.getBucketList().size(); // 각 버킷 리스트의 크기를 모두 더한 값을 구한다
		}
		return size;
	}

	@Override
	public boolean isEmpty() {
		if (size == 0) { // 크기가 0이면 true 반환
			return true;
		} else
			return false;
	}

	// hashMap의 buckets의 인덱스 위치를 알기 위한 해쉬값 반환
	public int hash(Object o) {
		return o.hashCode() % 70003;
	}

	@Override
	public boolean contains(Object o) {
		int index = hash(o);
		if (buckets[index] == null) {
			return false;
		}
		MyBucket<E> b = new MyBucket<E>(index, buckets[index].getBucketList()); // 해당하는 인덱스의 버킷 원소를 받아옴

		ArrayList<E> bList = b.getBucketList();
		// bucketList가 null인 경우는 add에서 따로 처리를 하고 contains를 호출함

		// b의 bucketList안에 o가 존재하면 true
		if (bList.contains(o)) {
			return true;
		}

		return false;
	}

// 이터레이터 다시 구현할 필요가 있음 해시 값에 대한 리스트 순회를 해야함
	// 이터레이터 키를 순회??
	// ㄴㄴ toList로 set을 리스트로 만들고 그 리스트를 순회
	@Override
	public Iterator<E> iterator() {
		return new Itr(); // MyArrayList에서 작성한 부분 수정 사용
	}

	// Iterator을 구현하기 위한 class인 Itr을 생성
	private class Itr implements Iterator<E> {
		E[] list;
		int cursor; // iterator의 다음 위치를 관리(linked list의 next pointer같은 느낌)


		public Itr() {
			ArrayList<E> setToArray = new ArrayList<E>();
			for(MyBucket b : buckets){
				// 각각의 버킷에 대해 bucketList를 받아오고
				ArrayList<E> bList = b.getBucketList();
				for(int i=0;i<bList.size();i++) {
					// 모두 setToArray에 삽입하여 하나의 리스트로 만들어 줌
					setToArray.add(bList.get(i));
				}
			}
			
			list = (E[]) setToArray.toArray();
			cursor = 0;
		}

// 수정 필요
		@Override
		public boolean hasNext() {
			// cursor는 다음 원소의 위치를 가르키고 있으므로 size와 값이 같으면 마지막 원소를 의미함
			if (cursor == size) {
				return false;
			}
			return true;
		}

		@Override
		public E next() {
			E elem;
			if (hasNext() == false) {
				return null;
			}
			elem = (E) list[cursor];
			cursor += 1;
			return elem;
		}
		
	}

	@Override
	public Object[] toArray() {
		ArrayList<E> setToArray = new ArrayList<E>();
		for(MyBucket b : buckets){
			// 각각의 버킷에 대해 bucketList를 받아오고
			ArrayList<E> bList = b.getBucketList();
			for(int i=0;i<bList.size();i++) {
				// 모두 setToArray에 삽입하여 하나의 리스트로 만들어 줌
				setToArray.add(bList.get(i));
			}
		}
		
		Object[] arr = setToArray.toArray();
		
		return arr;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		ArrayList<T> setToArray = new ArrayList<T>();
		for(MyBucket b : buckets){
			// 각각의 버킷에 대해 bucketList를 받아오고
			ArrayList<T> bList = b.getBucketList();
			for(int i=0;i<bList.size();i++) {
				// 모두 setToArray에 삽입하여 하나의 리스트로 만들어 줌
				setToArray.add(bList.get(i));
			}
		}
		
		T[] arr = (T[]) setToArray.toArray();
		
		return arr;
	}

	@Override
	public boolean add(E e) {
		int index = hash(e);

		// bucketList 안에 값이 하나도 없을경우 바로 삽입하면 됨
		if (buckets[index] == null) {
			ArrayList<E> newList = new ArrayList<E>();
			newList.add(e);
			// buckets[index].setBucketList(newList);
			MyBucket<E> b = new MyBucket<E>(index, newList);
			this.buckets[index] = b;
			size += 1;
			return true;
		}

		// 다음으로는 e가 존재하는지 확인 (중복시 따로 add하지 않음)
		if (!this.contains(e)) {
			ArrayList<E> exList = buckets[index].getBucketList(); // exList에 기존 인덱스에 해당하는 bucketList를 받아옴
			exList.add(e);
			MyBucket<E> b = new MyBucket<E>(index, exList);
			buckets[index] = b;
			size += 1;
			return true;
		}

		// 중복된 값을 add하면 false를 반환
		return false;
	}

	@Override
	public boolean remove(Object o) {
		int index = hash(o);
		// 해당하는 인덱스의 bucketList가 비어있으면 값이 없는 것 이므로 false 반환
		if (buckets[index] == null) {
			return false;
		}
		ArrayList<E> bList = buckets[index].getBucketList();
		// 삭제하고자 하는 값을 찾으면
		if (bList.contains(o)) {
			bList.remove(o);
			size -= 1;
			return true;
		}
		return false;
	}

//수정필요
	@Override
	public boolean containsAll(Collection<?> c) { // c가 hashSet의 부분집합인지 확인
		Iterator<E> itr = iterator();
		boolean flag = false;
		while (itr.hasNext()) {
			if (c.contains(itr.next())) {
				return true;
			}
		}
		return false;
	}

//수정필요
	@Override
	public boolean addAll(Collection<? extends E> c) { // 합집합
		Iterator<E> itr = iterator(); // spliterator??
		boolean flag = false;
		while (itr.hasNext()) {
			E e = itr.next();
			if (!c.contains(e)) {
					this.add(e);
			}
		}
		return flag;
	}

	@Override
	public boolean retainAll(Collection<?> c) { // 교집합
		boolean flag = false; // 변경 여부를 알려주는 플래그

// 이터레이터 뻑났음..
		/*Iterator<E> itr = iterator();
		
		while (itr.hasNext()) { // 
			E elem=itr.next();
			if (!c.contains(elem)) { // c에 b가 들어있으면 set에 추가 >> c에 들어있는 값을 추가하는 게 아니라 들어있는걸 b에서 제거하는게 논리적으로 맞음
				this.remove(elem);
				flag = true; 
			}
		}
		//remove 사용을 위해서는 bucket이 아니라 hashSet을 사용, 따라서 iter 필요
		 
		return flag;*/
		 
		
		for (MyBucket<E> b : buckets) {
			if (b != null) { // bucketList에 값이 있으면
				// b는 buckets. 배열 안에 있는 각각의 bucket이고 그 리스트에서 값을 하나씩 읽어옴
				for (int i = 0; i < b.getBucketList().size(); i++) {
					// c에 들어있지 않는 값==b 차집합 c 이므로 이에 해당하는 값을 삭제
					if (!c.contains(b.getBucketList().get(i))) {
						b.getBucketList().remove(i);
						i--; // int i 로 순회중이므로 삭제한 경우 i값을 임의로 다시 하나 줄여줌
						// 플래그 값이 참이 아닐 경우만 바꿈
						if (flag != true) {
							flag = true;
						}
					}
				}
			}
		}

		return flag;
	}

	@Override
	public boolean removeAll(Collection<?> c) { // 차집합
		boolean flag = false;
		for (MyBucket<E> b : buckets) {
			if (b != null) { // bucketList에 값이 있으면
				// b는 buckets. 배열 안에 있는 각각의 bucket이고 그 리스트에서 값을 하나씩 읽어옴
				for (int i = 0; i < b.getBucketList().size(); i++) {
					// c에 들어있는 값을 뺀 것==b 차집합 c 이므로 이에 해당하는 값을 삭제
					if (c.contains(b.getBucketList().get(i))) {
						b.getBucketList().remove(i);
						i--; // int i 로 순회중이므로 삭제한 경우 i값을 임의로 다시 하나 줄여줌
						// 플래그 값이 참이 아닐 경우만 바꿈
						if (flag != true) {
							flag = true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}
}