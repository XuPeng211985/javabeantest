package jianzhioffer.tree;
import jianzhioffer.utils.BinaryTreeNode;
/**
 * 题目描述：给定一颗二叉树和其中一个节点，输出中序遍历的下一个节点
 *          树的节点中含有指向父亲节点的指针
 *思路：中序遍历二叉树的规则是首先遍历左孩子接着遍历根
 *      最后遍历右孩子，所以一颗二叉树中的任意一个节点的下一个节点
 *      取决于当前节点是否有右孩子，如果有右孩子那么中序遍历的下一个节点就是
 *      它的右子树中的最左子节点（如果右子树只包含一个节点，那么该节点就是下一个节点），
 *      如果当前节点没有右孩子，并且它是父节点的左孩子，那么他的父节点就是当
 *      前节点的下一个节点，如果是父节点的右孩子，那么向上遍历，直到找到
 *      一个是它父节点的左子节点的节点，那么它的父节点就是下一个节点
 */
public class GetNextNode {
    public static void main(String[] args) {
    }
    public static BinaryTreeNode getNextNode(BinaryTreeNode node){
        //判空
        if(node == null){
            return null;
        }
        BinaryTreeNode pNext = null;
        //当前节点有右孩子
        if(node.getRight() != null){
            //获得当前节点的右孩子节点
            BinaryTreeNode pRight = node.getRight();
            //遍历寻找右孩子节点的最左子节点，如果右孩子节点没有左孩子，呢么它就是下一个节点
            while(pRight.getLeft() != null){
                pRight = pRight.getLeft();
            }
            pNext = pRight;
            //没有右孩子节点，再判断它是否有父亲节点
        }else if(node.getParent() != null){
            BinaryTreeNode pCurrent = node;
            BinaryTreeNode pParent = node.getParent();
            //如果当前节点是父亲节点的右孩子，那么往二叉树的顶层寻找
            //直到找到一个左孩子为止
            while(pParent != null && pCurrent == pParent.getRight()){
                //孩子变为父亲
                pCurrent = pParent;
                //父亲变为父亲的父亲
                pParent = pParent.getParent();
            }
            //找到的父亲节点便是我们要找的下一个节点
            pNext = pParent;
        }
        return pNext;
    }
}
