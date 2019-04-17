package com.globallogic.test.tree.event;

import com.globallogic.test.tree.TreeNode;

import java.util.List;

@FunctionalInterface
public interface ChildrenRemovedListener<T> {
    void childrenRemoved(TreeNode<T> parent, List<TreeNode<T>> children);
}
