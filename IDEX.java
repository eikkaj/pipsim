
public class IDEX {
	
	private static int writeInstr;
	private static int readInstr;
	
	//Pipeline Register for ID/EX stage
	private static int writeOffset;
	private static int readOffset;
	
	private static int writeRegS;
	private static int readRegS;
	
	private static int writeRegT;
	private static int readRegT;
	
	private static int writeRegD;
	private static int readRegD;
	
	private static int writeOpCode;
	private static int readOpCode;
	
	private static int writeFunct;
	private static int readFunct;

	//control signals
	private static int WriteRegDst;
	private static int ReadRegDst;
	
	private static int WriteALUOp;
	private static int ReadALUOp;
	
	private static int WriteALUSrc;
	private static int ReadALUSrc;
	
	private static int WriteMemRead;
	private static int ReadMemRead;
	
	private static int WriteMemWrite;
	private static int ReadMemWrite;
	
	private static int WriteMemtoReg;
	private static int ReadMemtoReg;
	
	private static int WriteRegWrite;
	private static int ReadRegWrite;
	
	
	private static int writeALUcontrol;
	private static int readALUcontrol;
	
	private static int writeReadData1;
	private static int readReadData1;
	
	private static int writeReadData2;
	private static int readReadData2;

	public static int getWriteReadData1() {
		return writeReadData1;
	}

	public static void setWriteReadData1(int writeReadData1) {
		IDEX.writeReadData1 = writeReadData1;
	}

	public static int getReadReadData1() {
		return readReadData1;
	}

	public static void setReadReadData1(int readReadData1) {
		IDEX.readReadData1 = readReadData1;
	}

	public static int getWriteReadData2() {
		return writeReadData2;
	}

	public static void setWriteReadData2(int writeReadData2) {
		IDEX.writeReadData2 = writeReadData2;
	}

	public static int getReadReadData2() {
		return readReadData2;
	}

	public static void setReadReadData2(int readReadData2) {
		IDEX.readReadData2 = readReadData2;
	}

	public static int getWriteALUcontrol() {
		return writeALUcontrol;
	}

	public static void setWriteALUcontrol(int writeALUcontrol) {
		IDEX.writeALUcontrol = writeALUcontrol;
	}

	public static int getReadALUcontrol() {
		return readALUcontrol;
	}

	public static void setReadALUcontrol(int readALUcontrol) {
		IDEX.readALUcontrol = readALUcontrol;
	}

	
	
	public static int getWriteRegDst() {
		return WriteRegDst;
	}
	public static void setWriteRegDst(int writeRegDst) {
		WriteRegDst = writeRegDst;
	}
	public static int getReadRegDst() {
		return ReadRegDst;
	}
	public static void setReadRegDst(int readRegDst) {
		ReadRegDst = readRegDst;
	}
	public static int getWriteALUOp() {
		return WriteALUOp;
	}
	public static void setWriteALUOp(int writeALUOp) {
		WriteALUOp = writeALUOp;
	}
	public static int getReadALUOp() {
		return ReadALUOp;
	}
	public static void setReadALUOp(int readALUOp) {
		ReadALUOp = readALUOp;
	}
	public static int getWriteALUSrc() {
		return WriteALUSrc;
	}
	public static void setWriteALUSrc(int writeALUSrc) {
		WriteALUSrc = writeALUSrc;
	}
	public static int getReadALUSrc() {
		return ReadALUSrc;
	}
	public static void setReadALUSrc(int readALUSrc) {
		ReadALUSrc = readALUSrc;
	}
	public static int getWriteMemRead() {
		return WriteMemRead;
	}
	public static void setWriteMemRead(int writeMemRead) {
		WriteMemRead = writeMemRead;
	}
	public static int getReadMemRead() {
		return ReadMemRead;
	}
	public static void setReadMemRead(int readMemRead) {
		ReadMemRead = readMemRead;
	}
	public static int getWriteMemWrite() {
		return WriteMemWrite;
	}
	public static void setWriteMemWrite(int writeMemWrite) {
		WriteMemWrite = writeMemWrite;
	}
	public static int getReadMemWrite() {
		return ReadMemWrite;
	}
	public static void setReadMemWrite(int readMemWrite) {
		ReadMemWrite = readMemWrite;
	}
	public static int getWriteMemtoReg() {
		return WriteMemtoReg;
	}
	public static void setWriteMemtoReg(int writeMemtoReg) {
		WriteMemtoReg = writeMemtoReg;
	}
	public static int getReadMemtoReg() {
		return ReadMemtoReg;
	}
	public static void setReadMemtoReg(int readMemtoReg) {
		ReadMemtoReg = readMemtoReg;
	}
	public static int getWriteRegWrite() {
		return WriteRegWrite;
	}
	public static void setWriteRegWrite(int writeRegWrite) {
		WriteRegWrite = writeRegWrite;
	}
	public static int getReadRegWrite() {
		return ReadRegWrite;
	}
	public static void setReadRegWrite(int readRegWrite) {
		ReadRegWrite = readRegWrite;
	}
	public static int getWriteOffset() {
		return writeOffset;
	}
	public static void setWriteOffset(int writeOffset) {
		IDEX.writeOffset = writeOffset;
	}
	public static int getReadOffset() {
		return readOffset;
	}
	public static void setReadOffset(int readOffset) {
		IDEX.readOffset = readOffset;
	}
	public static int getWriteRegS() {
		return writeRegS;
	}
	public static void setWriteRegS(int writeRegS) {
		IDEX.writeRegS = writeRegS;
	}
	public static int getReadRegS() {
		return readRegS;
	}
	public static void setReadRegS(int readRegS) {
		IDEX.readRegS = readRegS;
	}
	public static int getWriteRegT() {
		return writeRegT;
	}
	public static void setWriteRegT(int writeRegT) {
		IDEX.writeRegT = writeRegT;
	}
	public static int getReadRegT() {
		return readRegT;
	}
	public static void setReadRegT(int readRegT) {
		IDEX.readRegT = readRegT;
	}
	public static int getWriteRegD() {
		return writeRegD;
	}
	public static void setWriteRegD(int writeRegD) {
		IDEX.writeRegD = writeRegD;
	}
	public static int getReadRegD() {
		return readRegD;
	}
	public static void setReadRegD(int readRegD) {
		IDEX.readRegD = readRegD;
	}
	public static int getWriteOpCode() {
		return writeOpCode;
	}
	public static void setWriteOpCode(int writeOpCode) {
		IDEX.writeOpCode = writeOpCode;
	}
	public static int getReadOpCode() {
		return readOpCode;
	}
	public static void setReadOpCode(int readOpCode) {
		IDEX.readOpCode = readOpCode;
	}
	public static int getWriteFunct() {
		return writeFunct;
	}
	public static void setWriteFunct(int writeFunct) {
		IDEX.writeFunct = writeFunct;
	}
	public static int getReadFunct() {
		return readFunct;
	}
	public static void setReadFunct(int readFunct) {
		IDEX.readFunct = readFunct;
	}
	public static int getWriteInstr() {
		return writeInstr;
	}
	public static void setWriteInstr(int writeInstr) {
		IDEX.writeInstr = writeInstr;
	}
	public static int getReadInstr() {
		return readInstr;
	}
	public static void setReadInstr(int readInstr) {
		IDEX.readInstr = readInstr;
	}

	
	
	
}
