package kr.ac.sejong.da.first_project;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<E> implements List<E> {

	private Object[] data;
	private int size; // list에 들어있는 원소의 개수를 관리하는 변수

	public MyArrayList() {
		data = new Object[0];
		size = 0;
	}

	@Override
	public boolean add(E e) {
		// data에 값이 하나도 들어있지 않으면 바로 삽입
		if (size == 0) {
			Object[] tmp = new Object[1];
			tmp[0] = e;
			data = tmp;
			size += 1;
			return true;
		}
		// 크기 확인 후, 벗어나면 array doubling 해야 함
		// 현재까지 크기가 size이므로 한 개 더 넣을시 size + 1이 배열의 길이를 넘어가는지 확인
		if (size + 1 > data.length) {
			// array doubling
			Object[] newData = new Object[data.length * 2];
			// 현재 들어있는 값을 길이 두배의 새로운 배열에 복사
			for (int i = 0; i < size; i++) {
				newData[i] = data[i];
			}
			data = newData;
			data[size] = e;
			size += 1;
			return true;
		} else if (size + 1 <= data.length) {
			data[size] = e;
			size += 1;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ListIterator<E> listIterator() {
		return new ListItr();
	}

	// ListIterator를 구현하기 위한 class인 ListItr을 생성, Itr을 상속받음
	private class ListItr extends Itr implements ListIterator<E> {

		public ListItr() {
			super();
		}

		public ListItr(int index) {
			super();
			cursor = index;
		}

		@Override
		public boolean hasPrevious() {
			if (cursor == 0) { // cursor는 리스트의 다음 요소를 가르키므로 cursor가 0이면 더이상 앞의 원소가 존재하지 않음
				return false;
			} else {
				return true;
			}
		}

		@Override
		public E previous() {
			E elem;
			// 더이상 앞으로 이동할 수 없으면 null 반환
			if (hasPrevious() == false) {
				return null;
			}
			cursor -= 1; // 먼저 cursor를 하나 감소시킨 후
			elem = (E) data[cursor]; // elem에 원소 할당
			return elem;
		}

		@Override
		public int nextIndex() {
			// 다음 원소가 없으면 -1 반환
			if (hasNext() == false) {
				return -1;
			}
			return cursor;
		}

		@Override
		public int previousIndex() {
			// 이전 원소가 없으면 -1 반환
			if (hasPrevious() == false) {
				return -1;
			}
			return cursor - 1;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}

		@Override
		public void set(E e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void add(E e) {
			// TODO Auto-generated method stub

		}
	}

	//

	@Override
	public int size() {
		// *length는 배열의 총 크기
		return size;
	}

	@Override
	public boolean isEmpty() {
		if (data == null) {
			return true;
		}
		if (size == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(Object o) {
		for (Object i : data) {
			if (i == o) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	// Iterator을 구현하기 위한 class인 Itr을 생성
	public class Itr implements Iterator<E> {
		int cursor; // iterator의 다음 위치를 관리(linked list의 next pointer같은 느낌)
		int lastElem; // list의 마지막 원소를 가르킴

		public Itr() {
			cursor = 0;
			lastElem = -1;
		}

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
			elem = (E) data[cursor];
			cursor += 1;
			return elem;
		}
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		if (!this.contains(o)) {
			return false;
		}
		int idx = this.indexOf(o); // 원소의 인덱스를 구하고
		for (int i = idx; i < size - 1; i++) {
			data[i] = data[i + 1]; // 하나씩 앞으로 가져옴
		}
		size--; // 삭제후 크기를 하나 줄임
		data[size] = null;
		return true;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			data[i] = null; // 모든 값을 null로 초기화
		}
		size = 0; // 크기도 0으로 바꿔줌
	}

	@Override
	public E get(int index) {
		// 인덱스가 배열의 크기를 넘어가면 null 반환
		if (index > size) {
			return null;
		}
		return (E) data[index];
	}

	@Override
	public E set(int index, E element) {
		data[index] = element;
		return null;
	}

	@Override
	public void add(int index, E element) {
		if (index == 0) {
			Object[] tmp = new Object[1];
			tmp[0] = element;
			data = tmp;
			size += 1;
			return;
		}
		// index의 크기가 배열의 길이를 넘어가면 array doubling
		if (index >= data.length) {
			Object[] newData = new Object[data.length * 2];
			// 현재 들어있는 값을 길이 두배의 새로운 배열에 복사
// 해당 인덱스 전까지 쭉 복사후 해당 인덱스에 원소 삽입, 나머지는 뒤이어 복사하는 것으로 수정 필요
			for (int i = 0; i < size; i++) {
				newData[i] = data[i];
			}
			data = newData;

		}
		data[index] = element;
		size += 1;
	}

	@Override
	public E remove(int index) {
		if (index >= size || index < 0) {
			return null;
		}
		E element = (E) data[index];

		// 삭제할 인덱스부터 배열의 끝까지 한칸씩 앞으로 복사함
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		size--; // 삭제후 크기를 하나 줄임
		data[size] = null; // 가장 마지막 원소 위치 초기화
		return element;
	}

	@Override
	public int indexOf(Object o) {
		for (int i = 0; i < size; i++) {
			if (data[i] == o) {
				return i;
			}
		}
		return -1; // false
	}

	@Override
	public int lastIndexOf(Object o) {
		// 리스트 안에 동일한 객체가 2개 이상 존재할 때 가장 뒤에 위치한 객체의 인덱스 반환
		int idx = -1;
		for (int i = 0; i < size; i++) {
			if (data[i] == o) {
				idx = i;
			}
		}
		return idx;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return new ListItr(index);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// fromIndex와 toIndex의 값이 같거나, fromIndex가 더 크다면 null 반환
		if (fromIndex >= toIndex) {
			return null;
		}
		// fromIndex, toIndex가 범위 리스트 범위 밖이면 null 반환
		if (fromIndex < 0 || toIndex > size) {
			return null;
		}
		List<E> sub = new MyArrayList<E>();
		for (int i = fromIndex; i < toIndex; i++) {
			sub.add((E) data[i]);
		}

		return sub;
	}

	public String toString() {
		String str = new String();
		if (size == 0) {
			str = "ArrayList is NULL";
		}
		for (int i = 0; i < size; i++) {
			str += data[i] + " ";
		}
		return str;
	}

}
