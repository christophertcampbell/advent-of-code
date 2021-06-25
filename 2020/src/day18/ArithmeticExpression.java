package day18;

import java.util.Stack;

/**
 * Class for evaluating an arithmetic expression
 * in the form of: 1 + (2 * 3 + 2) + (4 * (5 + 6))
 */
public class ArithmeticExpression
{
  private String expression;

  public ArithmeticExpression(String expression)
  {
    this.expression = expression;
  }

  /**
   * Evaluates the arithmetic expression in left-to-right fashion
   * with all arithmetic operators having the same precedence
   */
  public long evaluate()
  {
    Stack<Long> numStack = new Stack<>();
    Stack<Character> operatorStack = new Stack<>();

    for (char c : expression.toCharArray())
    {
      switch(c)
      {
        // Ignore spaces
        case ' ':
          continue;

        // If operator or operning parentheses, push onto the
        // operator stack and continue on with parsing the expression
        case '+':
        case '-':
        case '*':
        case '(':
          operatorStack.push(c);
          continue;

        // If closing parentheses, pop the matching opening parentheses
        // off the operator stack and discard both
        case ')':
          if (operatorStack.peek() == '(') {
            operatorStack.pop();
          }
          break;

        // If we have not found a match yet, it should be a digit.
        // Try to parse the digit and push it onto the number stack.
        default:
          try
          {
            int num = Integer.parseInt(String.valueOf(c));
            numStack.push((long)num);
          }
          catch (Exception e)
          {
            System.out.println(e.getMessage());
            continue;
          }
      }

      /**
       * As long as there is a valid arithmetic operator on the
       * operator stack, pop it off and perform the operation
       * on the top two numbers on the number stack. Push the
       * result back onto the number stack and repeat.
       *
       * If an operning parentheses is encountered, leave it on
       * the stack and continue on with parsing the expression.
       */
      while (operatorStack.isEmpty() == false && operatorStack.peek() != '(')
      {
        char operator = operatorStack.pop();

        /**
         * Pop the top two numbers off the number stack, perform the
         * operation and push the result back onto the number stack
         */
        long num2 = numStack.pop();
        long num1 = numStack.pop();
        long result;
        switch (operator)
        {
          case '+':
            result = num1 + num2;
            break;
          case '-':
            result = num1 - num2;
            break;
          case '*':
            result = num1 * num2;
            break;
          default:
            result = 0;
        }
        numStack.push(result);
      }
    }

    // After the whole expression is parsed, there should only be
    // a single number (the final result) on the number stack.
    // Pop it off and return it.
    return numStack.isEmpty() ? -1 : numStack.pop();
  }
}
