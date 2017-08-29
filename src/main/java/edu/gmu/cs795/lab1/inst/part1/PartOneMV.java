package edu.gmu.cs795.lab1.inst.part1;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.*;

public class PartOneMV extends MethodVisitor {
	private String methodName;
	public PartOneMV(MethodVisitor mv, String name) {
		super(ASM5, mv);
		this.methodName = name;
	}
	
	@Override
	public void visitInsn(int opcode) {
		if(opcode == RETURN)
		{
			mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitLdcInsn("Hello, ASM!");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

		}
		super.visitInsn(opcode);
	}
	
}
