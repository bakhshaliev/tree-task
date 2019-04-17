package com.globallogic.test.tree.manager;

import com.globallogic.test.tree.TreeNode;
import com.globallogic.test.tree.container.TreeContainer;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TreeManager<T> {
    private TreeContainer<T> treeContainer;

    public int getMaxChildCount(TreeNode<T> parent) {
        int maxChildrenCount = Integer.MIN_VALUE;

        for (TreeNode<T> subTree : treeContainer.getSubTrees(parent)) {
            int childrenCount = subTree.getChildren().size();

            if (childrenCount > maxChildrenCount) {
                maxChildrenCount = childrenCount;
            }
        }

        return maxChildrenCount;
    }
}
