package com.globallogic.test.tree.container;

import com.globallogic.test.tree.TreeNode;
import com.globallogic.test.tree.event.ChildrenAddedListener;
import com.globallogic.test.tree.event.ChildrenRemovedListener;
import com.globallogic.test.tree.operator.TreeEditor;
import com.globallogic.test.tree.operator.TreeExplorer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Getter
@Setter
public class TreeContainer<T> implements TreeExplorer<T>, TreeEditor<T> {
    private TreeNode<T> root;
    private List<ChildrenAddedListener<T>> childrenAddedListeners = new ArrayList<>();
    private List<ChildrenRemovedListener<T>> childrenRemovedListeners = new ArrayList<>();

    public TreeContainer(TreeNode<T> root) {
        this.root = root;
    }

    @Override
    public List<TreeNode<T>> search(Predicate<T> predicate) {
        return search(predicate, root, new ArrayList<>());
    }

    private List<TreeNode<T>> search(Predicate<T> predicate, TreeNode<T> node, List<TreeNode<T>> nodes) {
        if (node == null) {
            return null;
        }

        if (predicate.test(node.getValue())) {
            nodes.add(node);
        }

        List<TreeNode<T>> children = node.getChildren();
        if (children != null) {
            for (TreeNode<T> child : children) {
                search(predicate, child, nodes);
            }
        }

        return nodes;
    }

    @Override
    public List<TreeNode<T>> getSubTrees(TreeNode<T> parent) {
        return getSubTrees(parent, new ArrayList<>());
    }

    private List<TreeNode<T>> getSubTrees(TreeNode<T> parent, List<TreeNode<T>> subTrees) {
        if (parent == null) {
            return null;
        }
        subTrees.add(parent);

        List<TreeNode<T>> children = parent.getChildren();
        if (children != null) {
            for (TreeNode<T> child : children) {
                getSubTrees(child, subTrees);
            }
        }

        return subTrees;
    }

    @Override
    public void addChildren(TreeNode<T> parent, List<TreeNode<T>> childrenToAdd) {
        if (childrenToAdd == null) {
            return;
        }

        List<TreeNode<T>> children = parent.getChildren();
        if (children == null) {
            children = new ArrayList<>();
            parent.setChildren(children);
        }
        children.addAll(childrenToAdd);

        childrenAddedListeners.forEach(childrenAddedListener -> childrenAddedListener.childrenAdded(parent));
    }

    @Override
    public void removeChildren(TreeNode<T> parent) {
        if (parent == null) {
            return;
        }

        parent.setChildren(null);

        childrenRemovedListeners.forEach(childrenRemovedListener -> childrenRemovedListener.childrenRemoved(parent));
    }

    public void addChildrenAddedListener(ChildrenAddedListener<T> childrenAddedListener) {
        childrenAddedListeners.add(childrenAddedListener);
    }

    public void addChildrenRemovedListener(ChildrenRemovedListener<T> childrenRemovedListener) {
        childrenRemovedListeners.add(childrenRemovedListener);
    }
}
