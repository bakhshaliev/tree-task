import com.globallogic.test.tree.TreeNode;
import com.globallogic.test.tree.container.TreeContainer;
import com.globallogic.test.tree.event.log.LogChildrenAddedListener;
import com.globallogic.test.tree.event.log.LogChildrenRemovedListener;

import java.util.Arrays;

public class TestTaskApplication {

    public static void main(String[] args) {
        TreeNode<Integer> one = new TreeNode<>(1);
        TreeNode<Integer> two = new TreeNode<>(2);
        TreeNode<Integer> three = new TreeNode<>(3);
        TreeNode<Integer> four = new TreeNode<>(4);
        TreeNode<Integer> five = new TreeNode<>(5);
        TreeNode<Integer> six = new TreeNode<>(6);
        TreeNode<Integer> seven = new TreeNode<>(7);
        TreeNode<Integer> eight = new TreeNode<>(8);
        TreeNode<Integer> nine = new TreeNode<>(9);
        TreeContainer<Integer> treeContainer = new TreeContainer<>(one);
        one.setChildren(Arrays.asList(two, three));
        two.setChildren(Arrays.asList(four, five));
        three.setChildren(Arrays.asList(six));
        five.setChildren(Arrays.asList(seven, eight));
        six.setChildren(Arrays.asList(nine));

        TreeNode<Integer> ten = new TreeNode<>(10);
        TreeNode<Integer> eleven = new TreeNode<>(11);
        TreeNode<Integer> twelve = new TreeNode<>(12);
        treeContainer.addChildrenAddedListener(new LogChildrenAddedListener<>());
        treeContainer.addChildrenRemovedListener(new LogChildrenRemovedListener<>());
        treeContainer.addChildren(nine, Arrays.asList(ten, eleven, twelve));
        treeContainer.removeChildren(one);
    }
}
