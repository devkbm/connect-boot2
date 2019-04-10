package com.like.hrm.util;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

//Monadic composition으로 구현한 재귀하향파서
class Parser {
 public static Optional<List<Token<Type>>> parse(List<Token<Type>> tokens) {
     Parser p = new Parser(tokens);
     Context c0 = new Context();

     return p.tryExpr(c0)
         .filter(c -> c.cursor == tokens.size())
         .map(c -> c.terminate())
         .map(c -> c.output);
 }

 // Internal class constructor
 private final List<Token<Type>> tokens;
 private Parser(List<Token<Type>> tokens) { this.tokens = tokens; }

 // Alternatives for `tokens.get(idx)` function which is exception-safe
 private Optional<Token<Type>> tokenAt(int index) {
     return 0 <= index && index < tokens.size()
         ? of(tokens.get(index))
         : empty();
 }

 // Missing `or` function for Optional<T>
 private static <T> Optional<T> or(Optional<T> left, Optional<T> right) {
     return left.isPresent() ? left
         : right.isPresent() ? right
         : empty();
 }

 // Parser context. 파서가 파싱중 사용하는 컨텍스트 클래스이다.
 // Immutable이다.
 //
 // Reference: Shunting-yard Algorithm
 private static class Context {
     public final int cursor;
     private final ArrayList<Token<Type>> output;
     private final ArrayList<Token<Type>> op_stack;

     private Context(int cursor,
             ArrayList<Token<Type>> output,
             ArrayList<Token<Type>> op_stack)
     {
         this.cursor = cursor;
         this.output = output;
         this.op_stack = op_stack;
     }

     // 빈 컨텍스트 객체를 생성한다.
     public Context() {
         cursor = 0;
         output = new ArrayList<Token<Type>>();
         op_stack = new ArrayList<Token<Type>>();
     }

     // Shunting-yard Algorithm에 맞춰, 토큰들의 순서를 Infix에서 Postfix로
     // 재배열한다.
     public Context push(Token<Type> t) {
         switch (t.kind) {
         case Number: {
             final ArrayList<Token<Type>> output = new ArrayList<Token<Type>>(this.output) {{
                 add(t);
             }};

             return new Context(cursor + 1, output, op_stack); }
         case Operator: {
             final ArrayList<Token<Type>>
                 output = new ArrayList<Token<Type>>(this.output),
                 op_stack = new ArrayList<Token<Type>>(this.op_stack);

             switch (t.sequence) {
             case "(":
                 op_stack.add(t);
                 break;
             case ")":
                 while (true) {
                     if (op_stack.isEmpty()) { throw new IllegalArgumentException(); }

                     Token<Type> pop = op_stack.remove(op_stack.size() - 1);
                     if (pop.sequence.equals("(")) { break; }

                     output.add(pop);
                 }
                 break;
             default:
                 while (!op_stack.isEmpty()) {
                     Token<Type> top = op_stack.get(op_stack.size() - 1);
                     boolean condition = !top.sequence.equals("(") &&
                         ( is_left_assoc(t)
                         ? precedence(t) <= precedence(top)
                         : precedence(t) < precedence(top) );

                     if (!condition) { break; }

                     output.add(top);
                     op_stack.remove(op_stack.size() - 1);
                 }

                 op_stack.add(t);
             }

             return new Context(cursor + 1, output, op_stack); }
         default:
             return this;
         }
     }

     // 파서 컨텍스트에 파싱이 끝났음을 알린다. op_stack에 들어있는 잔여
     // 토큰들을 모두 output 스택으로 옮긴다.
     public Context terminate() {
         final ArrayList<Token<Type>>
             output = new ArrayList<Token<Type>>(this.output),
             op_stack = new ArrayList<Token<Type>>(this.op_stack);

         while (!op_stack.isEmpty()) {
             Token<Type> t = op_stack.remove(op_stack.size() - 1);
             output.add(t);
         }

         return new Context(cursor, output, op_stack);
     }

     @Override
     public String toString() {
         return "Content(cursor: "+cursor+", output: "+output+", op_stack: "+op_stack+")";
     }

     private static int precedence(Token<Type> op) {
         switch (op.sequence) {
         case "^":                       return 4;
         case "~":                       return 3;
         case "*": case "/": case "%":   return 2;
         case "+": case "-":             return 1;
         default: throw new IllegalArgumentException();
         }
     }

     private static boolean is_left_assoc(Token<Type> op) {
         switch (op.sequence) {
         case "*": case "/": case "%":
         case "+": case "-":             return true;
         case "^": case "~":             return false;
         default: throw new IllegalArgumentException();
         }
     }
 }

 // Recursive descent parser
 //
 //          <expr> ::= <term> | <term> <add-op> <expr>
 //          <term> ::= <signed-factor> | <signed-factor> <mult-op> <term>
 // <signed-factor> ::= <factor> | "-" <signed-factor>
 //        <factor> ::= <element> | <element> "^" <factor>
 //       <element> ::= "(" <expr> ")" | <number>
 //
 //        <add-op> ::= "+" | "-"
 //       <mult-op> ::= "*" | "/" | "%"
 private Optional<Context> tryExpr(Context ctxt) {
     return or(
         of(ctxt)
             .flatMap(this::tryTerm)
             .flatMap(tryOp("+", "-"))
             .flatMap(this::tryExpr),
         of(ctxt)
             .flatMap(this::tryTerm)
     );
 }
 private Optional<Context> tryTerm(Context ctxt) {
     return or(
         of(ctxt)
             .flatMap(this::trySignedFactor)
             .flatMap(tryOp("*", "/", "%"))
             .flatMap(this::tryTerm),
         of(ctxt)
             .flatMap(this::trySignedFactor)
     );
 }
 private Optional<Context> trySignedFactor(Context ctxt) {
     return or(
         of(ctxt)
             .flatMap(this::tryUnaryMinus)
             .flatMap(this::trySignedFactor),
         of(ctxt)
             .flatMap(this::tryFactor)
     );
 }
 private Optional<Context> tryUnaryMinus(Context ctxt) {
     return tokenAt(ctxt.cursor)
         .filter(t -> t.kind == Type.Operator)
         .filter(t -> t.sequence.equals("-"))
         .map(t -> new Token<Type>("~", Type.Operator))
         .map(ctxt::push);
 }
 private Optional<Context> tryFactor(Context ctxt) {
     return or(
         of(ctxt)
             .flatMap(this::tryElement)
             .flatMap(tryOp("^"))
             .flatMap(this::tryFactor),
         of(ctxt)
             .flatMap(this::tryElement)
     );
 }
 private HashMap<Context, Optional<Context>> cache = new HashMap<Context, Optional<Context>>();
 private Optional<Context> tryElement(Context ctxt) {
     // Memoization
     return cache.computeIfAbsent(ctxt, c -> or(
         of(c)
             .flatMap(tryOp("("))
             .flatMap(this::tryExpr)
             .flatMap(tryOp(")")),
         of(c)
             .flatMap(this::tryNumber)
     ));
 }
 private Function<Context, Optional<Context>> tryOp(String... ops) {
     return ctxt -> tokenAt(ctxt.cursor)
         .filter(t -> t.kind == Type.Operator)
         .filter(t -> Arrays.asList(ops).contains(t.sequence))
         .map(ctxt::push);
 }
 private Optional<Context> tryNumber(Context ctxt) {
     return tokenAt(ctxt.cursor)
         .filter(t -> t.kind == Type.Number)
         .map(ctxt::push);
 }
}