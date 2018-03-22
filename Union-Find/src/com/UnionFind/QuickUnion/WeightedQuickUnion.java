package com.UnionFind.QuickUnion;

public class WeightedQuickUnion {

	//��������
	private int [] id;
	
	//�����ڵ�����Ӧ�ķ�����С
	private int [] size;
	
	//��ͨ����������
	private int count;
	
	public WeightedQuickUnion(int N)
	{
		count = N;
		id = new int[N];
		for (int i = 0 ; i< N ; i++)
			id[i] = i;
		size = new int[N];
		for (int i = 0 ; i< N ; i++)
			size[i] = 1;
	}
	
	//������ͨ����������
	public int count()
	{
		return count;
	}
	
	//�ҵ����ڵ�
	public int find(int p)
	{
		while(p != id[p])
			p = id[p];
		return p;
	}
	
	//�ж�p �� q�ǲ�����ͨ
	public boolean connected(int p , int q)
	{
//		int idp = find(p);
//		int idq = find(q);
//		if(idp == idq)
//			return true;
//		else
//			return false;
		return find(p) == find(q);
	}
	
	//������ÿ�����Ľڵ�ϲ���ͬһ��������
	public void union(int p, int q)
	{
		int idp = find(p);
		int idq = find(q);
		
		//�����ͬ
		if(idp == idq) return;
		
		if(size[p] > size[q])
		{
			id[q] = p;
			
			//Ȩ�ر仯
			size[p] +=size[q];
		}
		if(size[p] < size[q])
		{
			id[p] = q;
			size[q] += size[p];
		}
		//��ͨ��������
		count--;
	}
}
