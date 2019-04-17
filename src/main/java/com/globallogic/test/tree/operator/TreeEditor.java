package com.globallogic.test.tree.operator;

import com.globallogic.test.tree.TreeNode;

import java.util.List;

public interface TreeEditor<T> {
    void addChildren(TreeNode<T> parent, List<TreeNode<T>> childrenToAdd);

    void removeChildren(TreeNode<T> parent);
}
