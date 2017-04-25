package toporesolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TopoResolver {

	public static <T extends INode> void sort(List<T> nodes){

		Map<String,T> id2Node = new HashMap<>();
		for(T node:nodes){
			id2Node.put(node.luid(),node);
		}

		T head = resolve(nodes);

		while(head != null){
			nodes.add(head);
			head = id2Node.get(head.nextLuid());
		}
	}

	public static <T extends INode> T resolve(List<T> nodes){
		T head = null;
		if(!Utils.listEmpty(nodes)){
			final Map<String,T> id2Node = new HashMap<String,T>();
			final Map<String,ArrayList<Edge>> incomes = new HashMap<>();
			for(T n:nodes){
				id2Node.put(n.luid(), n);

				inc(incomes,n.luid(),n.nextLuid(),n.nextPriority());
				inc(incomes,n.luid(),n.nextLuidOfCloud(),n.nextPriorityOfCloud());

				ArrayList<Edge> in = incomes.get(n.luid());
				if(in == null){
					incomes.put(n.luid(), new ArrayList<Edge>());
				}
			}

			Set<Edge> edges = new HashSet<>();
			Set<String> topoed = new HashSet<>();

			INode cur = null;
			while(!Utils.listEmpty(nodes)){
				List<String> nexts = nexts(incomes);

				String next = chooseNode(edges,nexts);
				if(next == null){
					//��2�����������������
					//1. ��ǰѡ����ǵ�1����
					//2. �ƶ����ݲ�������������ͼ���ڶ����ͨ����
					//��Ϊ�������ݹ��ɵ�����ͼ�϶���ͬһ����ͨ�������Զ��������ͨ����Ľ�����ƶ�����
					Collections.sort(nexts, new Comparator<String>() {

						@Override
						public int compare(String o1, String o2) {

							INode n1 = id2Node.get(o1);
							INode n2 = id2Node.get(o2);

							if(n1 == null){
								return n2==null?0:-1/*��������ǰ��*/;
							}

							if(n2 == null){
								return 1/*��������ǰ��*/;
							}

							if(Utils.listEmpty(incomes.get(o1))){// ��Щ�����ȶ�Ϊ0
								return (int)(Math.max(n1.nextPriority(), n1.nextPriorityOfCloud()) - Math.max(n2.nextPriority(), n2.nextPriorityOfCloud()));
							}else{//һ�㲻���ߵ������Ϊ�˸����ں����ԣ����ǿ����������
								long priority1 = maxPriorityOfIncomes(id2Node, incomes.get(o1));
								long priority2 = maxPriorityOfIncomes(id2Node, incomes.get(o2));
								return (int)(priority1-priority2);//��Ȼ���ڱ��˵ĺ��棬��ȡ�ȵ�
							}
						}
					});


					next = nexts.get(0);
				}

				T node = id2Node.get(next);
				if(node != null){

					topoed.add(next);
					nodes.remove(node);

					if(node.nextLuid() != null && !topoed.contains(node.nextLuid())){//���Ѿ��ų����ĵ�û�б�Ҫ����ӱ���
						edges.add(new Edge(node.luid(),node.nextLuid(),node.nextPriority()));
					}
					if(node.nextLuidOfCloud() != null && !topoed.contains(node.nextLuidOfCloud())){//���Ѿ��ų����ĵ�û�б�Ҫ����ӱ���
						edges.add(new Edge(node.luid(),node.nextLuidOfCloud(),node.nextPriorityOfCloud()));
					}

					dec(incomes,node.luid(),node.nextLuid());
					dec(incomes,node.luid(),node.nextLuidOfCloud());

					if(node.deleted() == 0){
						if(cur != null){
							cur.updateNext(next);
						}else{
							head = node;
						}
						cur = node;
					}
				}

				incomes.remove(next);
			}

			if(cur != null) cur.updateNext(null);
		}

		return head;
	}

	private static String chooseNode(Set<Edge> choose, List<String> nodes){
		Edge target = null;
		String next = null;
		if(choose != null && nodes != null){
			for(String node:nodes){
				for(Edge e:choose){
					if(node.equals(e.to)){
						if(target == null || target.priority < e.priority){
							target = e;
							next = node;
						}
					}
				}
			}
		}

		if(target != null){
			choose.remove(target);
		}

		return next;
	}

	private static <T> long maxPriorityOfIncomes(Map<String,T> id2Node,List<Edge> incomes){
		long max = Long.MIN_VALUE;
		if(!Utils.listEmpty(incomes)){
			for(Edge edge:incomes){
				if(edge.priority > max){
					max = edge.priority;
				}
			}
		}

		return max;
	}

	private static List<String> nexts(Map<String,ArrayList<Edge>> incomes){
		List<String> nexts = new ArrayList<String>();
		Iterator<Entry<String,ArrayList<Edge>>> itr = incomes.entrySet().iterator();
		int min = Integer.MAX_VALUE;
		while(itr.hasNext()){
			Entry<String,ArrayList<Edge>> entry = itr.next();
			if(entry.getValue().size() > min){
				continue;
			}


			if(entry.getValue().size() < min){
				min = entry.getValue().size();
				nexts.clear();
			}

			nexts.add(entry.getKey());
		}

		return nexts;
	}

	private static void inc(Map<String,ArrayList<Edge>> m,String from,String to,long priority){
		if(m != null && !Utils.stringEmpty(to)){
			ArrayList<Edge> v = m.get(to);
			if(v == null){
				v = new ArrayList<Edge>();
				m.put(to,v);
			}


			v.add(new Edge(from,to,priority));
		}
	}

	private static void dec(Map<String,ArrayList<Edge>> m,String from,String to){
		if(m != null && !Utils.stringEmpty(to)){
			ArrayList<Edge> v = m.get(to);

			if(v != null){
				ArrayList<Edge> dels = new ArrayList<>();
				for(Edge edge:v){
					if(edge.from.equals(from)){
						dels.add(edge);
					}
				}

				v.removeAll(dels);
			}
		}
	}


	private static class Edge{
		String from;
		String to;

		public Edge(String from,String to,long priority){
			this.from = from;
			this.to = to;
			this.priority = priority;
		}

		long priority;
	}


	public interface INode{

		public static final String TAIL = "tail";

		public String nextLuid();
		public String nextLuidOfCloud();
		public long nextPriority();
		public long nextPriorityOfCloud();
		public String luid();
		public int deleted();

		public void updateNext(String identifier);

	}
}

