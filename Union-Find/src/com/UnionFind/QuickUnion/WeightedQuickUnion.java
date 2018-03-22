package com.UnionFind.QuickUnion;

public class WeightedQuickUnion {

	//父连接组
	private int [] id;
	
	//各个节点所对应的分量大小
	private int [] size;
	
	//连通分量的数量
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
	
	//返回连通分量的数量
	public int count()
	{
		return count;
	}
	
	//找到跟节点
	public int find(int p)
	{
		while(p != id[p])
			p = id[p];
		return p;
	}
	
	//判断p 和 q是不是连通
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
	
	//将两个每关联的节点合并到同一个分量中
	public void union(int p, int q)
	{
		int idp = find(p);
		int idq = find(q);
		
		//如果相同
		if(idp == idq) return;
		
		if(size[p] > size[q])
		{
			id[q] = p;
			
			//权重变化
			size[p] +=size[q];
		}
		if(size[p] < size[q])
		{
			id[p] = q;
			size[q] += size[p];
		}
		//连通分量减少
		count--;
	}
}
