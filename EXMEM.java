
public class EXMEM {
	//Pipeline Register for EX/MEM stage
	

	//registers
	private static int writeRegD;
	private static int readRegD;

	private static int writeRegS;
	private static int readRegS;
	
	private static int writeRegT;
	private static int readRegT;
	
	private static int writeOffset;
	private static int readOffset;
	
	//instruction
	private static int writeInstr;
	private static int readInstr;
	
	//control signals
	private static int writeRegWrite;
	private static int readRegWrite;
	
	private static int writeMemRead;
	private static int readMemRead;
	
	private static int writeMemWrite;
	private static int readMemWrite;
	
	private static int writeMemtoReg;
	private static int readMemtoReg;
	
	//sets which register will be written to
	private static int writeWriteRegister;
	private static int readWriteRegister;
	
	//result of ALU execution - either addition or subtraction
	private static int WriteALUresult;
	private static int ReadALUresult;
	
	private static int writeReadData1;
	private static int readReadData1;
	
	private static int writeReadData2;
	private static int readReadData2;
	
	
	public static int getWriteReadData1() {
		return writeReadData1;
	}

	public static void setWriteReadData1(int writeReadData1) {
		EXMEM.writeReadData1 = writeReadData1;
	}

	public static int getReadReadData1() {
		return readReadData1;
	}

	public static void setReadReadData1(int readReadData1) {
		EXMEM.readReadData1 = readReadData1;
	}

	public static int getWriteReadData2() {
		return writeReadData2;
	}

	public static void setWriteReadData2(int writeReadData2) {
		EXMEM.writeReadData2 = writeReadData2;
	}

	public static int getReadReadData2() {
		return readReadData2;
	}

	public static void setReadReadData2(int readReadData2) {
		EXMEM.readReadData2 = readReadData2;
	}

	public static int getWriteOffset() {
		return writeOffset;
	}

	public static void setWriteOffset(int writeOffset) {
		EXMEM.writeOffset = writeOffset;
	}

	public static int getReadOffset() {
		return readOffset;
	}

	public static void setReadOffset(int readOffset) {
		EXMEM.readOffset = readOffset;
	}

	public static int getWriteALUresult() {
		return WriteALUresult;
	}

	public static void setWriteALUresult(int writeALUresult) {
		WriteALUresult = writeALUresult;
	}

	public static int getReadALUresult() {
		return ReadALUresult;
	}

	public static void setReadALUresult(int readALUresult) {
		ReadALUresult = readALUresult;
	}

	

	public static int getWriteWriteRegister() {
		return writeWriteRegister;
	}

	public static void setWriteWriteRegister(int writeWriteRegister) {
		EXMEM.writeWriteRegister = writeWriteRegister;
	}

	public static int getReadWriteRegister() {
		return readWriteRegister;
	}

	public static void setReadWriteRegister(int readWriteRegister) {
		EXMEM.readWriteRegister = readWriteRegister;
	}

	public static int getWriteRegWrite() {
		return writeRegWrite;
	}

	public static void setWriteRegWrite(int writeRegWrite) {
		EXMEM.writeRegWrite = writeRegWrite;
	}

	public static int getReadRegWrite() {
		return readRegWrite;
	}

	public static void setReadRegWrite(int readRegWrite) {
		EXMEM.readRegWrite = readRegWrite;
	}

	public static int getWriteMemRead() {
		return writeMemRead;
	}

	public static void setWriteMemRead(int writeMemRead) {
		EXMEM.writeMemRead = writeMemRead;
	}

	public static int getReadMemRead() {
		return readMemRead;
	}

	public static void setReadMemRead(int readMemRead) {
		EXMEM.readMemRead = readMemRead;
	}

	public static int getWriteMemWrite() {
		return writeMemWrite;
	}

	public static void setWriteMemWrite(int writeMemWrite) {
		EXMEM.writeMemWrite = writeMemWrite;
	}

	public static int getReadMemWrite() {
		return readMemWrite;
	}

	public static void setReadMemWrite(int readMemWrite) {
		EXMEM.readMemWrite = readMemWrite;
	}

	public static int getWriteMemtoReg() {
		return writeMemtoReg;
	}

	public static void setWriteMemtoReg(int writeMemtoReg) {
		EXMEM.writeMemtoReg = writeMemtoReg;
	}

	public static int getReadMemtoReg() {
		return readMemtoReg;
	}

	public static void setReadMemtoReg(int readMemtoReg) {
		EXMEM.readMemtoReg = readMemtoReg;
	}

	public static int getWriteRegS() {
		return writeRegS;
	}

	public static void setWriteRegS(int writeRegS) {
		EXMEM.writeRegS = writeRegS;
	}

	public static int getReadRegS() {
		return readRegS;
	}

	public static void setReadRegS(int readRegS) {
		EXMEM.readRegS = readRegS;
	}

	public static int getWriteRegD() {
		return writeRegD;
	}

	public static void setWriteRegD(int writeRegD) {
		EXMEM.writeRegD = writeRegD;
	}

	public static int getReadRegD() {
		return readRegD;
	}

	public static void setReadRegD(int readRegD) {
		EXMEM.readRegD = readRegD;
	}



	public static int getWriteInstr() {
		return writeInstr;
	}

	public static void setWriteInstr(int writeInstr) {
		EXMEM.writeInstr = writeInstr;
	}

	public static int getReadInstr() {
		return readInstr;
	}

	public static void setReadInstr(int readInstr) {
		EXMEM.readInstr = readInstr;
	}

	public static int getWriteRegT() {
		return writeRegT;
	}

	public static void setWriteRegT(int writeRegT) {
		EXMEM.writeRegT = writeRegT;
	}

	public static int getReadRegT() {
		return readRegT;
	}

	public static void setReadRegT(int readRegT) {
		EXMEM.readRegT = readRegT;
	}
	
	
	
	
}
