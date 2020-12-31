package kr.ac.sejong.da.first_project;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;

import kr.ac.sejong.da.first_project.MyTreeSet.TreeComp;

public class MyTreeSet<E> implements NavigableSet<E> {
	private MyNode<E> root;
	private int height;

	public MyTreeSet() {
		this.root = null;
		this.height = 0;
	}

	@Override
	public boolean add(E e) {
		root = add(e, root);
		return true;
	}

	// 재귀적으로 삽입후 트리를 fix
	public MyNode<E> add(E e, MyNode<E> tmp) {
		Comparator<E> comp = (Comparator<E>) this.comparator();

		// 루트가 비어있을 경우에는 값을 바로 루트에 삽입
		if (tmp == null) {
			tmp = new MyNode(e);

		}
		// 들어온 데이터가 더 부모의 값보다 더 작은 경우
		else if (comp.compare(e, tmp.getData()) < 0) {
			tmp.setLeft(add(e, tmp.getLeft()));
			// 왼쪽과 오른쪽의 높이 차이가 2보다 크면 rotation을 통해 fix
			if (height(tmp.getLeft()) - height(tmp.getRight()) >= 2) {
				// 들어온 값이 left의 왼쪽 자식일때는(/ 이모양) 단일 회전
				if (comp.compare(e, tmp.getLeft().getData()) < 0) {
					tmp = rotateLeft(tmp);
				}
				// 오른쪽 자식이면(< 이모양)double 회전
				else
					tmp = rotateDoubleLeft(tmp);
			}
		}
		// 들어온 데이터가 부모의 값보다 더 큰 경우
		else if (comp.compare(e, tmp.getData()) > 0) {
			tmp.setRight(add(e, tmp.getRight()));
			// 높이 차이가 2보다 크면 rotation을 통해 fix
			// 자식들이 null인 경우도 고려해야함 -> null인 경우는 높이가 -1이므로 이를 반환하도록 함수를 작성함
			if (height(tmp.getRight()) - height(tmp.getLeft()) >= 2) {
				// 들어온 값이 right의 오른쪽 자식이면 오른쪽 단일 회전
				if (comp.compare(e, tmp.getRight().getData()) > 0) {
					tmp = rotateRight(tmp);
				}
				// 왼쪽 자식이면 double 회전
				else
					tmp = rotateDoubleRight(tmp);
			}
		}
		// 이외의 경우는 중복된 값이 들어온 경우이므로 삽입을 수행하지 않음
		// tmp의 높이를 업데이트 - 왼쪽과 오른쪽 자식 중에 더 크기가 큰 높이값을 취한 뒤 하나 증가시킨 값
		tmp.setHeight(max(height(tmp.getLeft()), height(tmp.getRight())) + 1);
		return tmp;
	}

	public int height(MyNode<E> p) {
		// null일 경우에는 -1 반환
		if (p == null) {
			return -1;
		} else
			return p.getHeight();
	}

	public MyNode<E> rotateLeft(MyNode<E> p) {
		MyNode<E> q = p.getLeft(); // q는 p의 왼쪽 자식으로 할당
		// p의 왼쪽을 q의 오른쪽자식으로 할당
		p.setLeft(q.getRight());
		// q의 오른쪽을 p로 할당
		q.setRight(p);
		// p와 q 각각의 왼쪽과 오른쪽자식의 높이를 비교후, 큰 값+1로 높이를 업데이트
		p.setHeight(max(height(p.getLeft()), height(p.getRight())) + 1);
		// q의 경우는 오른쪽에 p를 자식으로 가지게 되었으므로 왼쪽자식의 높이와 p의 높이를 비교
		q.setHeight(max(height(q.getLeft()), height(p)) + 1);
		return q;
	}

	public MyNode<E> rotateRight(MyNode<E> q) {
		MyNode<E> p = q.getRight(); // 인자로 들어온 q의 오른쪽 자식을 p에 할당
		// q의 오른쪽을 p의 왼쪽자식으로 할당
		q.setRight(p.getLeft());
		// p의 왼쪽을 q로 할당
		p.setLeft(q);

		// 높이 업데이트
		q.setHeight(max(height(q.getLeft()), height(q.getRight())) + 1);
		p.setHeight(max(height(q), height(p.getRight())) + 1);
		return p;
	}

	public MyNode<E> rotateDoubleLeft(MyNode<E> p) {
		// p의 오른쪽 자식을 right회전을 통해 / 이 모양으로 만들어 준 뒤에
		p.setLeft(rotateRight(p.getLeft()));
		// left회전을 수행. 즉 LR 회전
		p = rotateLeft(p);
		return p;
	}

	public MyNode<E> rotateDoubleRight(MyNode<E> q) {
		// q의 왼쪽 자식을 left회전을 통해 대각선 모양으로 만들고 나서
		q.setRight(rotateLeft(q.getRight()));
		// right 회전 수행. 즉 RL 회전
		q = rotateRight(q);
		return q;
	}

	public int max(int x, int y) {
		if (x > y) {
			return x;
		} else
			return y;
	}

	@Override
	public boolean contains(Object o) {
		return search(root, o);
	}

	public boolean search(MyNode<E> tmp, Object o) {
		boolean flag = false;
		// root가 비어있을 경우는 바로 false 반환하며 종료
		if (root == null) {
			return false;
		}

		Comparator<E> comp = (Comparator<E>) this.comparator();
		while (true) {
			// 외부노드이면 종료
			if (tmp == null) {
				break;
			}
			if (comp.compare((E) o, tmp.getData()) == 0) {
				// 같은 값을 찾으면 flag를 true로 변경하고 반복문 종료
				flag = true;
				break;
			} else if (comp.compare((E) o, tmp.getData()) < 0) {
				// 찾고자 하는 값이 tmp 값보다 작으면 왼쪽 노드로 이동
				tmp = tmp.getLeft();
			} else {
				// 찾고자 하는 값이 tmp 값보다 크면 오른쪽 노드로 이동
				tmp = tmp.getRight();
			}
			// 재귀적으로 탐색
			flag = search(tmp, o);
		}
		return flag;
	}

	@Override
	public E first() {
		// 가장 왼쪽에 있는 리프 노드
		MyNode<E> tmp = root;
		while (true) {
			if (tmp.getLeft() == null) {
				return tmp.getData();
			} else {
				tmp = tmp.getLeft();
			}
		}
	}

	@Override
	public E last() {
		// 가장 오른쪽에 있는 리프 노드
		MyNode<E> tmp = root;
		while (true) {
			if (tmp.getRight() == null) {
				return tmp.getData();
			} else
				tmp = tmp.getRight();
		}
	}

	@Override
	public Comparator<? super E> comparator() {
		return new TreeComp();
	}

	public class TreeComp implements Comparator {

		public TreeComp() {
			super();
		}

		@Override
		public int compare(Object o1, Object o2) {
			// object가 정수 형태로 저장되어 있는 이메일 id를 담고 있으므로
			// 임의로 정수로 형변환 하여 쉽게 비교하도록 한다.
			if ((Integer) o1 < (Integer) o2) {
				return -1;
			} 
			else if((Integer) o1 > (Integer) o2) {
				// o1 > o2인 경우
				return 1;
			} else return 0;
		}
	}

	@Override
	public int size() {
		int size = 0;

		return size;
	}

	@Override
	public boolean isEmpty() {
		if (root == null) {
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		root = null;
		return;
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
		// TODO Auto-generated method stub
		return false;
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
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E lower(E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E floor(E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E ceiling(E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E higher(E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E pollFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E pollLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<E> descendingSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> descendingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<E> headSet(E toElement, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<E> subSet(E fromElement, E toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<E> headSet(E toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<E> tailSet(E fromElement) {
		// TODO Auto-generated method stub
		return null;
	}
}
