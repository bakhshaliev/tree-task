package com.globallogic.test.tree.container;

import com.globallogic.test.tree.TreeNode;
import com.globallogic.test.tree.event.ChildrenAddedListener;
import com.globallogic.test.tree.event.ChildrenRemovedListener;
import com.globallogic.test.tree.operator.TreeEditor;
import com.globallogic.test.tree.operator.TreeExplorer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
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

        for (TreeNode<T> child : node.getChildren()) {
            search(predicate, child, nodes);
        }

        return nodes;
    }

    @Override
    public List<TreeNode<T>> getSubTreeNodes(TreeNode<T> parent) {
        return getSubTreeNodes(parent, new ArrayList<>());
    }

    private List<TreeNode<T>> getSubTreeNodes(TreeNode<T> parent, List<TreeNode<T>> subTrees) {
        if (parent == null) {
            return null;
        }
        subTrees.add(parent);

        for (TreeNode<T> child : parent.getChildren()) {
            getSubTreeNodes(child, subTrees);
        }

        return subTrees;
    }

    @Override
    public List<TreeContainer<T>> getSubTrees(TreeNode<T> parent) {
        return getSubTreeNodes(parent).stream()
                .map((Function<TreeNode<T>, TreeContainer<T>>) TreeContainer::new)
                .collect(Collectors.toList());
    }

    @Override
    public void addChildren(TreeNode<T> parent, List<TreeNode<T>> childrenToAdd) {
        if (childrenToAdd == null || childrenToAdd.isEmpty()) {
            return;
        }

        parent.getChildren().addAll(childrenToAdd);

        childrenAddedListeners.forEach(childrenAddedListener ->
                childrenAddedListener.childrenAdded(parent, childrenToAdd));
    }

    @Override
    public void removeChildren(TreeNode<T> parent) {
        if (parent == null) {
            return;
        }
        List<TreeNode<T>> childrenToDelete = parent.getChildren();

        parent.setChildren(new ArrayList<>());

        childrenRemovedListeners.forEach(childrenRemovedListener ->
                childrenRemovedListener.childrenRemoved(parent, childrenToDelete));
    }

    public void addChildrenAddedListener(ChildrenAddedListener<T> childrenAddedListener) {
        childrenAddedListeners.add(childrenAddedListener);
    }

    public void addChildrenRemovedListener(ChildrenRemovedListener<T> childrenRemovedListener) {
        childrenRemovedListeners.add(childrenRemovedListener);
    }
}
