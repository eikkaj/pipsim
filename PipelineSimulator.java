
public class PipelineSimulator {

	//array with instructions
	private static int instructionCache[];
	//1k main memory array
	private static int mainMemory[];
	//array of registers
	private static int regs[];

	public static void IF_stage(int i) {
		IFID.setWriteInstruction(i);
	}
	
	public static void ID_stage() {
		//Set registers in the Register block
		IDEX.setWriteOffset((short) (IFID.getReadInstruction() & 0xFFFF));
		IDEX.setWriteRegS((IFID.getReadInstruction() >>> 21) & 0x1F);
		IDEX.setWriteRegT((IFID.getReadInstruction() >>> 16) & 0x1F);
		IDEX.setWriteRegD((IFID.getReadInstruction() >>> 11) & 0x1F);
		IDEX.setWriteOpCode((IFID.getReadInstruction() >>> 26) & 0x3F);
		IDEX.setWriteFunct(IFID.getReadInstruction() & 0x3F);
		IDEX.setWriteInstr(IFID.getReadInstruction());
		
		//Get contents of registers $s $t $d
		IDEX.setWriteReadData1(regs[IDEX.getWriteRegS()]);
		IDEX.setWriteReadData2(regs[IDEX.getWriteRegT()]);
		
		if (IFID.getReadInstruction() != 0) {
			setControlSignals();
		} else {
			IDEX.setWriteRegDst(0);
			IDEX.setWriteRegWrite(0);
			IDEX.setWriteALUSrc(0);
			IDEX.setWriteMemRead(0);
			IDEX.setWriteMemWrite(0);
			IDEX.setWriteMemtoReg(0);
			IDEX.setWriteALUOp(0);
			IDEX.setWriteALUcontrol(0);
			
		}
	}
	
	public static void EX_stage() {
		EXMEM.setWriteInstr(IDEX.getReadInstr());
		EXMEM.setWriteMemRead(IDEX.getReadMemRead());
		EXMEM.setWriteMemtoReg(IDEX.getReadMemtoReg());
		EXMEM.setWriteMemWrite(IDEX.getReadMemWrite());
		EXMEM.setWriteRegWrite(IDEX.getReadRegWrite());
		EXMEM.setWriteRegD(IDEX.getReadRegD());
		EXMEM.setWriteRegS(IDEX.getReadRegS());
		EXMEM.setWriteRegT(IDEX.getReadRegT());
		EXMEM.setWriteOffset(IDEX.getReadOffset());
		EXMEM.setWriteReadData1(IDEX.getReadReadData1());
		EXMEM.setWriteReadData2(IDEX.getReadReadData2());
		//ALU Execution
		if (IDEX.getReadALUcontrol() == 2) {
			//Is it add or lb/sb
			if (IDEX.getReadALUSrc() == 1) {
				// load or store will add offset, not second register
				EXMEM.setWriteALUresult(IDEX.getReadReadData1() + IDEX.getReadOffset());
			}
			else if (IDEX.getReadALUSrc() == 0)
				EXMEM.setWriteALUresult(IDEX.getReadReadData1() + IDEX.getReadReadData2());
		} else if (IDEX.getReadALUcontrol() == 6) {
			//this must be a subtract operation
			EXMEM.setWriteALUresult(IDEX.getReadReadData1() - IDEX.getReadReadData2());
		}

		//Use RegDst to set which register will be written to with ALU executed product
		if (IDEX.getReadRegDst() == 0)
			EXMEM.setWriteWriteRegister(EXMEM.getWriteRegT());
		else
			EXMEM.setWriteWriteRegister(EXMEM.getWriteRegD());
	}
	
	public static void MEM_stage() {
		//Needed for WB stage
		MEMWB.setWriteWriteRegister(EXMEM.getReadWriteRegister());
		MEMWB.setWriteALUresult(EXMEM.getReadALUresult());
		//send regWrite and MemtoReg to MEMWB register
		MEMWB.setWriteRegWrite(EXMEM.getReadRegWrite());
		MEMWB.setWriteMemtoReg(EXMEM.getReadMemtoReg());
		//instruction data
		MEMWB.setWriteInstr(EXMEM.getReadInstr());
		MEMWB.setWriteRegD(EXMEM.getReadRegD());
		MEMWB.setWriteRegS(EXMEM.getReadRegS());
		MEMWB.setWriteRegT(EXMEM.getReadRegT());
		MEMWB.setWriteOffset(EXMEM.getReadOffset());
		
		//Check control signal MemRead, if asserted, this is a load - load data, prepare for write back stage
		if (EXMEM.getReadMemRead() == 1) {
			MEMWB.setWriteData(mainMemory[(MEMWB.getWriteALUresult() & 0xFF)]);
			
		}
		//Check control signal MemWrite, if asserted, this is a store - store data into mainMemory now
		if (EXMEM.getReadMemWrite() == 1) {
			mainMemory[MEMWB.getWriteALUresult()] = regs[MEMWB.getWriteRegT()];
		}
		

	}
	
	public static void WB_stage() {
		//RegWrite asserted if anything but store, which instruction would already be complete by now anyway
		//Check MemToReg, if not asserted, value fed to Write register comes from the ALU product
		//If MemtoReg is asserted, value fed to Write register comes from data memory
		if (MEMWB.getReadRegWrite() == 1) {
			if (MEMWB.getReadMemtoReg() == 0) {
				//Add or subtract
				regs[MEMWB.getReadWriteRegister()] = MEMWB.getReadALUresult();
			//value fed to register Write data input comes from the ALU
			} else if (MEMWB.getReadMemtoReg() == 1) {
				//This is a load- value fed to register Write data input comes from the data memory, stored as Write/Read Data in MEMWB reg
				regs[MEMWB.getReadWriteRegister()] = MEMWB.getReadData();
			}
		}
	}
	
	public static String opName(int i) {
		int tempOpcode = (i >>> 26) & 0x3F;
		String opName = "";
		switch (tempOpcode) {
		case 32 :
			opName = "lb";
			break;
		case 40:
			opName = "sb";
			break;
			
		}
		return opName;
			
	}
	
	public static String functionName(int i) {
		int tempFunction = i & 0x3F;
		String funcName = "";
		switch (tempFunction) {
		case 32:
			funcName = "add";
			break;
		case 34:
			funcName = "sub";
			break;
		}
		return funcName;
	}

	public static void Print_out_everything() {
		System.out.println("******IF/ID Register]******");
		System.out.print("Write Instr: ");
		if (store(IFID.getWriteInstruction()) || load(IFID.getWriteInstruction())) {
			System.out.print(opName(IFID.getWriteInstruction()) + " ");
			System.out.print("$" + ((IFID.getWriteInstruction() >>> 16) & 0x1F) + ",");
			System.out.print((short)(IFID.getWriteInstruction() & 0xFFFF) + "(");
			System.out.print("$" + ((IFID.getWriteInstruction() >>> 21) & 0x1F) + ") ");
		} else {
			System.out.print(functionName(IFID.getWriteInstruction()));
			System.out.print("$" + ((IFID.getWriteInstruction() >>> 11) & 0x1F) + ",");
			System.out.print("$" + ((IFID.getWriteInstruction() >>> 21) & 0x1F) + ",");
			System.out.print("$" + ((IFID.getWriteInstruction() >>> 16) & 0x1F));
		}
		System.out.println();
		System.out.print("Read Instr: ");
		if (store(IFID.getReadInstruction()) || load(IFID.getReadInstruction())) {
			System.out.print(opName(IFID.getReadInstruction()) + " ");
			System.out.print("$" + ((IFID.getReadInstruction() >>> 16) & 0x1F) + ",");
			System.out.print((short)(IFID.getReadInstruction() & 0xFFFF) + "(");
			System.out.print("$" +((IFID.getReadInstruction() >>> 21) & 0x1F) + ") ");
		} else {
			System.out.print(functionName(IFID.getReadInstruction()));
			System.out.print("$" + ((IFID.getReadInstruction() >>> 11) & 0x1F) + ",");
			System.out.print("$" + ((IFID.getReadInstruction() >>> 21) & 0x1F) + ",");
			System.out.print("$" + ((IFID.getReadInstruction() >>> 16) & 0x1F));
		}
		System.out.println();
		System.out.println();
		
		System.out.println("******[ID/EX Register]******");
		//Print Instructions and Control Signals
		if(store(IDEX.getWriteInstr()) || load(IDEX.getWriteInstr())) {
			System.out.print("Write Instruction ");
			System.out.print(opName(IDEX.getWriteInstr()));
			System.out.print("$" + IDEX.getWriteRegT() + ",");
			System.out.print(IDEX.getWriteOffset() + " (");
			System.out.print("$" + IDEX.getWriteRegS() + ") ");
			System.out.println();
			
		} else {
			//prints opcode func rd rs rt
			System.out.print("Write Instruction ");
			System.out.print(functionName(IDEX.getWriteInstr()));
			System.out.print("$" + IDEX.getWriteRegD() + ",");
			System.out.print("$" + IDEX.getWriteRegS() + ",");
			System.out.print("$" + IDEX.getWriteRegT());
			System.out.println();
		}
		System.out.println();
		System.out.println("----Write Control Signals----");
		System.out.println("RegDst: " + IDEX.getWriteRegDst());
		System.out.println("RegWrite: " + IDEX.getWriteRegWrite());
		System.out.println("ALUSrc: " + IDEX.getWriteALUSrc());
		System.out.println("MemRead: " + IDEX.getWriteMemRead());
		System.out.println("MemWrite: " + IDEX.getWriteMemWrite());
		System.out.println("MemToReg: " + IDEX.getWriteMemtoReg());
		System.out.println("ALUOp: " + IDEX.getWriteALUOp());
		System.out.println("ALU Control: " + IDEX.getWriteALUcontrol());
		System.out.println();
		System.out.print("Read Data 1: ");
		System.out.printf("%X",  IDEX.getReadReadData1());
		System.out.println();
		System.out.print("Read Data 2: ");
		System.out.printf("%X",  IDEX.getReadReadData2());
		System.out.println();
		System.out.println();
		
		
		if(store(IDEX.getReadInstr()) || load(IDEX.getReadInstr())) {
			System.out.print("Read Instruction ");
			System.out.print(opName(IDEX.getReadInstr()));
			System.out.print("$" + IDEX.getReadRegT() + ",");
			System.out.print(IDEX.getReadOffset() + " (");
			System.out.print("$" + IDEX.getReadRegS() + ") ");
			System.out.println();
			System.out.println();
	
			} else {
				//prints opcode func rd rs rt
				System.out.print("Read Instruction ");
				System.out.print(functionName(IDEX.getReadInstr()));
				System.out.print("$" + IDEX.getReadRegD() + ",");
				System.out.print("$" + IDEX.getReadRegS() + ",");
				System.out.print("$" + IDEX.getReadRegT());
				System.out.println();
				System.out.println();
			}
		System.out.println("----Read Control Signals----");
		System.out.println("RegDst: " + IDEX.getReadRegDst());
		System.out.println("RegWrite: " + IDEX.getReadRegWrite());
		System.out.println("ALUSrc: " + IDEX.getReadALUSrc());
		System.out.println("MemRead: " + IDEX.getReadMemRead());
		System.out.println("MemWrite: " + IDEX.getReadMemWrite());
		System.out.println("MemToReg: " + IDEX.getReadMemtoReg());
		System.out.println("ALUOp: " + IDEX.getReadALUOp());
		System.out.println("ALU Control: " + IDEX.getReadALUcontrol());
		System.out.println();
		System.out.print("Read Data 1: ");
		System.out.printf("%X",  IDEX.getWriteReadData1());
		System.out.println();
		System.out.print("Read Data 2: ");
		System.out.printf("%X",  IDEX.getWriteReadData2());
		System.out.println();
		System.out.println();
		
		
		System.out.println("******[EX/MEM Register]******");
		if(rType(EXMEM.getWriteInstr())) {
			//prints opcode func rd rs rt
			System.out.print("Write Instruction ");
			System.out.print(functionName(EXMEM.getWriteInstr()));
			System.out.print("$" + EXMEM.getWriteRegD() + ",");
			System.out.print("$" + EXMEM.getWriteRegS() + ",");
			System.out.print("$" + EXMEM.getWriteRegT());
			System.out.println();
			System.out.println();
			
		} else {
			System.out.print("Write Instruction ");
			System.out.print(opName(EXMEM.getWriteInstr()));
			System.out.print("$" + EXMEM.getWriteRegT() + ",");
			System.out.print(EXMEM.getWriteOffset() + " (");
			System.out.print("$" + EXMEM.getWriteRegS() + ") ");
			System.out.println();
			System.out.println();
		}
		
		
		System.out.println("----Write Control Signals----");
		System.out.println("RegWrite: " + EXMEM.getWriteRegWrite());
		System.out.println("MemRead: " + EXMEM.getWriteMemRead());
		System.out.println("MemWrite: " + EXMEM.getWriteMemWrite());
		System.out.println("MemToReg: " + EXMEM.getWriteMemtoReg());
		System.out.println();
		System.out.print("ALU Result: ");
		System.out.printf("%X", EXMEM.getWriteALUresult());
		System.out.println();
		if (rType(EXMEM.getWriteInstr()) || load(EXMEM.getWriteInstr())) {
			System.out.println("Write Register: $" + EXMEM.getWriteWriteRegister());
		}
		System.out.println();
		
		if(rType(EXMEM.getReadInstr())) {
			//prints opcode func rd rs rt
			System.out.print("Read Instruction ");
			System.out.print(functionName(EXMEM.getReadInstr()));
			System.out.print("$" + EXMEM.getReadRegD() + ",");
			System.out.print("$" + EXMEM.getReadRegS() + ",");
			System.out.print("$" + EXMEM.getReadRegT());
			System.out.println();
			System.out.println();
			
		} else {
			System.out.print("Read Instruction ");
			System.out.print(opName(EXMEM.getReadInstr()));
			System.out.print("$" + EXMEM.getReadRegT() + ",");
			System.out.print(EXMEM.getReadOffset() + " (");
			System.out.print("$" + EXMEM.getReadRegS() + ") ");
			System.out.println();
			System.out.println();
		}
		
		System.out.println("----Read Control Signals----");
		System.out.println("RegWrite: " + EXMEM.getReadRegWrite());
		System.out.println("MemRead: " + EXMEM.getReadMemRead());
		System.out.println("MemWrite: " + EXMEM.getReadMemWrite());
		System.out.println("MemToReg: " + EXMEM.getReadMemtoReg());
		System.out.println();
		System.out.print("ALU Result: ");
		System.out.printf("%X", EXMEM.getReadALUresult());
		System.out.println();
		if (rType(EXMEM.getReadInstr()) || load(EXMEM.getReadInstr())) {
			System.out.println("Write Register: $" + EXMEM.getReadWriteRegister());
		}
		System.out.println();
			
		System.out.println("******[MEM/WB Register]******");
		
		if(rType(MEMWB.getWriteInstr())) {
			//prints opcode func rd rs rt
			System.out.print("Write Instruction ");
			System.out.print(functionName(MEMWB.getWriteInstr()));
			System.out.print("$" + MEMWB.getWriteRegD() + ",");
			System.out.print("$" + MEMWB.getWriteRegS() + ",");
			System.out.print("$" + MEMWB.getWriteRegT());
			System.out.println();
			System.out.println();
			
		} else {
			System.out.print("Write Instruction ");
			System.out.print(opName(MEMWB.getWriteInstr()));
			System.out.print("$" + MEMWB.getWriteRegT() + ",");
			System.out.print(MEMWB.getWriteOffset() + " (");
			System.out.print("$" + MEMWB.getWriteRegS() + ") ");
			System.out.println();
			System.out.println();
		}
		
		System.out.println("----Write Control Signals----");
		System.out.println("RegWrite: " + MEMWB.getWriteRegWrite());
		System.out.println("MemToReg: " + MEMWB.getWriteMemtoReg());
		if (load(MEMWB.getWriteInstr())) {
			System.out.println("Data to load from main memory: " + MEMWB.getWriteData());
		} else if (store(MEMWB.getWriteInstr())) {
			System.out.println("Main Memory #" + MEMWB.getWriteALUresult() + " now has value [" + mainMemory[MEMWB.getWriteALUresult()] + "]");
		}
		System.out.println();
		
		if(rType(MEMWB.getReadInstr())) {
			//prints opcode func rd rs rt
			System.out.print("Read Instruction ");
			System.out.print(functionName(MEMWB.getReadInstr()));
			System.out.print("$" + MEMWB.getReadRegD() + ",");
			System.out.print("$" + MEMWB.getReadRegS() + ",");
			System.out.print("$" + MEMWB.getReadRegT());
			System.out.println();
			System.out.println();
			
		} else {
			System.out.print("Read Instruction ");
			System.out.print(opName(MEMWB.getReadInstr()));
			System.out.print("$" + MEMWB.getReadRegT() + ",");
			System.out.print(MEMWB.getReadOffset() + " (");
			System.out.print("$" + MEMWB.getReadRegS() + ") ");
			System.out.println();
			System.out.println();
		}
		System.out.println("----Read Control Signals----");
		System.out.println("RegWrite: " + MEMWB.getReadRegWrite());
		System.out.println("MemToReg: " + MEMWB.getReadMemtoReg());
		System.out.println();
		if (load(MEMWB.getReadInstr()))
			System.out.println("Data to load from main memory: " + MEMWB.getReadData());
		
		System.out.println();
		System.out.println("Register File: ");
		for (int i = 0; i < regs.length; i++) {
			System.out.printf("%X",regs[i]);
			System.out.print(" |");
		}
		System.out.println();
		System.out.println();
		//System.out.println("Main Memory: ");
		//for (int i = 0; i < mainMemory.length; i++) {
		//	System.out.print(mainMemory[i] + "|");
		//}
		
		System.out.println();
		System.out.println();
		}
	
	public static void Copy_write_to_read() {
		//copies everything from write register to read registers

		IFID.setReadInstruction(IFID.getWriteInstruction());
		
		IDEX.setReadInstr(IDEX.getWriteInstr());
		IDEX.setReadALUOp(IDEX.getWriteALUOp());
		IDEX.setReadALUSrc(IDEX.getWriteALUSrc());
		IDEX.setReadALUcontrol(IDEX.getWriteALUcontrol());
		IDEX.setReadRegWrite(IDEX.getWriteRegWrite());
		IDEX.setReadMemRead(IDEX.getWriteMemRead());
		IDEX.setReadMemtoReg(IDEX.getWriteMemtoReg());
		IDEX.setReadMemWrite(IDEX.getWriteMemWrite());
		IDEX.setReadRegDst(IDEX.getWriteRegDst());
		IDEX.setReadFunct(IDEX.getWriteFunct());
		IDEX.setReadOffset(IDEX.getWriteOffset());
		IDEX.setReadOpCode(IDEX.getWriteOpCode());
		IDEX.setReadRegD(IDEX.getWriteRegD());
		IDEX.setReadRegS(IDEX.getWriteRegS());
		IDEX.setReadRegT(IDEX.getWriteRegT());
		IDEX.setReadReadData1(IDEX.getWriteReadData1());
		IDEX.setReadReadData2(IDEX.getWriteReadData2());

		
		EXMEM.setReadInstr(EXMEM.getWriteInstr());
		EXMEM.setReadMemRead(EXMEM.getWriteMemRead());
		EXMEM.setReadMemtoReg(EXMEM.getWriteMemtoReg());
		EXMEM.setReadMemWrite(EXMEM.getWriteMemWrite());
		EXMEM.setReadRegD(EXMEM.getWriteRegD());
		EXMEM.setReadRegS(EXMEM.getWriteRegS());
		EXMEM.setReadRegT(EXMEM.getWriteRegT());
		EXMEM.setReadOffset(EXMEM.getWriteOffset());
		EXMEM.setReadRegWrite(EXMEM.getWriteRegWrite());
		EXMEM.setReadALUresult(EXMEM.getWriteALUresult());
		EXMEM.setReadWriteRegister(EXMEM.getWriteWriteRegister());
		EXMEM.setReadReadData1(EXMEM.getWriteReadData1());
		EXMEM.setReadReadData2(EXMEM.getWriteReadData2());

		MEMWB.setReadData(MEMWB.getWriteData());
		MEMWB.setReadInstr(MEMWB.getWriteInstr());
		MEMWB.setReadMemtoReg(MEMWB.getWriteMemtoReg());
		MEMWB.setReadRegWrite(MEMWB.getWriteRegWrite());
		MEMWB.setReadALUresult(MEMWB.getWriteALUresult());
		MEMWB.setReadOffset(MEMWB.getWriteOffset());
		MEMWB.setReadRegD(MEMWB.getWriteRegD());
		MEMWB.setReadRegS(MEMWB.getWriteRegS());
		MEMWB.setReadRegT(MEMWB.getWriteRegT());
		MEMWB.setReadWriteRegister(MEMWB.getWriteWriteRegister());

	}
	
	public static boolean rType(int i) {
		if (((i >>> 26) & 0x3f) == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean load(int i) {
		if (((i >>> 26) & 0x3f) == 0x20) {
			return true;
		}
		return false;
	}
	
	public static boolean store(int i) {
		if (((i >>> 26) & 0x3f) == 0x28) {
			return true;
		}
		return false;
	}
	
	public static void setControlSignals() {
		//sets the control signals in the ID stage if the instruction is not a NOP
		
		//RegDst set at 1 for R-types
		if (rType(IFID.getReadInstruction())) {
			IDEX.setWriteRegDst(1);
		} else {
			IDEX.setWriteRegDst(0);
		}
		
		//ALUOp sets ALU operation
		//look at function for rtypes
		if (rType(IFID.getReadInstruction())) {
			if (IDEX.getWriteFunct() == 0x20) {
				//this is an add
				IDEX.setWriteALUOp(2);
			} else if (IDEX.getWriteFunct() == 0x22) {
				//subtract
				IDEX.setWriteALUOp(2);
			}
		} else {
			//load store
			IDEX.setWriteALUOp(0);
		}
		
		//ALUSrc sets to read data 2 or offset for the ALU
		if (rType(IFID.getReadInstruction())) {
			IDEX.setWriteALUSrc(0);
		} else {
			IDEX.setWriteALUSrc(1);
		}
		
		//MemRead for lw
		if (load(IFID.getReadInstruction())) {
			IDEX.setWriteMemRead(1);
		} else {
			IDEX.setWriteMemRead(0);
		}
		//MemWrite for sw
		if (store(IFID.getReadInstruction())) {
			IDEX.setWriteMemWrite(1);
		} else {
			IDEX.setWriteMemWrite(0);
		}
		//MemtoReg sends ALU result or Mem value to reg file
		//If instruction is load or store, assert, else deassert
		if (store(IFID.getReadInstruction()) || load(IFID.getReadInstruction())) {
			IDEX.setWriteMemtoReg(1);
		} else {
			IDEX.setWriteMemtoReg(0);
		}
		//RegWrite writes chosen value
		//assert if anything but store word
		if (store(IFID.getReadInstruction())) {
			IDEX.setWriteRegWrite(0);
		} else {
			IDEX.setWriteRegWrite(1);
		}
		//Get the ALU OP and Function code, determine the ALU control which will tell ALU what operation to do add/sub
		if (IDEX.getWriteALUOp() == 0) {
			//this is a load or store
			IDEX.setWriteALUcontrol(2);
		} 
		
		if (IDEX.getWriteALUOp() == 2) {
			//get the function code
			if (IDEX.getWriteFunct() == 0x22) {
				IDEX.setWriteALUcontrol(6);
			} else if (IDEX.getWriteFunct() == 0x28) {
				IDEX.setWriteALUcontrol(2);
			}
		}
	}

	public static void main(String[] args) {
		//array of the instructions we are going to run through
		instructionCache = new int[12];
		instructionCache[0] = 0xa1020000;
		instructionCache[1] = 0x810afffc;
		instructionCache[2] = 0x00831820;
		instructionCache[3] = 0x01263820;
		instructionCache[4] = 0x01224820;
		instructionCache[5] = 0x81180000;
		instructionCache[6] = 0x81510010;
		instructionCache[7] = 0x00624022;
		instructionCache[8] = 0x0;
		instructionCache[9] = 0x0;
		instructionCache[10] = 0x0;
		instructionCache[11] = 0x0;
		
		//initialize mainMemory
		mainMemory = new int[1024];
		for (int i = 0; i < mainMemory.length; i++) {
			mainMemory[i] = 0xFF & i;
		}
		
		//initialize register values 0-32
		regs = new int[32];
		regs[0] = 0;
		for (int i = 1; i < regs.length; i++) {
			regs[i] = 0x100 + i;
		}
		
		//loops through each stage for each instruction
		for (int i = 0; i < instructionCache.length; i++) {
			System.out.println("****Clock Cycle " + (i+1) + "****");
			IF_stage(instructionCache[i]);
			ID_stage();
			EX_stage();
			MEM_stage();
			WB_stage();
			Print_out_everything();
			Copy_write_to_read();
		}
	}

}
