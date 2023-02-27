回溯框架：
```java
result = []
void backtrack(选择列表, 路径):
    if 满足结束条件:
        result.add(路径)
        return

    for 选择 in 选择列表:
        // 做选择
        路径.add(选择)
        将该选择从选择列表移除
        backtrack(选择列表, 路径) // 核心 递归调用之前【做选择】，调用之后【撤销选择】
        // 撤销选择
        路径.remove(选择)
        将该选择再加入选择列表

作者：缓缓
链接：https://leetcode.cn/problems/combination-sum/solutions/1542788/by-huan-huan-20-k3hb/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。