package edu.gmu.cs795.lab1.inst.part2;

import org.objectweb.asm.MethodVisitor;
import static org.objectweb.asm.Opcodes.*;

public class MethodProfilingMV extends MethodVisitor {

	public String className;
	public String methodName;
	public String methodDesc;

	public MethodProfilingMV(String className, String methodName, String methodDesc, MethodVisitor mv) {
		super(ASM5, mv);
		this.className = className;
		this.methodName = methodName;
		this.methodDesc = methodDesc;
	}

	@Override
	public void visitCode() {
		super.visitCode();
		//Don't instrument any method that is not in the lab1/test package
		//Solves StackOverflow issues :)
		if(!className.startsWith("edu/gmu/cs795/lab1/test"))
			return;
		
		//Sample: edu/gmu/cs795/lab1/test/MethodTraceIT.otherMethod()V
		mv.visitLdcInsn(className+"."+methodName+methodDesc);
		mv.visitMethodInsn(INVOKESTATIC, 
				"edu/gmu/cs795/lab1/ProfileLogger", 
				"methodHit", "(Ljava/lang/String;)V", false);
	}
}
