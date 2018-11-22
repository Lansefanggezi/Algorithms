package www.twosum.com;

import java.util.HashMap;
import java.util.Map;

public class Solution {

	public static void main(String[] args) {

	}
	
	public static int[] twosum(int[] nums,int target){
		Map map = new HashMap<>();
		for(int i = 0; i<nums.length; i++){
			map.put(nums[i], i);
			if (map.containsKey(target - nums[i])) {
				return new int[]{i,(Integer)map.get(target - nums[i])};
			}
		}
		return null;
	}
}
