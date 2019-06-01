// Generated from C:/Users/Sarah/Documents/Alpha-Compiladores/Alpha\Parser2.g4 by ANTLR 4.7.2
package generated;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Parser2}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Parser2Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code programAST}
	 * labeled alternative in {@link Parser2#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramAST(Parser2.ProgramASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code commandAST}
	 * labeled alternative in {@link Parser2#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommandAST(Parser2.CommandASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignSCast}
	 * labeled alternative in {@link Parser2#singleCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignSCast(Parser2.AssignSCastContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callSCAST}
	 * labeled alternative in {@link Parser2#singleCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallSCAST(Parser2.CallSCASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifSCAST}
	 * labeled alternative in {@link Parser2#singleCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfSCAST(Parser2.IfSCASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileSCAST}
	 * labeled alternative in {@link Parser2#singleCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileSCAST(Parser2.WhileSCASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code letSCAST}
	 * labeled alternative in {@link Parser2#singleCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetSCAST(Parser2.LetSCASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code beginSCASD}
	 * labeled alternative in {@link Parser2#singleCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeginSCASD(Parser2.BeginSCASDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printAST}
	 * labeled alternative in {@link Parser2#singleCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintAST(Parser2.PrintASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declarationAST}
	 * labeled alternative in {@link Parser2#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationAST(Parser2.DeclarationASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constDeclAST}
	 * labeled alternative in {@link Parser2#singleDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDeclAST(Parser2.ConstDeclASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDeclAST}
	 * labeled alternative in {@link Parser2#singleDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclAST(Parser2.VarDeclASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typedenoterIntAST}
	 * labeled alternative in {@link Parser2#typedenoter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedenoterIntAST(Parser2.TypedenoterIntASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typedenoterStringGAST}
	 * labeled alternative in {@link Parser2#typedenoter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedenoterStringGAST(Parser2.TypedenoterStringGASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typedenoterBoolAST}
	 * labeled alternative in {@link Parser2#typedenoter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedenoterBoolAST(Parser2.TypedenoterBoolASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionAST}
	 * labeled alternative in {@link Parser2#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionAST(Parser2.ExpressionASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numPEAST}
	 * labeled alternative in {@link Parser2#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumPEAST(Parser2.NumPEASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idPEAST}
	 * labeled alternative in {@link Parser2#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdPEAST(Parser2.IdPEASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringPEAST}
	 * labeled alternative in {@link Parser2#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringPEAST(Parser2.StringPEASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanPEAST}
	 * labeled alternative in {@link Parser2#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanPEAST(Parser2.BooleanPEASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code groupPEAST}
	 * labeled alternative in {@link Parser2#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupPEAST(Parser2.GroupPEASTContext ctx);
	/**
	 * Visit a parse tree produced by {@link Parser2#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(Parser2.OperatorContext ctx);
}