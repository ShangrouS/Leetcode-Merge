import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _305_Number_of_Islands_II {
    /**
     A 2d grid map of m rows and n columns is initially filled with water.
     We may perform an addLand operation which turns the water at position (row, col) into a land.
     Given a list of positions to operate, count the number of islands after each addLand operation.
     An island is surrounded by water and is formed by connecting
     adjacent lands horizontally or vertically.
     You may assume all four edges of the grid are all surrounded by water.
     Example:
     Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
     Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
     0 0 0
     0 0 0
     0 0 0
     Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
     1 0 0
     0 0 0   Number of islands = 1
     0 0 0
     Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
     1 1 0
     0 0 0   Number of islands = 1
     0 0 0
     Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
     1 1 0
     0 0 1   Number of islands = 2
     0 0 0
     Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
     1 1 0
     0 0 1   Number of islands = 3
     0 1 0
     We return the result as an array: [1, 1, 2, 3]


     题意：
        用一个m行n列的二维数组表示的地图，初始状态全部是水。我们用addLand这个操作表示把位置（row，col）的水变成陆地。给出一个位置的
        序列表示要把这一些位置变成陆地，每次操作后计算岛的数量。岛的定义：水平或垂直方向相连的周围全是水的陆地。可以假设地图的边界全部
        都是水。

        例如:
            给出 m = 3, n = 3, 位置序列 = [[0,0], [0,1], [1,2], [2,1]].
            起初，整个矩阵全是水. (假设0表示水，1表示陆地).
             0 0 0
             0 0 0
             0 0 0

            操作 #1: addLand(0, 0) 把grid[0][0]变成陆地.
             1 0 0
             0 0 0   岛的数量 = 1
             0 0 0

            操作 #2: addLand(0, 1) 把grid[0][1]变成陆地.
             1 1 0
             0 0 0   岛的数量 = 1
             0 0 0

            操作 #3: addLand(1, 2) 把grid[1][2]变成陆地.
             1 1 0
             0 0 1   岛的数量 = 2
             0 0 0

            操作 #4: addLand(2, 1) 把grid[2][1]变成陆地.
             1 1 0
             0 0 1   岛的数量 = 3
             0 1 0

        输出结果数组: [1, 1, 2, 3]

     思路：
        用一个一维数组roots表示题目中二维数组的父节点。position[x][y]可以映射为roots[n*x + y]。外层循环表示逐一添加点到地图
        中。内层循环表示每次添加完一个点，在四个方向上判断判断这个点与已有的点是否连通。定义find函数：roots[i]表示节点i所指向
        的节点，每调用一次find函数，相当于向上查找目前已知的该节点的根节点。对于每一个输入的点，调用find函数，如果find函数返回
        的位置不等于当前输入的位置（position ！= anoIsland），说明要添加的位置与当前位置是联通的（拥有同一祖先），添上这个点
        以后岛的数量不变（进入循环之前count++了这里要给它减回去）；否则(position == anoIsland)，说明在这个方向上当前要添加的
        点与已有点不联通，添上这个点会形成新的岛。

     复杂度：
        time : O(k^2)
        space : O(m * n)

     */
    int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if (m <= 0 || n <= 0) return res;

        int count = 0;
        int[] roots = new int[m * n];
        Arrays.fill(roots, -1);

        for (int[] pair : positions) {
            int position = n * pair[0] + pair[1];
            roots[position] = position;
            count++;

            for (int[] dir : dirs) {
                int x = pair[0] + dir[0];
                int y = pair[1] + dir[1];
                int curPos = n * x + y;
                if (x < 0 || x >= m || y < 0 || y >= n || roots[curPos] == -1) {
                    continue;
                }
                int anoIsland = find(roots, curPos);
                if (position != anoIsland) {
                    roots[position] = anoIsland;
                    position = anoIsland;
                    count--;
                }
            }
            res.add(count);
        }
        return res;
    }

    private int find(int[] roots, int i) {
        while (i != roots[i]) {
            i = roots[i];
        }
        return i;
    }
}
