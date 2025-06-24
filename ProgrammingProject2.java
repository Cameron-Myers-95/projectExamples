

import java.util.Scanner; 
/*
 *@author Cameron Myers
 *@version 2-19-2025
 *This program is designed to take user data, analzye it and 
 *return whether or not it is a palindrome 
 */

public class ProgrammingProject2
{
/**
 * An interface that defines stack behaviors
 * @param <T> the type of data in the stack
 */
    interface StackInterface<T>
    {
        void push(T item);
        T pop();
        boolean isEmpty();
    }
    /**
     * A stack that stores items as a linked list
     * @param <T> the stored generic data
     */

    static class LinkedStack<T> implements StackInterface<T>
    {
        private Node<T> top;

        private static class Node<T>
        {
            T data;
            Node<T> next;

            Node(T data)
            {
                this.data = data;
            }
        }

        public void push(T item)
        {
            Node<T> node = new Node<>(item);
            node.next = top;
            top = node;
        }

        public T pop()
        {
            if (isEmpty())
            {
                throw new IllegalStateException("Stack is empty");
            }
            T data = top.data;
            top = top.next;
            return data;
        }

        public boolean isEmpty()
        {
            return top == null;
        }
    }
    /**
     * Checks if the user's text is a palindrome.
     *
     * @param userInput the original message from the user
     * @param stack     the stack we will use to help with comparison
     * @return true if the input is a palindrome, false if it isn't
     */


    public static boolean palindromeChecker(String userInput, StackInterface<Character> stack)
    {
        String readableUserInput = userInput.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int length = readableUserInput.length();

        for (int i = 0; i < length / 2; i++)
        {
            stack.push(readableUserInput.charAt(i));
        }

        int start = (length % 2 == 0) ? length / 2 : length / 2 + 1;

        for (int i = start; i < length; i++)
        {
            if (stack.pop() != readableUserInput.charAt(i))
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets input from the user and shows whether the input is a palindrome or not
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter data:   ");
        String input = scanner.nextLine();

        StackInterface<Character> stack = new LinkedStack<>();

        if (palindromeChecker(input, stack))
        {
            System.out.println("Congrats you have entered a palindrome");
        } 
        else
        {
            System.out.println("nope not a palindrome this time");
        }

        scanner.close();
    }
}
