package com.like.hrm.util;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

//
//Postfix Expression 계산기
//
class Calc {
 // 주어진 postfix expression을 계산하여 of(CalcResult)로 그 결과를 반환한다.
 // Devide by 0와 같이 에러가 있었을경우 empty() 를 반환한다.
 static Optional<CalcResult> calc(List<Token<Type>> postfix) {
     Stack<BigDecimal> operands = new Stack<BigDecimal>();

     for (Token<Type> token: postfix) {
    	 
         switch (token.kind) {
	         case Number:
	             operands.push(new BigDecimal(token.sequence));
	             break;
	         case Operator:
	        	 BigDecimal result;
	
	             if (token.sequence.equals("~")) {
	                 // Unary operator
	            	 BigDecimal operand = operands.pop();
	
	                 result = operand.negate();
	             } else {
	                 // Binary operator
	            	 BigDecimal right = operands.pop();
	            	 BigDecimal left = operands.pop();
	
	                 switch (token.sequence) {
		                 case "+": result = left.add(right); break;
		                 case "-": result = left.subtract(right); break;
		                 case "*": result = left.multiply(right); break;
		                 case "/":
		                     if (right.equals(0)) { return empty(); }
		                     result = left.divide(right);
		                     break;
		                 case "%":
		                     if (right.equals(0)) { return empty(); }
		                     result = left.remainder(right);
		                     break;
		                 case "^":
		                     if (left.equals(0) && right.intValue() < 0) { return empty(); }
		                     result = left.pow(right.intValue());
		                     break;
		                 default: throw new IllegalArgumentException();
	                 }
	             }
	
	             operands.push(result);
	             break;
	         }
     }

     return of(new CalcResult(postfix, operands.pop()));
 }
}