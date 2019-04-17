package com.globallogic.test.tree.operator;

import com.globallogic.test.tree.TreeNode;

import java.util.List;
import java.util.function.Predicate;

public interface TreeExplorer<T> {
    List<TreeNode<T>> search(Predicate<T> predicate);

    List<TreeNode<T>> getSubTrees(TreeNode<T> parent);
}
