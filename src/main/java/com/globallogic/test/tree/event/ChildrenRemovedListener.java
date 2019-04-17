package com.globallogic.test.tree.event;

import com.globallogic.test.tree.TreeNode;

@FunctionalInterface
public interface ChildrenRemovedListener<T> {
    void childrenRemoved(TreeNode<T> parent);
}
