package com.globallogic.test.tree.event;

import com.globallogic.test.tree.TreeNode;

@FunctionalInterface
public interface ChildrenAddedListener<T> {
    void childrenAdded(TreeNode<T> parent);
}
