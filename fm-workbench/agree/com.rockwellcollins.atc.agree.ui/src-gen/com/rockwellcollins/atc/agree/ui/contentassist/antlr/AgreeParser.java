/*
* generated by Xtext
*/
package com.rockwellcollins.atc.agree.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import com.rockwellcollins.atc.agree.services.AgreeGrammarAccess;

public class AgreeParser extends AbstractContentAssistParser {
	
	@Inject
	private AgreeGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected com.rockwellcollins.atc.agree.ui.contentassist.antlr.internal.InternalAgreeParser createParser() {
		com.rockwellcollins.atc.agree.ui.contentassist.antlr.internal.InternalAgreeParser result = new com.rockwellcollins.atc.agree.ui.contentassist.antlr.internal.InternalAgreeParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getNamedElementAccess().getAlternatives(), "rule__NamedElement__Alternatives");
					put(grammarAccess.getElementAccess().getAlternatives(), "rule__Element__Alternatives");
					put(grammarAccess.getSpecStatementAccess().getAlternatives(), "rule__SpecStatement__Alternatives");
					put(grammarAccess.getSynchStatementAccess().getAlternatives(), "rule__SynchStatement__Alternatives");
					put(grammarAccess.getSynchStatementAccess().getSimAlternatives_0_4_0(), "rule__SynchStatement__SimAlternatives_0_4_0");
					put(grammarAccess.getCallDefAccess().getAlternatives(), "rule__CallDef__Alternatives");
					put(grammarAccess.getNodeStmtAccess().getAlternatives(), "rule__NodeStmt__Alternatives");
					put(grammarAccess.getTypeAccess().getAlternatives(), "rule__Type__Alternatives");
					put(grammarAccess.getPrimTypesAccess().getAlternatives(), "rule__PrimTypes__Alternatives");
					put(grammarAccess.getRelateOpAccess().getAlternatives(), "rule__RelateOp__Alternatives");
					put(grammarAccess.getAddSubExprAccess().getOpAlternatives_1_0_0_1_0(), "rule__AddSubExpr__OpAlternatives_1_0_0_1_0");
					put(grammarAccess.getMultDivExprAccess().getOpAlternatives_1_0_0_1_0(), "rule__MultDivExpr__OpAlternatives_1_0_0_1_0");
					put(grammarAccess.getUnaryExprAccess().getAlternatives(), "rule__UnaryExpr__Alternatives");
					put(grammarAccess.getUnaryExprAccess().getOpAlternatives_0_1_0(), "rule__UnaryExpr__OpAlternatives_0_1_0");
					put(grammarAccess.getIfThenElseExprAccess().getAlternatives(), "rule__IfThenElseExpr__Alternatives");
					put(grammarAccess.getPreDefFnExprAccess().getAlternatives(), "rule__PreDefFnExpr__Alternatives");
					put(grammarAccess.getTermExprAccess().getAlternatives(), "rule__TermExpr__Alternatives");
					put(grammarAccess.getComplexExprAccess().getAlternatives_1(), "rule__ComplexExpr__Alternatives_1");
					put(grammarAccess.getNestedDotIDAccess().getAlternatives_1_0_1(), "rule__NestedDotID__Alternatives_1_0_1");
					put(grammarAccess.getQCPREFAccess().getAlternatives(), "rule__QCPREF__Alternatives");
					put(grammarAccess.getReservedVarTagAccess().getAlternatives(), "rule__ReservedVarTag__Alternatives");
					put(grammarAccess.getContainedPropertyAssociationAccess().getAlternatives_1(), "rule__ContainedPropertyAssociation__Alternatives_1");
					put(grammarAccess.getPropertyAssociationAccess().getAlternatives_1(), "rule__PropertyAssociation__Alternatives_1");
					put(grammarAccess.getPropertyExpressionAccess().getAlternatives(), "rule__PropertyExpression__Alternatives");
					put(grammarAccess.getBooleanLiteralAccess().getAlternatives_1(), "rule__BooleanLiteral__Alternatives_1");
					put(grammarAccess.getPlusMinusAccess().getAlternatives(), "rule__PlusMinus__Alternatives");
					put(grammarAccess.getSignedIntAccess().getAlternatives_0(), "rule__SignedInt__Alternatives_0");
					put(grammarAccess.getSignedRealAccess().getAlternatives_0(), "rule__SignedReal__Alternatives_0");
					put(grammarAccess.getNumAltAccess().getAlternatives(), "rule__NumAlt__Alternatives");
					put(grammarAccess.getAgreeLibraryAccess().getGroup(), "rule__AgreeLibrary__Group__0");
					put(grammarAccess.getAgreeSubclauseAccess().getGroup(), "rule__AgreeSubclause__Group__0");
					put(grammarAccess.getAgreeContractAccess().getGroup(), "rule__AgreeContract__Group__0");
					put(grammarAccess.getSpecStatementAccess().getGroup_0(), "rule__SpecStatement__Group_0__0");
					put(grammarAccess.getSpecStatementAccess().getGroup_1(), "rule__SpecStatement__Group_1__0");
					put(grammarAccess.getSpecStatementAccess().getGroup_2(), "rule__SpecStatement__Group_2__0");
					put(grammarAccess.getSpecStatementAccess().getGroup_3(), "rule__SpecStatement__Group_3__0");
					put(grammarAccess.getSpecStatementAccess().getGroup_4(), "rule__SpecStatement__Group_4__0");
					put(grammarAccess.getSpecStatementAccess().getGroup_5(), "rule__SpecStatement__Group_5__0");
					put(grammarAccess.getSynchStatementAccess().getGroup_0(), "rule__SynchStatement__Group_0__0");
					put(grammarAccess.getSynchStatementAccess().getGroup_1(), "rule__SynchStatement__Group_1__0");
					put(grammarAccess.getSynchStatementAccess().getGroup_1_4(), "rule__SynchStatement__Group_1_4__0");
					put(grammarAccess.getPropertyStatementAccess().getGroup(), "rule__PropertyStatement__Group__0");
					put(grammarAccess.getConstStatementAccess().getGroup(), "rule__ConstStatement__Group__0");
					put(grammarAccess.getEqStatementAccess().getGroup(), "rule__EqStatement__Group__0");
					put(grammarAccess.getEqStatementAccess().getGroup_1(), "rule__EqStatement__Group_1__0");
					put(grammarAccess.getEqStatementAccess().getGroup_1_1(), "rule__EqStatement__Group_1_1__0");
					put(grammarAccess.getEqStatementAccess().getGroup_2(), "rule__EqStatement__Group_2__0");
					put(grammarAccess.getFnDefExprAccess().getGroup(), "rule__FnDefExpr__Group__0");
					put(grammarAccess.getFnDefExprAccess().getGroup_4(), "rule__FnDefExpr__Group_4__0");
					put(grammarAccess.getNodeDefExprAccess().getGroup(), "rule__NodeDefExpr__Group__0");
					put(grammarAccess.getNodeDefExprAccess().getGroup_3(), "rule__NodeDefExpr__Group_3__0");
					put(grammarAccess.getNodeDefExprAccess().getGroup_3_1(), "rule__NodeDefExpr__Group_3_1__0");
					put(grammarAccess.getNodeDefExprAccess().getGroup_7(), "rule__NodeDefExpr__Group_7__0");
					put(grammarAccess.getNodeDefExprAccess().getGroup_7_1(), "rule__NodeDefExpr__Group_7_1__0");
					put(grammarAccess.getNodeBodyExprAccess().getGroup(), "rule__NodeBodyExpr__Group__0");
					put(grammarAccess.getNodeBodyExprAccess().getGroup_0(), "rule__NodeBodyExpr__Group_0__0");
					put(grammarAccess.getNodeBodyExprAccess().getGroup_0_1(), "rule__NodeBodyExpr__Group_0_1__0");
					put(grammarAccess.getNodeStmtAccess().getGroup_0(), "rule__NodeStmt__Group_0__0");
					put(grammarAccess.getNodeStmtAccess().getGroup_0_2(), "rule__NodeStmt__Group_0_2__0");
					put(grammarAccess.getNodeStmtAccess().getGroup_1(), "rule__NodeStmt__Group_1__0");
					put(grammarAccess.getArgAccess().getGroup(), "rule__Arg__Group__0");
					put(grammarAccess.getTypeAccess().getGroup_0(), "rule__Type__Group_0__0");
					put(grammarAccess.getTypeAccess().getGroup_1(), "rule__Type__Group_1__0");
					put(grammarAccess.getRecordDefExprAccess().getGroup(), "rule__RecordDefExpr__Group__0");
					put(grammarAccess.getRecordDefExprAccess().getGroup_5(), "rule__RecordDefExpr__Group_5__0");
					put(grammarAccess.getRecordDefExprAccess().getGroup_5_1(), "rule__RecordDefExpr__Group_5_1__0");
					put(grammarAccess.getArrowExprAccess().getGroup(), "rule__ArrowExpr__Group__0");
					put(grammarAccess.getArrowExprAccess().getGroup_1(), "rule__ArrowExpr__Group_1__0");
					put(grammarAccess.getArrowExprAccess().getGroup_1_0(), "rule__ArrowExpr__Group_1_0__0");
					put(grammarAccess.getArrowExprAccess().getGroup_1_0_0(), "rule__ArrowExpr__Group_1_0_0__0");
					put(grammarAccess.getImpliesExprAccess().getGroup(), "rule__ImpliesExpr__Group__0");
					put(grammarAccess.getImpliesExprAccess().getGroup_1(), "rule__ImpliesExpr__Group_1__0");
					put(grammarAccess.getImpliesExprAccess().getGroup_1_0(), "rule__ImpliesExpr__Group_1_0__0");
					put(grammarAccess.getImpliesExprAccess().getGroup_1_0_0(), "rule__ImpliesExpr__Group_1_0_0__0");
					put(grammarAccess.getEquivExprAccess().getGroup(), "rule__EquivExpr__Group__0");
					put(grammarAccess.getEquivExprAccess().getGroup_1(), "rule__EquivExpr__Group_1__0");
					put(grammarAccess.getEquivExprAccess().getGroup_1_0(), "rule__EquivExpr__Group_1_0__0");
					put(grammarAccess.getEquivExprAccess().getGroup_1_0_0(), "rule__EquivExpr__Group_1_0_0__0");
					put(grammarAccess.getOrExprAccess().getGroup(), "rule__OrExpr__Group__0");
					put(grammarAccess.getOrExprAccess().getGroup_1(), "rule__OrExpr__Group_1__0");
					put(grammarAccess.getOrExprAccess().getGroup_1_0(), "rule__OrExpr__Group_1_0__0");
					put(grammarAccess.getOrExprAccess().getGroup_1_0_0(), "rule__OrExpr__Group_1_0_0__0");
					put(grammarAccess.getAndExprAccess().getGroup(), "rule__AndExpr__Group__0");
					put(grammarAccess.getAndExprAccess().getGroup_1(), "rule__AndExpr__Group_1__0");
					put(grammarAccess.getAndExprAccess().getGroup_1_0(), "rule__AndExpr__Group_1_0__0");
					put(grammarAccess.getAndExprAccess().getGroup_1_0_0(), "rule__AndExpr__Group_1_0_0__0");
					put(grammarAccess.getRelateExprAccess().getGroup(), "rule__RelateExpr__Group__0");
					put(grammarAccess.getRelateExprAccess().getGroup_1(), "rule__RelateExpr__Group_1__0");
					put(grammarAccess.getRelateExprAccess().getGroup_1_0(), "rule__RelateExpr__Group_1_0__0");
					put(grammarAccess.getRelateExprAccess().getGroup_1_0_0(), "rule__RelateExpr__Group_1_0_0__0");
					put(grammarAccess.getAddSubExprAccess().getGroup(), "rule__AddSubExpr__Group__0");
					put(grammarAccess.getAddSubExprAccess().getGroup_1(), "rule__AddSubExpr__Group_1__0");
					put(grammarAccess.getAddSubExprAccess().getGroup_1_0(), "rule__AddSubExpr__Group_1_0__0");
					put(grammarAccess.getAddSubExprAccess().getGroup_1_0_0(), "rule__AddSubExpr__Group_1_0_0__0");
					put(grammarAccess.getMultDivExprAccess().getGroup(), "rule__MultDivExpr__Group__0");
					put(grammarAccess.getMultDivExprAccess().getGroup_1(), "rule__MultDivExpr__Group_1__0");
					put(grammarAccess.getMultDivExprAccess().getGroup_1_0(), "rule__MultDivExpr__Group_1_0__0");
					put(grammarAccess.getMultDivExprAccess().getGroup_1_0_0(), "rule__MultDivExpr__Group_1_0_0__0");
					put(grammarAccess.getUnaryExprAccess().getGroup_0(), "rule__UnaryExpr__Group_0__0");
					put(grammarAccess.getIfThenElseExprAccess().getGroup_0(), "rule__IfThenElseExpr__Group_0__0");
					put(grammarAccess.getPreDefFnExprAccess().getGroup_0(), "rule__PreDefFnExpr__Group_0__0");
					put(grammarAccess.getPreDefFnExprAccess().getGroup_1(), "rule__PreDefFnExpr__Group_1__0");
					put(grammarAccess.getRecordUpdateExprAccess().getGroup(), "rule__RecordUpdateExpr__Group__0");
					put(grammarAccess.getRecordUpdateExprAccess().getGroup_1(), "rule__RecordUpdateExpr__Group_1__0");
					put(grammarAccess.getRecordUpdateExprAccess().getGroup_1_0(), "rule__RecordUpdateExpr__Group_1_0__0");
					put(grammarAccess.getRecordUpdateExprAccess().getGroup_1_0_1(), "rule__RecordUpdateExpr__Group_1_0_1__0");
					put(grammarAccess.getTermExprAccess().getGroup_1(), "rule__TermExpr__Group_1__0");
					put(grammarAccess.getTermExprAccess().getGroup_2(), "rule__TermExpr__Group_2__0");
					put(grammarAccess.getTermExprAccess().getGroup_3(), "rule__TermExpr__Group_3__0");
					put(grammarAccess.getTermExprAccess().getGroup_4(), "rule__TermExpr__Group_4__0");
					put(grammarAccess.getTermExprAccess().getGroup_5(), "rule__TermExpr__Group_5__0");
					put(grammarAccess.getTermExprAccess().getGroup_5_2(), "rule__TermExpr__Group_5_2__0");
					put(grammarAccess.getTermExprAccess().getGroup_6(), "rule__TermExpr__Group_6__0");
					put(grammarAccess.getTermExprAccess().getGroup_7(), "rule__TermExpr__Group_7__0");
					put(grammarAccess.getTermExprAccess().getGroup_8(), "rule__TermExpr__Group_8__0");
					put(grammarAccess.getComplexExprAccess().getGroup(), "rule__ComplexExpr__Group__0");
					put(grammarAccess.getComplexExprAccess().getGroup_1_0(), "rule__ComplexExpr__Group_1_0__0");
					put(grammarAccess.getComplexExprAccess().getGroup_1_0_0(), "rule__ComplexExpr__Group_1_0_0__0");
					put(grammarAccess.getComplexExprAccess().getGroup_1_0_0_0(), "rule__ComplexExpr__Group_1_0_0_0__0");
					put(grammarAccess.getComplexExprAccess().getGroup_1_0_1(), "rule__ComplexExpr__Group_1_0_1__0");
					put(grammarAccess.getComplexExprAccess().getGroup_1_0_1_1(), "rule__ComplexExpr__Group_1_0_1_1__0");
					put(grammarAccess.getComplexExprAccess().getGroup_1_1(), "rule__ComplexExpr__Group_1_1__0");
					put(grammarAccess.getComplexExprAccess().getGroup_1_1_0(), "rule__ComplexExpr__Group_1_1_0__0");
					put(grammarAccess.getComplexExprAccess().getGroup_1_1_0_0(), "rule__ComplexExpr__Group_1_1_0_0__0");
					put(grammarAccess.getComplexExprAccess().getGroup_1_1_2(), "rule__ComplexExpr__Group_1_1_2__0");
					put(grammarAccess.getNestedDotIDAccess().getGroup(), "rule__NestedDotID__Group__0");
					put(grammarAccess.getNestedDotIDAccess().getGroup_1(), "rule__NestedDotID__Group_1__0");
					put(grammarAccess.getNestedDotIDAccess().getGroup_1_0(), "rule__NestedDotID__Group_1_0__0");
					put(grammarAccess.getQCPREFAccess().getGroup_0(), "rule__QCPREF__Group_0__0");
					put(grammarAccess.getQCPREFAccess().getGroup_0_0(), "rule__QCPREF__Group_0_0__0");
					put(grammarAccess.getQCPREFAccess().getGroup_0_0_0(), "rule__QCPREF__Group_0_0_0__0");
					put(grammarAccess.getQCPREFAccess().getGroup_0_2(), "rule__QCPREF__Group_0_2__0");
					put(grammarAccess.getQCPREFAccess().getGroup_0_2_0(), "rule__QCPREF__Group_0_2_0__0");
					put(grammarAccess.getContainedPropertyAssociationAccess().getGroup(), "rule__ContainedPropertyAssociation__Group__0");
					put(grammarAccess.getContainedPropertyAssociationAccess().getGroup_3(), "rule__ContainedPropertyAssociation__Group_3__0");
					put(grammarAccess.getContainedPropertyAssociationAccess().getGroup_3_1(), "rule__ContainedPropertyAssociation__Group_3_1__0");
					put(grammarAccess.getContainedPropertyAssociationAccess().getGroup_4(), "rule__ContainedPropertyAssociation__Group_4__0");
					put(grammarAccess.getContainedPropertyAssociationAccess().getGroup_4_3(), "rule__ContainedPropertyAssociation__Group_4_3__0");
					put(grammarAccess.getContainedPropertyAssociationAccess().getGroup_5(), "rule__ContainedPropertyAssociation__Group_5__0");
					put(grammarAccess.getPropertyAssociationAccess().getGroup(), "rule__PropertyAssociation__Group__0");
					put(grammarAccess.getPropertyAssociationAccess().getGroup_3(), "rule__PropertyAssociation__Group_3__0");
					put(grammarAccess.getPropertyAssociationAccess().getGroup_3_1(), "rule__PropertyAssociation__Group_3_1__0");
					put(grammarAccess.getPropertyAssociationAccess().getGroup_4(), "rule__PropertyAssociation__Group_4__0");
					put(grammarAccess.getBasicPropertyAssociationAccess().getGroup(), "rule__BasicPropertyAssociation__Group__0");
					put(grammarAccess.getContainmentPathAccess().getGroup(), "rule__ContainmentPath__Group__0");
					put(grammarAccess.getContainmentPathAccess().getGroup_2(), "rule__ContainmentPath__Group_2__0");
					put(grammarAccess.getModalPropertyValueAccess().getGroup(), "rule__ModalPropertyValue__Group__0");
					put(grammarAccess.getModalPropertyValueAccess().getGroup_5(), "rule__ModalPropertyValue__Group_5__0");
					put(grammarAccess.getOptionalModalPropertyValueAccess().getGroup(), "rule__OptionalModalPropertyValue__Group__0");
					put(grammarAccess.getOptionalModalPropertyValueAccess().getGroup_1(), "rule__OptionalModalPropertyValue__Group_1__0");
					put(grammarAccess.getOptionalModalPropertyValueAccess().getGroup_1_4(), "rule__OptionalModalPropertyValue__Group_1_4__0");
					put(grammarAccess.getBooleanLiteralAccess().getGroup(), "rule__BooleanLiteral__Group__0");
					put(grammarAccess.getReferenceTermAccess().getGroup(), "rule__ReferenceTerm__Group__0");
					put(grammarAccess.getReferenceTermAccess().getGroup_2(), "rule__ReferenceTerm__Group_2__0");
					put(grammarAccess.getReferenceTermAccess().getGroup_4(), "rule__ReferenceTerm__Group_4__0");
					put(grammarAccess.getRecordTermAccess().getGroup(), "rule__RecordTerm__Group__0");
					put(grammarAccess.getOldRecordTermAccess().getGroup(), "rule__OldRecordTerm__Group__0");
					put(grammarAccess.getComputedTermAccess().getGroup(), "rule__ComputedTerm__Group__0");
					put(grammarAccess.getComponentClassifierTermAccess().getGroup(), "rule__ComponentClassifierTerm__Group__0");
					put(grammarAccess.getListTermAccess().getGroup(), "rule__ListTerm__Group__0");
					put(grammarAccess.getListTermAccess().getGroup_2(), "rule__ListTerm__Group_2__0");
					put(grammarAccess.getListTermAccess().getGroup_2_1(), "rule__ListTerm__Group_2_1__0");
					put(grammarAccess.getFieldPropertyAssociationAccess().getGroup(), "rule__FieldPropertyAssociation__Group__0");
					put(grammarAccess.getContainmentPathElementAccess().getGroup(), "rule__ContainmentPathElement__Group__0");
					put(grammarAccess.getANNEXREFAccess().getGroup(), "rule__ANNEXREF__Group__0");
					put(grammarAccess.getArrayRangeAccess().getGroup(), "rule__ArrayRange__Group__0");
					put(grammarAccess.getArrayRangeAccess().getGroup_3(), "rule__ArrayRange__Group_3__0");
					put(grammarAccess.getSignedConstantAccess().getGroup(), "rule__SignedConstant__Group__0");
					put(grammarAccess.getIntegerTermAccess().getGroup(), "rule__IntegerTerm__Group__0");
					put(grammarAccess.getSignedIntAccess().getGroup(), "rule__SignedInt__Group__0");
					put(grammarAccess.getRealTermAccess().getGroup(), "rule__RealTerm__Group__0");
					put(grammarAccess.getSignedRealAccess().getGroup(), "rule__SignedReal__Group__0");
					put(grammarAccess.getNumericRangeTermAccess().getGroup(), "rule__NumericRangeTerm__Group__0");
					put(grammarAccess.getNumericRangeTermAccess().getGroup_3(), "rule__NumericRangeTerm__Group_3__0");
					put(grammarAccess.getQCLREFAccess().getGroup(), "rule__QCLREF__Group__0");
					put(grammarAccess.getQPREFAccess().getGroup(), "rule__QPREF__Group__0");
					put(grammarAccess.getQPREFAccess().getGroup_1(), "rule__QPREF__Group_1__0");
					put(grammarAccess.getQCREFAccess().getGroup(), "rule__QCREF__Group__0");
					put(grammarAccess.getQCREFAccess().getGroup_0(), "rule__QCREF__Group_0__0");
					put(grammarAccess.getQCREFAccess().getGroup_2(), "rule__QCREF__Group_2__0");
					put(grammarAccess.getAgreeLibraryAccess().getContractAssignment_1(), "rule__AgreeLibrary__ContractAssignment_1");
					put(grammarAccess.getAgreeSubclauseAccess().getContractAssignment_1(), "rule__AgreeSubclause__ContractAssignment_1");
					put(grammarAccess.getAgreeContractAccess().getSpecsAssignment_1(), "rule__AgreeContract__SpecsAssignment_1");
					put(grammarAccess.getSpecStatementAccess().getStrAssignment_0_2(), "rule__SpecStatement__StrAssignment_0_2");
					put(grammarAccess.getSpecStatementAccess().getExprAssignment_0_4(), "rule__SpecStatement__ExprAssignment_0_4");
					put(grammarAccess.getSpecStatementAccess().getStrAssignment_1_2(), "rule__SpecStatement__StrAssignment_1_2");
					put(grammarAccess.getSpecStatementAccess().getExprAssignment_1_4(), "rule__SpecStatement__ExprAssignment_1_4");
					put(grammarAccess.getSpecStatementAccess().getExprAssignment_2_2(), "rule__SpecStatement__ExprAssignment_2_2");
					put(grammarAccess.getSpecStatementAccess().getExprAssignment_3_2(), "rule__SpecStatement__ExprAssignment_3_2");
					put(grammarAccess.getSpecStatementAccess().getTypeAssignment_3_4(), "rule__SpecStatement__TypeAssignment_3_4");
					put(grammarAccess.getSpecStatementAccess().getStrAssignment_4_2(), "rule__SpecStatement__StrAssignment_4_2");
					put(grammarAccess.getSpecStatementAccess().getExprAssignment_4_4(), "rule__SpecStatement__ExprAssignment_4_4");
					put(grammarAccess.getSpecStatementAccess().getSubcompAssignment_5_2(), "rule__SpecStatement__SubcompAssignment_5_2");
					put(grammarAccess.getSynchStatementAccess().getValAssignment_0_3(), "rule__SynchStatement__ValAssignment_0_3");
					put(grammarAccess.getSynchStatementAccess().getSimAssignment_0_4(), "rule__SynchStatement__SimAssignment_0_4");
					put(grammarAccess.getSynchStatementAccess().getElsAssignment_1_3(), "rule__SynchStatement__ElsAssignment_1_3");
					put(grammarAccess.getSynchStatementAccess().getElsAssignment_1_4_1(), "rule__SynchStatement__ElsAssignment_1_4_1");
					put(grammarAccess.getPropertyStatementAccess().getNameAssignment_1(), "rule__PropertyStatement__NameAssignment_1");
					put(grammarAccess.getPropertyStatementAccess().getExprAssignment_3(), "rule__PropertyStatement__ExprAssignment_3");
					put(grammarAccess.getConstStatementAccess().getNameAssignment_1(), "rule__ConstStatement__NameAssignment_1");
					put(grammarAccess.getConstStatementAccess().getTypeAssignment_3(), "rule__ConstStatement__TypeAssignment_3");
					put(grammarAccess.getConstStatementAccess().getExprAssignment_5(), "rule__ConstStatement__ExprAssignment_5");
					put(grammarAccess.getEqStatementAccess().getLhsAssignment_1_0(), "rule__EqStatement__LhsAssignment_1_0");
					put(grammarAccess.getEqStatementAccess().getLhsAssignment_1_1_1(), "rule__EqStatement__LhsAssignment_1_1_1");
					put(grammarAccess.getEqStatementAccess().getExprAssignment_2_1(), "rule__EqStatement__ExprAssignment_2_1");
					put(grammarAccess.getFnDefExprAccess().getNameAssignment_1(), "rule__FnDefExpr__NameAssignment_1");
					put(grammarAccess.getFnDefExprAccess().getArgsAssignment_3(), "rule__FnDefExpr__ArgsAssignment_3");
					put(grammarAccess.getFnDefExprAccess().getArgsAssignment_4_1(), "rule__FnDefExpr__ArgsAssignment_4_1");
					put(grammarAccess.getFnDefExprAccess().getTypeAssignment_7(), "rule__FnDefExpr__TypeAssignment_7");
					put(grammarAccess.getFnDefExprAccess().getExprAssignment_9(), "rule__FnDefExpr__ExprAssignment_9");
					put(grammarAccess.getNodeDefExprAccess().getNameAssignment_1(), "rule__NodeDefExpr__NameAssignment_1");
					put(grammarAccess.getNodeDefExprAccess().getArgsAssignment_3_0(), "rule__NodeDefExpr__ArgsAssignment_3_0");
					put(grammarAccess.getNodeDefExprAccess().getArgsAssignment_3_1_1(), "rule__NodeDefExpr__ArgsAssignment_3_1_1");
					put(grammarAccess.getNodeDefExprAccess().getRetsAssignment_7_0(), "rule__NodeDefExpr__RetsAssignment_7_0");
					put(grammarAccess.getNodeDefExprAccess().getRetsAssignment_7_1_1(), "rule__NodeDefExpr__RetsAssignment_7_1_1");
					put(grammarAccess.getNodeDefExprAccess().getNodeBodyAssignment_10(), "rule__NodeDefExpr__NodeBodyAssignment_10");
					put(grammarAccess.getNodeBodyExprAccess().getLocsAssignment_0_1_0(), "rule__NodeBodyExpr__LocsAssignment_0_1_0");
					put(grammarAccess.getNodeBodyExprAccess().getStmtsAssignment_2(), "rule__NodeBodyExpr__StmtsAssignment_2");
					put(grammarAccess.getNodeStmtAccess().getLhsAssignment_0_1(), "rule__NodeStmt__LhsAssignment_0_1");
					put(grammarAccess.getNodeStmtAccess().getLhsAssignment_0_2_1(), "rule__NodeStmt__LhsAssignment_0_2_1");
					put(grammarAccess.getNodeStmtAccess().getExprAssignment_0_4(), "rule__NodeStmt__ExprAssignment_0_4");
					put(grammarAccess.getNodeStmtAccess().getStrAssignment_1_2(), "rule__NodeStmt__StrAssignment_1_2");
					put(grammarAccess.getNodeStmtAccess().getExprAssignment_1_4(), "rule__NodeStmt__ExprAssignment_1_4");
					put(grammarAccess.getArgAccess().getNameAssignment_0(), "rule__Arg__NameAssignment_0");
					put(grammarAccess.getArgAccess().getTypeAssignment_2(), "rule__Arg__TypeAssignment_2");
					put(grammarAccess.getTypeAccess().getStringAssignment_0_1(), "rule__Type__StringAssignment_0_1");
					put(grammarAccess.getTypeAccess().getRecordAssignment_1_1(), "rule__Type__RecordAssignment_1_1");
					put(grammarAccess.getRecordDefExprAccess().getNameAssignment_1(), "rule__RecordDefExpr__NameAssignment_1");
					put(grammarAccess.getRecordDefExprAccess().getArgsAssignment_5_0(), "rule__RecordDefExpr__ArgsAssignment_5_0");
					put(grammarAccess.getRecordDefExprAccess().getArgsAssignment_5_1_1(), "rule__RecordDefExpr__ArgsAssignment_5_1_1");
					put(grammarAccess.getArrowExprAccess().getOpAssignment_1_0_0_1(), "rule__ArrowExpr__OpAssignment_1_0_0_1");
					put(grammarAccess.getArrowExprAccess().getRightAssignment_1_1(), "rule__ArrowExpr__RightAssignment_1_1");
					put(grammarAccess.getImpliesExprAccess().getOpAssignment_1_0_0_1(), "rule__ImpliesExpr__OpAssignment_1_0_0_1");
					put(grammarAccess.getImpliesExprAccess().getRightAssignment_1_1(), "rule__ImpliesExpr__RightAssignment_1_1");
					put(grammarAccess.getEquivExprAccess().getOpAssignment_1_0_0_1(), "rule__EquivExpr__OpAssignment_1_0_0_1");
					put(grammarAccess.getEquivExprAccess().getRightAssignment_1_1(), "rule__EquivExpr__RightAssignment_1_1");
					put(grammarAccess.getOrExprAccess().getOpAssignment_1_0_0_1(), "rule__OrExpr__OpAssignment_1_0_0_1");
					put(grammarAccess.getOrExprAccess().getRightAssignment_1_1(), "rule__OrExpr__RightAssignment_1_1");
					put(grammarAccess.getAndExprAccess().getOpAssignment_1_0_0_1(), "rule__AndExpr__OpAssignment_1_0_0_1");
					put(grammarAccess.getAndExprAccess().getRightAssignment_1_1(), "rule__AndExpr__RightAssignment_1_1");
					put(grammarAccess.getRelateExprAccess().getOpAssignment_1_0_0_1(), "rule__RelateExpr__OpAssignment_1_0_0_1");
					put(grammarAccess.getRelateExprAccess().getRightAssignment_1_1(), "rule__RelateExpr__RightAssignment_1_1");
					put(grammarAccess.getAddSubExprAccess().getOpAssignment_1_0_0_1(), "rule__AddSubExpr__OpAssignment_1_0_0_1");
					put(grammarAccess.getAddSubExprAccess().getRightAssignment_1_1(), "rule__AddSubExpr__RightAssignment_1_1");
					put(grammarAccess.getMultDivExprAccess().getOpAssignment_1_0_0_1(), "rule__MultDivExpr__OpAssignment_1_0_0_1");
					put(grammarAccess.getMultDivExprAccess().getRightAssignment_1_1(), "rule__MultDivExpr__RightAssignment_1_1");
					put(grammarAccess.getUnaryExprAccess().getOpAssignment_0_1(), "rule__UnaryExpr__OpAssignment_0_1");
					put(grammarAccess.getUnaryExprAccess().getExprAssignment_0_2(), "rule__UnaryExpr__ExprAssignment_0_2");
					put(grammarAccess.getIfThenElseExprAccess().getAAssignment_0_2(), "rule__IfThenElseExpr__AAssignment_0_2");
					put(grammarAccess.getIfThenElseExprAccess().getBAssignment_0_4(), "rule__IfThenElseExpr__BAssignment_0_4");
					put(grammarAccess.getIfThenElseExprAccess().getCAssignment_0_6(), "rule__IfThenElseExpr__CAssignment_0_6");
					put(grammarAccess.getPreDefFnExprAccess().getDelayAssignment_0_3(), "rule__PreDefFnExpr__DelayAssignment_0_3");
					put(grammarAccess.getPreDefFnExprAccess().getInitAssignment_0_5(), "rule__PreDefFnExpr__InitAssignment_0_5");
					put(grammarAccess.getPreDefFnExprAccess().getComponentAssignment_1_3(), "rule__PreDefFnExpr__ComponentAssignment_1_3");
					put(grammarAccess.getPreDefFnExprAccess().getPropAssignment_1_5(), "rule__PreDefFnExpr__PropAssignment_1_5");
					put(grammarAccess.getRecordUpdateExprAccess().getArgsAssignment_1_0_1_1(), "rule__RecordUpdateExpr__ArgsAssignment_1_0_1_1");
					put(grammarAccess.getRecordUpdateExprAccess().getArgExprAssignment_1_0_1_3(), "rule__RecordUpdateExpr__ArgExprAssignment_1_0_1_3");
					put(grammarAccess.getTermExprAccess().getValAssignment_1_1(), "rule__TermExpr__ValAssignment_1_1");
					put(grammarAccess.getTermExprAccess().getExprAssignment_2_3(), "rule__TermExpr__ExprAssignment_2_3");
					put(grammarAccess.getTermExprAccess().getValAssignment_3_1(), "rule__TermExpr__ValAssignment_3_1");
					put(grammarAccess.getTermExprAccess().getValAssignment_4_1(), "rule__TermExpr__ValAssignment_4_1");
					put(grammarAccess.getTermExprAccess().getSubThisAssignment_5_2_1(), "rule__TermExpr__SubThisAssignment_5_2_1");
					put(grammarAccess.getTermExprAccess().getExprAssignment_6_3(), "rule__TermExpr__ExprAssignment_6_3");
					put(grammarAccess.getTermExprAccess().getExprAssignment_7_3(), "rule__TermExpr__ExprAssignment_7_3");
					put(grammarAccess.getComplexExprAccess().getArgsAssignment_1_0_1_0(), "rule__ComplexExpr__ArgsAssignment_1_0_1_0");
					put(grammarAccess.getComplexExprAccess().getArgsAssignment_1_0_1_1_1(), "rule__ComplexExpr__ArgsAssignment_1_0_1_1_1");
					put(grammarAccess.getComplexExprAccess().getArgsAssignment_1_1_0_0_2(), "rule__ComplexExpr__ArgsAssignment_1_1_0_0_2");
					put(grammarAccess.getComplexExprAccess().getArgExprAssignment_1_1_1(), "rule__ComplexExpr__ArgExprAssignment_1_1_1");
					put(grammarAccess.getComplexExprAccess().getArgsAssignment_1_1_2_1(), "rule__ComplexExpr__ArgsAssignment_1_1_2_1");
					put(grammarAccess.getComplexExprAccess().getArgExprAssignment_1_1_2_3(), "rule__ComplexExpr__ArgExprAssignment_1_1_2_3");
					put(grammarAccess.getNestedDotIDAccess().getBaseAssignment_0(), "rule__NestedDotID__BaseAssignment_0");
					put(grammarAccess.getNestedDotIDAccess().getTagAssignment_1_0_1_0(), "rule__NestedDotID__TagAssignment_1_0_1_0");
					put(grammarAccess.getNestedDotIDAccess().getSubAssignment_1_0_1_1(), "rule__NestedDotID__SubAssignment_1_0_1_1");
					put(grammarAccess.getContainedPropertyAssociationAccess().getPropertyAssignment_0(), "rule__ContainedPropertyAssociation__PropertyAssignment_0");
					put(grammarAccess.getContainedPropertyAssociationAccess().getAppendAssignment_1_1(), "rule__ContainedPropertyAssociation__AppendAssignment_1_1");
					put(grammarAccess.getContainedPropertyAssociationAccess().getConstantAssignment_2(), "rule__ContainedPropertyAssociation__ConstantAssignment_2");
					put(grammarAccess.getContainedPropertyAssociationAccess().getOwnedValueAssignment_3_0(), "rule__ContainedPropertyAssociation__OwnedValueAssignment_3_0");
					put(grammarAccess.getContainedPropertyAssociationAccess().getOwnedValueAssignment_3_1_1(), "rule__ContainedPropertyAssociation__OwnedValueAssignment_3_1_1");
					put(grammarAccess.getContainedPropertyAssociationAccess().getAppliesToAssignment_4_2(), "rule__ContainedPropertyAssociation__AppliesToAssignment_4_2");
					put(grammarAccess.getContainedPropertyAssociationAccess().getAppliesToAssignment_4_3_1(), "rule__ContainedPropertyAssociation__AppliesToAssignment_4_3_1");
					put(grammarAccess.getContainedPropertyAssociationAccess().getInBindingAssignment_5_3(), "rule__ContainedPropertyAssociation__InBindingAssignment_5_3");
					put(grammarAccess.getPropertyAssociationAccess().getPropertyAssignment_0(), "rule__PropertyAssociation__PropertyAssignment_0");
					put(grammarAccess.getPropertyAssociationAccess().getAppendAssignment_1_1(), "rule__PropertyAssociation__AppendAssignment_1_1");
					put(grammarAccess.getPropertyAssociationAccess().getConstantAssignment_2(), "rule__PropertyAssociation__ConstantAssignment_2");
					put(grammarAccess.getPropertyAssociationAccess().getOwnedValueAssignment_3_0(), "rule__PropertyAssociation__OwnedValueAssignment_3_0");
					put(grammarAccess.getPropertyAssociationAccess().getOwnedValueAssignment_3_1_1(), "rule__PropertyAssociation__OwnedValueAssignment_3_1_1");
					put(grammarAccess.getPropertyAssociationAccess().getInBindingAssignment_4_3(), "rule__PropertyAssociation__InBindingAssignment_4_3");
					put(grammarAccess.getBasicPropertyAssociationAccess().getPropertyAssignment_0(), "rule__BasicPropertyAssociation__PropertyAssignment_0");
					put(grammarAccess.getBasicPropertyAssociationAccess().getOwnedValueAssignment_2(), "rule__BasicPropertyAssociation__OwnedValueAssignment_2");
					put(grammarAccess.getContainmentPathAccess().getContainmentPathElementAssignment_1(), "rule__ContainmentPath__ContainmentPathElementAssignment_1");
					put(grammarAccess.getContainmentPathAccess().getContainmentPathElementAssignment_2_1(), "rule__ContainmentPath__ContainmentPathElementAssignment_2_1");
					put(grammarAccess.getModalPropertyValueAccess().getOwnedValueAssignment_0(), "rule__ModalPropertyValue__OwnedValueAssignment_0");
					put(grammarAccess.getModalPropertyValueAccess().getInModeAssignment_4(), "rule__ModalPropertyValue__InModeAssignment_4");
					put(grammarAccess.getModalPropertyValueAccess().getInModeAssignment_5_1(), "rule__ModalPropertyValue__InModeAssignment_5_1");
					put(grammarAccess.getOptionalModalPropertyValueAccess().getOwnedValueAssignment_0(), "rule__OptionalModalPropertyValue__OwnedValueAssignment_0");
					put(grammarAccess.getOptionalModalPropertyValueAccess().getInModeAssignment_1_3(), "rule__OptionalModalPropertyValue__InModeAssignment_1_3");
					put(grammarAccess.getOptionalModalPropertyValueAccess().getInModeAssignment_1_4_1(), "rule__OptionalModalPropertyValue__InModeAssignment_1_4_1");
					put(grammarAccess.getPropertyValueAccess().getOwnedValueAssignment(), "rule__PropertyValue__OwnedValueAssignment");
					put(grammarAccess.getLiteralorReferenceTermAccess().getNamedValueAssignment(), "rule__LiteralorReferenceTerm__NamedValueAssignment");
					put(grammarAccess.getBooleanLiteralAccess().getValueAssignment_1_0(), "rule__BooleanLiteral__ValueAssignment_1_0");
					put(grammarAccess.getConstantValueAccess().getNamedValueAssignment(), "rule__ConstantValue__NamedValueAssignment");
					put(grammarAccess.getReferenceTermAccess().getContainmentPathElementAssignment_2_0(), "rule__ReferenceTerm__ContainmentPathElementAssignment_2_0");
					put(grammarAccess.getReferenceTermAccess().getContainmentPathElementAssignment_3(), "rule__ReferenceTerm__ContainmentPathElementAssignment_3");
					put(grammarAccess.getReferenceTermAccess().getContainmentPathElementAssignment_4_1(), "rule__ReferenceTerm__ContainmentPathElementAssignment_4_1");
					put(grammarAccess.getRecordTermAccess().getOwnedFieldValueAssignment_1(), "rule__RecordTerm__OwnedFieldValueAssignment_1");
					put(grammarAccess.getOldRecordTermAccess().getOwnedFieldValueAssignment_1(), "rule__OldRecordTerm__OwnedFieldValueAssignment_1");
					put(grammarAccess.getComputedTermAccess().getFunctionAssignment_2(), "rule__ComputedTerm__FunctionAssignment_2");
					put(grammarAccess.getComponentClassifierTermAccess().getClassifierAssignment_2(), "rule__ComponentClassifierTerm__ClassifierAssignment_2");
					put(grammarAccess.getListTermAccess().getOwnedListElementAssignment_2_0(), "rule__ListTerm__OwnedListElementAssignment_2_0");
					put(grammarAccess.getListTermAccess().getOwnedListElementAssignment_2_1_1(), "rule__ListTerm__OwnedListElementAssignment_2_1_1");
					put(grammarAccess.getFieldPropertyAssociationAccess().getPropertyAssignment_0(), "rule__FieldPropertyAssociation__PropertyAssignment_0");
					put(grammarAccess.getFieldPropertyAssociationAccess().getOwnedValueAssignment_2(), "rule__FieldPropertyAssociation__OwnedValueAssignment_2");
					put(grammarAccess.getContainmentPathElementAccess().getNamedElementAssignment_0(), "rule__ContainmentPathElement__NamedElementAssignment_0");
					put(grammarAccess.getContainmentPathElementAccess().getArrayRangeAssignment_1(), "rule__ContainmentPathElement__ArrayRangeAssignment_1");
					put(grammarAccess.getQualifiedContainmentPathElementAccess().getNamedElementAssignment(), "rule__QualifiedContainmentPathElement__NamedElementAssignment");
					put(grammarAccess.getStringTermAccess().getValueAssignment(), "rule__StringTerm__ValueAssignment");
					put(grammarAccess.getArrayRangeAccess().getLowerBoundAssignment_2(), "rule__ArrayRange__LowerBoundAssignment_2");
					put(grammarAccess.getArrayRangeAccess().getUpperBoundAssignment_3_1(), "rule__ArrayRange__UpperBoundAssignment_3_1");
					put(grammarAccess.getSignedConstantAccess().getOpAssignment_0(), "rule__SignedConstant__OpAssignment_0");
					put(grammarAccess.getSignedConstantAccess().getOwnedPropertyExpressionAssignment_1(), "rule__SignedConstant__OwnedPropertyExpressionAssignment_1");
					put(grammarAccess.getIntegerTermAccess().getValueAssignment_0(), "rule__IntegerTerm__ValueAssignment_0");
					put(grammarAccess.getIntegerTermAccess().getUnitAssignment_1(), "rule__IntegerTerm__UnitAssignment_1");
					put(grammarAccess.getRealTermAccess().getValueAssignment_0(), "rule__RealTerm__ValueAssignment_0");
					put(grammarAccess.getRealTermAccess().getUnitAssignment_1(), "rule__RealTerm__UnitAssignment_1");
					put(grammarAccess.getNumericRangeTermAccess().getMinimumAssignment_0(), "rule__NumericRangeTerm__MinimumAssignment_0");
					put(grammarAccess.getNumericRangeTermAccess().getMaximumAssignment_2(), "rule__NumericRangeTerm__MaximumAssignment_2");
					put(grammarAccess.getNumericRangeTermAccess().getDeltaAssignment_3_1(), "rule__NumericRangeTerm__DeltaAssignment_3_1");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			com.rockwellcollins.atc.agree.ui.contentassist.antlr.internal.InternalAgreeParser typedParser = (com.rockwellcollins.atc.agree.ui.contentassist.antlr.internal.InternalAgreeParser) parser;
			typedParser.entryRuleNamedElement();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_SL_COMMENT" };
	}
	
	public AgreeGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(AgreeGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
