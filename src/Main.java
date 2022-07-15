import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] ints = {5,5,5,5,4,4,4,4,3,3,3,3};
        System.out.println(solution.makesquare(ints));
    }
}
class Solution {
    private int oneSideLength;
    private List<Integer> sticks;
    private int[] sums = {0,0,0,0};

    public boolean isLengthValid(int[] arr){
        int sum = 0;
        for(int j = 0;j<arr.length;j++){
            sum+=arr[j];
        }
        if (sum%4==0)
            return true;
        return false;
    }

    public boolean makesquare(int[] matchsticks) {
        if (!isLengthValid(matchsticks))
            return false;
        try {
            setOneSideLength(matchsticks);
        }
        catch (Exception e){
            return false;
        }
        this.sticks= Arrays.stream(matchsticks).boxed().collect(Collectors.toList());
        Collections.sort(this.sticks, Collections.reverseOrder());
        return this.dfs(0);

    }

    private boolean dfs(int i) {
        if (i==this.sticks.size())
            return this.sums[0]==this.sums[1]&&this.sums[1]==this.sums[2]&&this.sums[2]==this.sums[3];
        int stick = this.sticks.get(i);
        for(int j = 0;j<4;j++){
            if (stick+sums[j]<=oneSideLength){
                sums[j]+=stick;
                if(this.dfs(i+1))
                    return true;
                sums[j]-=stick;
            }
        }
        return false;

    }

    private void setOneSideLength(int[] matchsticks) throws Exception {
        int sum = 0;
        for(int j = 0;j<matchsticks.length;j++){
            sum+=matchsticks[j];
        }
        if (sum%4==0)
            this.oneSideLength=sum/4;
        else throw new Exception("length is invalid");
    }

}