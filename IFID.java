
public class IFID {
//Pipeline register for stage IF/ID
	//There should be a read side and a write side
	private static int readInstruction;	
	private static int writeInstruction;	
	
	private static int readPCIncr;	
	private static int writePCIncr;
	

	public static int getReadInstruction() {
		return readInstruction;
	}
	public static void setReadInstruction(int readInstruction) {
		IFID.readInstruction = readInstruction;
	}
	
	public static int getWriteInstruction() {
		return writeInstruction;
	}
	public static void setWriteInstruction(int writeInstruction) {
		IFID.writeInstruction = writeInstruction;
	}
	
	public static int getReadPCIncr() {
		return readPCIncr;
	}
	public static void setReadPCIncr(int readPCIncr) {
		IFID.readPCIncr = readPCIncr;
	}
	
	public static int getWritePCIncr() {
		return writePCIncr;
	}
	public static void setWritePCIncr(int writePCIncr) {
		IFID.writePCIncr = writePCIncr;
	}

	
}
