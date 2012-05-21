
public class MEMWB {
	//Pipeline register for MEM/WEB stage
	
	private static int readData;
	private static int writeData;
	
	private static int writeInstr;
	private static int readInstr;
	
	private static int writeRegS;
	private static int readRegS;
	
	private static int writeRegD;
	private static int readRegD;
	
	private static int writeRegT;
	private static int readRegT;
	
	private static int writeOffset;
	private static int readOffset;
	
	//control signals
	private static int writeRegWrite;
	private static int readRegWrite;
	
	private static int writeMemtoReg;
	private static int readMemtoReg;
	
	//sets which register will be written to
	private static int writeWriteRegister;
	private static int readWriteRegister;
	
	//result of ALU operation to be written back if necessary
	private static int writeALUresult;
	private static int readALUresult;
	

	
	public static int getWriteRegS() {
		return writeRegS;
	}

	public static void setWriteRegS(int writeRegS) {
		MEMWB.writeRegS = writeRegS;
	}

	public static int getReadRegS() {
		return readRegS;
	}

	public static void setReadRegS(int readRegS) {
		MEMWB.readRegS = readRegS;
	}

	public static int getWriteRegD() {
		return writeRegD;
	}

	public static void setWriteRegD(int writeRegD) {
		MEMWB.writeRegD = writeRegD;
	}

	public static int getReadRegD() {
		return readRegD;
	}

	public static void setReadRegD(int readRegD) {
		MEMWB.readRegD = readRegD;
	}

	public static int getWriteRegT() {
		return writeRegT;
	}

	public static void setWriteRegT(int writeRegT) {
		MEMWB.writeRegT = writeRegT;
	}

	public static int getReadRegT() {
		return readRegT;
	}

	public static void setReadRegT(int readRegT) {
		MEMWB.readRegT = readRegT;
	}

	public static int getWriteOffset() {
		return writeOffset;
	}

	public static void setWriteOffset(int writeOffset) {
		MEMWB.writeOffset = writeOffset;
	}

	public static int getReadOffset() {
		return readOffset;
	}

	public static void setReadOffset(int readOffset) {
		MEMWB.readOffset = readOffset;
	}

	public static int getWriteALUresult() {
		return writeALUresult;
	}

	public static void setWriteALUresult(int writeALUresult) {
		MEMWB.writeALUresult = writeALUresult;
	}

	public static int getReadALUresult() {
		return readALUresult;
	}

	public static void setReadALUresult(int readALUresult) {
		MEMWB.readALUresult = readALUresult;
	}

	public static int getWriteWriteRegister() {
		return writeWriteRegister;
	}

	public static void setWriteWriteRegister(int writeWriteRegister) {
		MEMWB.writeWriteRegister = writeWriteRegister;
	}

	public static int getReadWriteRegister() {
		return readWriteRegister;
	}

	public static void setReadWriteRegister(int readWriteRegister) {
		MEMWB.readWriteRegister = readWriteRegister;
	}
	

	public static int getWriteRegWrite() {
		return writeRegWrite;
	}

	public static void setWriteRegWrite(int writeRegWrite) {
		MEMWB.writeRegWrite = writeRegWrite;
	}

	public static int getReadRegWrite() {
		return readRegWrite;
	}

	public static void setReadRegWrite(int readRegWrite) {
		MEMWB.readRegWrite = readRegWrite;
	}

	public static int getWriteMemtoReg() {
		return writeMemtoReg;
	}

	public static void setWriteMemtoReg(int writeMemtoReg) {
		MEMWB.writeMemtoReg = writeMemtoReg;
	}

	public static int getReadMemtoReg() {
		return readMemtoReg;
	}

	public static void setReadMemtoReg(int readMemtoReg) {
		MEMWB.readMemtoReg = readMemtoReg;
	}

	public static int getWriteInstr() {
		return writeInstr;
	}

	public static void setWriteInstr(int writeInstr) {
		MEMWB.writeInstr = writeInstr;
	}

	public static int getReadInstr() {
		return readInstr;
	}

	public static void setReadInstr(int readInstr) {
		MEMWB.readInstr = readInstr;
	}

	public static int getWriteData() {
		return writeData;
	}

	public static void setWriteData(int writeData) {
		MEMWB.writeData = writeData;
	}

	public static int getReadData() {
		return readData;
	}

	public static void setReadData(int readData) {
		MEMWB.readData = readData;
	}
	
	
}
