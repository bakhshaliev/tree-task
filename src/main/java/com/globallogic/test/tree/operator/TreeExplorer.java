package com.globallogic.test.tree.operator;

import com.globallogic.test.tree.TreeNode;
import com.globallogic.test.tree.container.TreeContainer;

import java.util.List;
import java.util.function.Predicate;

public interface TreeExplorer<T> {
    List<TreeNode<T>> search(Predicate<T> predicate);

    List<TreeNode<T>> getSubTreeNodes(TreeNode<T> parent);

    List<TreeContainer<T>> getSubTrees(TreeNode<T> parent);
}
